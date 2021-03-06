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
import com.damian.dao.UsuarioOrdenDAO;
import com.damian.dao.model.ModelUsuarioOrden;
import com.damian.exceptions.RepeatedUsernameException;
import com.damian.pojo.DatosPersonales;
import com.damian.pojo.Direccion;
import com.damian.pojo.Pais;
import com.damian.pojo.PreferenciaUsuario;
import com.damian.pojo.Rol;
import com.damian.pojo.Usuario;
import com.damian.pojo.UsuarioEmpresa;
import com.damian.pojo.UsuarioRol;
import com.damian.service.DatosPersonalesService;
import com.damian.service.DireccionService;
import com.damian.service.PreferenciaUsuarioService;
import com.damian.service.UsuarioEmpresaService;
import com.damian.service.UsuarioRolService;
import com.damian.service.UsuarioService;
import com.damian.utils.Utils;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private DatosPersonalesService datosPersonalesService;

	@Autowired
	private DireccionService direccionService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private PreferenciaUsuarioService preferenciaUsuarioService;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private UsuarioEmpresaService usuarioEmpresaService;

	@Autowired
	private UsuarioOrdenDAO usuarioOrdenDAO;

	@Autowired
	private UsuarioRolService usuarioRolService;


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
			fillModificadoPor(usuario, request);
			usuarioDAO.save(usuario, request);
			usuario = findByUsername(usuario.getUsuario());
			dp.setUsuario(usuario);
			datosPersonalesService.save(dp, request);
			PreferenciaUsuario preferenciaUsuario = new PreferenciaUsuario();
			preferenciaUsuario.setIdPrefUsr(usuario.getIdUsr());
			preferenciaUsuarioService.save(preferenciaUsuario, request);
		} else {
			DatosPersonales dpId = datosPersonalesService.findByUsrId(usuario.getIdUsr());
			dp.setIdDatosPers(dpId.getIdDatosPers());
			dp.setUsuario(usuario);
			datosPersonalesService.update(dp, request);
		}
		if (usuarioRol != null) {
			eliminarRolesNoSeleccionados(usuarioRol, usuario, request);
			String[] nuevosRoles = rolesAGuardar(usuarioRol, usuario);
			Date ahora = new Date();
			UsuarioRol ur = null;
			String creador = Utils.getLoggedUser(request);
			for (String rolId : nuevosRoles) {
				Rol rol = new Rol();
				rol.setIdRol(Integer.parseInt(rolId));
				ur = new UsuarioRol();
				ur.setRol(rol);
				ur.setUsuario(usuario);
				ur.setFechaCreacion(ahora);
				ur.setCreadoPor(creador);
				usuarioRolService.save(ur, request);
			}
		}
	}

	@Override
	public void saveChangePassword(Usuario usuario, HttpServletRequest request) {

		String claveUsr = usuario.getClave();
		usuario = findById(usuario.getIdUsr());
		usuario.setClave(passwordEncoder.encode(claveUsr));
		update(usuario, request);

	}

	@Override
	public List<Usuario> findAll(String column, String order, int paginaInicio, int totalPaginas,
			HttpServletRequest request) {
		return usuarioDAO.findAll(false, column, order, paginaInicio, totalPaginas, request);
	}

	@Override
	public void update(Usuario usuario, HttpServletRequest request) {
		fillModificadoPor(usuario, request);
		usuarioDAO.update(usuario, request);
	}

	@Override
	public void delete(int idUsr, HttpServletRequest request) {
		List<Direccion> direccionList = direccionService.findListFromUsuario(idUsr);
		if (direccionList != null) {
			for (Direccion direccion : direccionList) {
				direccionService.delete(direccion.getIdDir(), request);
			}
		}
		DatosPersonales datosPersonales = datosPersonalesService.findByUsrId(idUsr);
		datosPersonalesService.delete(datosPersonales.getIdDatosPers(), request);
		List<UsuarioEmpresa> ueList = usuarioEmpresaService.findByIdUsr(idUsr);
		if (ueList != null) {
			for (UsuarioEmpresa ue : ueList) {
				usuarioEmpresaService.delete(idUsr, ue.getEmpresa().getIdEmp(), request);
			}
		}
		List<UsuarioRol> urList = usuarioRolService.findByIdUsr(idUsr);
		if (urList != null) {
			for (UsuarioRol ur : urList) {
				usuarioRolService.delete(idUsr, ur.getRol().getIdRol(), request);
			}
		}
		usuarioDAO.delete(idUsr, request);
	}

	@Override
	public List<Usuario> findCustomers(String column, String order, int paginaInicio, int totalPaginas,
			HttpServletRequest request) {
		List<Usuario> usuarios = usuarioDAO.findAll(true, column, order, paginaInicio, totalPaginas, request);
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
		Pais nacionalidad = new Pais();
		direcciones.add(direccion);
		DatosPersonales datosPersonales = new DatosPersonales();
		datosPersonales.setDirecciones(direcciones);
		datosPersonales.setNacionalidad(nacionalidad);
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
	public Usuario reset(int idUsr, HttpServletRequest request) {
		Usuario usuario = findById(idUsr);
		usuario.setClave(passwordEncoder.encode("Superman1"));
		update(usuario, request);
		return usuario;
	}

	@Override
	public String getColumn(HttpServletRequest request) {
		Usuario usuario = Utils.getLoggedUser(request, usuarioDAO);
		ModelUsuarioOrden uo = null;
		if (usuario != null) {
			uo = usuarioOrdenDAO.findByIdUsrTabla(usuario.getIdUsr(), "usuario");
		}
		if (uo != null) {
			return uo.getColumna() + "/" + uo.getOrden();
		} else {
			return "null/null";
		}
	}

	@Override
	public List<Usuario> findSearchAll(boolean customer) {
		return usuarioDAO.findSearchAll(customer);
	}

	@Override
	public int countCustomers() {
		return usuarioDAO.countCustomers();
	}

	@Override
	public List<Usuario> findByPublicity(boolean receive) {
		List<PreferenciaUsuario> preferenciaUsuarioList = preferenciaUsuarioService.findByPublicity(receive);
		List<Usuario> usuarioList = new ArrayList<>();
		Usuario usuario = null;
		for(PreferenciaUsuario pu: preferenciaUsuarioList) {
			usuario = findById(pu.getIdPrefUsr());
			usuarioList.add(usuario);
		}
		Utils.ordenarPorPrimerApellido(usuarioList);
		return usuarioList;
	}

	private void eliminarRolesNoSeleccionados(String[] usuarioRol, Usuario usuario, HttpServletRequest request) {
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
				usuarioRolService.delete(usuario.getIdUsr(), idRol, request);
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

	private void fillModificadoPor(Usuario usuario, HttpServletRequest request) {

		usuario.setModificadoPor(Utils.getLoggedUser(request));
		usuario.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

	}

}
