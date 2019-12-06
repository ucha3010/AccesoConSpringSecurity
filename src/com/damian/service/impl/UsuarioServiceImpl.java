package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.damian.dao.UsuarioDAO;
import com.damian.exceptions.RepeatedUsernameException;
import com.damian.pojo.DatosPersonales;
import com.damian.pojo.Direccion;
import com.damian.pojo.Rol;
import com.damian.pojo.Usuario;
import com.damian.pojo.UsuarioEmpresa;
import com.damian.pojo.UsuarioRol;
import com.damian.service.DatosPersonalesService;
import com.damian.service.DireccionService;
import com.damian.service.RolService;
import com.damian.service.UsuarioEmpresaService;
import com.damian.service.UsuarioRolService;
import com.damian.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private DatosPersonalesService datosPersonalesService;

	@Autowired
	private UsuarioRolService usuarioRolService;

	@Autowired
	private UsuarioEmpresaService usuarioEmpresaService;

	@Autowired
	private RolService rolService;
	
	@Autowired
	private DireccionService direccionService;

	@Override
	public Usuario findById(int id) {
		return usuarioDAO.findById(id);
	}

	@Override
	public List<Usuario> findByIdList(int id) {
		return usuarioDAO.findByIdList(id);
	}

	@Override
	public Usuario findByUsername(String usuario) {
		return usuarioDAO.findByUsername(usuario);
	}

	@Override
	public void save(Usuario usuario, String[] usuarioRol, HttpServletRequest request)
			throws RepeatedUsernameException {

		DatosPersonales dp;
		dp = usuario.getDatosPersonales();

		if (usuario.getIdUsr() == 0) {
			Usuario verifico = findByUsername(usuario.getUsuario());
			if (verifico != null) {
				throw new RepeatedUsernameException(usuario.getUsuario());
			}
			usuario.setFechaCreacion(new Timestamp(new Date().getTime()));
			usuario.setHabilitado(true);
			String claveUsr = usuario.getClave();
			usuario.setClave(passwordEncoder.encode(claveUsr));
			usuarioDAO.save(usuario);
			usuario = findByUsername(usuario.getUsuario());
			dp.setUsuario(usuario);
			datosPersonalesService.save(dp);
		} else {
			DatosPersonales dpId = datosPersonalesService.findByUsrId(usuario.getIdUsr());
			dp.setIdDatosPers(dpId.getIdDatosPers());
			dp.setUsuario(usuario);
			datosPersonalesService.update(dp);
		}
		if (usuarioRol != null) {
			eliminarRolesNoSeleccionados(usuarioRol, usuario);
			String[] nuevosRoles = rolesAGuardar(usuarioRol, usuario);
			org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
					.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
			for (String rolId : nuevosRoles) {
				Rol rol = rolService.findById(Integer.parseInt(rolId));
				UsuarioRol ur = new UsuarioRol();
				ur.setRol(rol);
				ur.setUsuario(usuario);
				ur.setFechaCreacion(new Date());
				if (context != null) {
					ur.setCreadoPor(context.getAuthentication().getName());
				} else {
					ur.setCreadoPor("OWN USER");
				}
				usuarioRolService.save(ur);
			}
		}
	}

	@Override
	public void saveChangePassword(Usuario usuario) {

		String claveUsr = usuario.getClave();
		usuario = findById(usuario.getIdUsr());
		usuario.setClave(passwordEncoder.encode(claveUsr));
		update(usuario);

	}

	@Override
	public List<Usuario> findAll() {
		return usuarioDAO.findAll();
	}

	@Override
	public List<Usuario> findAllOrderByNombre() {
		return usuarioDAO.findAllOrderByNombre();
	}

	@Override
	public void update(Usuario usuario) {
		usuarioDAO.update(usuario);
	}

	@Override
	public void delete(int idUsr) {
		Usuario usuario = findById(idUsr);
		if(usuario.getDatosPersonales().getDirecciones() != null) {
			for(Direccion direccion: usuario.getDatosPersonales().getDirecciones()) {
				direccionService.delete(direccion.getIdDir());
			}
		}
		datosPersonalesService.delete(usuario.getDatosPersonales().getIdDatosPers());
		List<UsuarioEmpresa> ueList = usuarioEmpresaService.findByIdUsr(idUsr);
		if(ueList != null) {
			for(UsuarioEmpresa ue: ueList) {
				usuarioEmpresaService.delete(idUsr, ue.getEmpresa().getIdEmp());
			}
		}
		List<UsuarioRol> urList = usuarioRolService.findByIdUsr(idUsr);
		if(urList != null) {
			for(UsuarioRol ur:urList) {
				usuarioRolService.delete(idUsr, ur.getRol().getIdRol());
			}
		}
		usuarioDAO.delete(usuario);
	}

	@Override
	public List<Usuario> findCustomers() {
		List<Usuario> usuarios = usuarioDAO.findAll();
		List<Usuario> clientes = new ArrayList<>();
		for (Usuario usuario : usuarios) {
			List<UsuarioRol> roles = usuario.getUsuarioRol();
			for (UsuarioRol rol : roles) {
				if (rol.getRol().getRol().equalsIgnoreCase("ROL_CLIENTE")) {
					clientes.add(usuario);
				}
			}
		}
		return clientes;
	}

	@Override
	public List<Usuario> findFilteredCustomers(int idUsr) {
		List<Usuario> clientes = new ArrayList<>();
		clientes.add(findById(idUsr));
		return clientes;
	}

	@Override
	public void fillNewUser(Usuario usuario) {
		Direccion direccion = new Direccion();
		List<Direccion> direcciones = new ArrayList<>();
		direcciones.add(direccion);
		DatosPersonales datosPersonales = new DatosPersonales();
		datosPersonales.setDirecciones(direcciones);
		usuario.setDatosPersonales(datosPersonales);
	}

	@Override
	public void fillExistingUser(Usuario usuario) {
		List<UsuarioRol> usuarioRoles = usuarioRolService.findByIdUsr(usuario.getIdUsr());
		usuario.setUsuarioRol(usuarioRoles);
		List<UsuarioEmpresa> usuarioEmpresas = usuarioEmpresaService.findByIdUsr(usuario.getIdUsr());
		usuario.setUsuarioEmpresa(usuarioEmpresas);
	}

	@Override
	public Usuario reset(int idUsr) {
		Usuario usuario = findById(idUsr);
		usuario.setClave(passwordEncoder.encode("Superman1"));
		update(usuario);
		return usuario;
	}

	private void eliminarRolesNoSeleccionados(String[] usuarioRol, Usuario usuario) {
		List<UsuarioRol> rolesQueTraia = usuarioRolService.findByIdUsr(usuario.getIdUsr());
		List<Integer> rolesABorrar = new ArrayList<>();
		Boolean estaba;
		for (UsuarioRol urTraia : rolesQueTraia) {
			estaba = false;
			for (String rolNuevo : usuarioRol) {
				if (urTraia.getRol().getIdRol() == Integer.parseInt(rolNuevo)) {
					estaba = true;
				}
			}
			if (!estaba) {
				rolesABorrar.add(urTraia.getRol().getIdRol());
			}
		}
		if (!rolesABorrar.isEmpty()) {
			for (Integer idRol : rolesABorrar) {
				usuarioRolService.delete(usuario.getIdUsr(), idRol);
			}
		}
	}

	private String[] rolesAGuardar(String[] usuarioRol, Usuario usuario) {
		List<UsuarioRol> rolesQueTraia = usuarioRolService.findByIdUsr(usuario.getIdUsr());
		if (rolesQueTraia == null || rolesQueTraia.isEmpty()) {
			return usuarioRol;
		} else {
			boolean estaba;
			List<String> agregar = new ArrayList<>();
			for (String rolNuevo : usuarioRol) {
				estaba = false;
				for (UsuarioRol urTraia : rolesQueTraia) {
					if (urTraia.getRol().getIdRol() == Integer.parseInt(rolNuevo)) {
						estaba = true;
					}
				}
				if (!estaba) {
					agregar.add(rolNuevo);
				}
			}
			return agregar.toArray(new String[0]);
		}
	}

}
