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
import com.damian.dao.UsuarioRolDAO;
import com.damian.pojo.DatosPersonales;
import com.damian.pojo.Direccion;
import com.damian.pojo.Rol;
import com.damian.pojo.Usuario;
import com.damian.pojo.UsuarioEmpresa;
import com.damian.pojo.UsuarioRol;
import com.damian.pojo.UsuarioRolId;
import com.damian.service.DatosPersonalesService;
import com.damian.service.RolService;
import com.damian.service.UsuarioEmpresaService;
import com.damian.service.UsuarioRolService;
import com.damian.service.UsuarioService;

@Service("UsuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioRolDAO usuarioRolDAO;

	@Autowired
	private DatosPersonalesService datosPersonalesService;

	@Autowired
	private UsuarioRolService usuarioRolService;

	@Autowired
	private UsuarioEmpresaService usuarioEmpresaService;

	@Autowired
	private RolService rolService;

	public Usuario findById(int id) {
		return usuarioDAO.findById(id);
	}

	public Usuario findByUsername(String usuario) {
		return usuarioDAO.findByUsername(usuario);
	}

	public void save(Usuario usuario, String[] usuarioRol, HttpServletRequest request) {

		DatosPersonales dp;
		dp = usuario.getDatosPersonales();

		if (usuario.getIdUsr() == 0) {
			usuario.setFechaCreacion(new Timestamp(new Date().getTime()));
			usuario.setHabilitado(true);
			String claveUsr = usuario.getClave();
			usuario.setClave(passwordEncoder.encode(claveUsr));
		} else {
			DatosPersonales dpId = datosPersonalesService.findByUsrId(usuario.getIdUsr());
			dp.setIdDatosPers(dpId.getIdDatosPers());
			List<UsuarioEmpresa> usuarioEmpresas = usuarioEmpresaService.findAll();
			if (usuarioEmpresas != null) {
				List<UsuarioEmpresa> usuarioEmpresaList = new ArrayList<>();
				for (UsuarioEmpresa ue : usuarioEmpresas) {
					if (ue.getPk().getUsuario().getIdUsr() == usuario.getIdUsr()) {
						usuarioEmpresaList.add(ue);
					}
				}
				usuario.setUsuarioEmpresa(usuarioEmpresaList);
			}
		}
		if (usuarioRol != null) {
			if (usuario.getIdUsr() != 0) {
				eliminarRolesNoSeleccionados(usuarioRol, usuario);
			}
			List<UsuarioRol> roles = new ArrayList<>();
			org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
					.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
			for (String rolId : usuarioRol) {
				Rol rol = rolService.findById(Integer.parseInt(rolId));
				UsuarioRolId uri = new UsuarioRolId();
				uri.setRol(rol);
				uri.setUsuario(usuario);
				UsuarioRol ur = new UsuarioRol();
				ur.setPk(uri);
				ur.setRol(rol);
				ur.setUsuario(usuario);
				ur.setFechaCreacion(new Date());
				if(context != null) {
					ur.setCreadoPor(context.getAuthentication().getName());
				} else {
					ur.setCreadoPor("OWN USER");
				}
				roles.add(ur);
			}
			usuario.setUsuarioRol(roles);
		}
		dp.setUsuario(usuario);
		usuario.setDatosPersonales(dp);

		usuarioDAO.save(usuario);
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

	@Override
	public void saveChangePassword(Usuario usuario) {

		String claveUsr = usuario.getClave();
		usuario = findById(usuario.getIdUsr());
		usuario.setClave(passwordEncoder.encode(claveUsr));
		List<UsuarioRol> usuarioRols = usuarioRolService.findAll();
		List<UsuarioRol> usuarioRolList = new ArrayList<>();
		for (UsuarioRol ur : usuarioRols) {
			if (ur.getPk().getUsuario().getIdUsr() == usuario.getIdUsr()) {
				usuarioRolList.add(ur);
			}
		}
		usuario.setUsuarioRol(usuarioRolList);
//		DatosPersonales dp = datosPersonalesService.findByUsrId(usuario.getIdUsr());
//		dp.setUsuario(usuario);
//		usuario.setDatosPersonales(dp);

		usuarioDAO.save(usuario);

	}

	public List<Usuario> findAll() {
		return usuarioDAO.findAll();
	}

	public void update(Usuario usuario) {
		usuarioDAO.update(usuario);
	}

	public void delete(int idUsr) {
		Usuario usuario = findById(idUsr);
		usuarioDAO.delete(usuario);
	}

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

	public void fillNewUser(Usuario usuario) {
		Direccion direccion = new Direccion();
		List<Direccion> direcciones = new ArrayList<>();
		direcciones.add(direccion);
		DatosPersonales datosPersonales = new DatosPersonales();
		datosPersonales.setDirecciones(direcciones);
		usuario.setDatosPersonales(datosPersonales);
	}

	public void fillExistingUser(Usuario usuario) {
		// chapuzas hasta que sepa buscar roles y empresas sólo por idUsr
		List<UsuarioRol> usuarioRolTodos = usuarioRolDAO.findAll();
		List<UsuarioRol> usuarioRoles = new ArrayList<UsuarioRol>();
		for (UsuarioRol ur : usuarioRolTodos) {
			if (ur.getPk().getUsuario().getIdUsr() == usuario.getIdUsr()) {
				usuarioRoles.add(ur);
			}
		}
		usuario.setUsuarioRol(usuarioRoles);
		List<UsuarioEmpresa> usuarioEmpresaTodos = usuarioEmpresaService.findAll();
		List<UsuarioEmpresa> usuarioEmpresas = new ArrayList<UsuarioEmpresa>();
		for (UsuarioEmpresa ue : usuarioEmpresaTodos) {
			if (ue.getPk().getUsuario().getIdUsr() == usuario.getIdUsr()) {
				usuarioEmpresas.add(ue);
			}
		}
		usuario.setUsuarioEmpresa(usuarioEmpresas);
	}

	@Override
	public Usuario reset(int idUsr) {
		Usuario usuario = findById(idUsr);
		usuario.setClave(passwordEncoder.encode("Superman1"));		
		return usuario;
	}

}
