package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.damian.dao.RolDAO;
import com.damian.dao.UsuarioDAO;
import com.damian.pojo.DatosPersonales;
import com.damian.pojo.Direccion;
import com.damian.pojo.Rol;
import com.damian.pojo.Usuario;
import com.damian.pojo.UsuarioEmpresa;
import com.damian.pojo.UsuarioRol;
import com.damian.pojo.UsuarioRolId;
import com.damian.service.DatosPersonalesService;
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
	private RolDAO rolDAO;

	@Autowired
	private DatosPersonalesService datosPersonalesService;
	
	@Autowired
	private UsuarioRolService usuarioRolService;
	
	@Autowired
	private UsuarioEmpresaService usuarioEmpresaService;
	
	public Usuario findById(int id) {
		return usuarioDAO.findById(id);
	}
	
	public Usuario findByUsername(String usuario) {
		return usuarioDAO.findByUsername(usuario);
	}

	public void save(Usuario usuario) {
		
		DatosPersonales dp;
		dp = usuario.getDatosPersonales();		
		
		if(usuario.getIdUsr() == 0) {
			usuario.setFechaCreacion(new Timestamp(new Date().getTime()));
			usuario.setHabilitado(true);
			String claveUsr = usuario.getClave();
			usuario.setClave(passwordEncoder.encode(claveUsr));
			List<UsuarioRol> usuarioRoles = new ArrayList<>();
			UsuarioRol usuarioRol = new UsuarioRol();
			UsuarioRolId pk = new UsuarioRolId();
			List<Rol> roles = rolDAO.findByRolName("ROL_CLIENTE");
			Rol rol = roles.get(0);
			pk.setRol(rol);
			pk.setUsuario(usuario);
			usuarioRol.setPk(pk);
			usuarioRol.setFechaCreacion(new Date());
			usuarioRol.setCreadoPor("DAMIAN");
			usuarioRoles.add(usuarioRol);
			usuario.setUsuarioRoles(usuarioRoles);			
		} else {
			DatosPersonales dpId = datosPersonalesService.findByUsrId(usuario.getIdUsr());
			dp.setIdDatosPers(dpId.getIdDatosPers());
			List<UsuarioRol> usuarioRols = usuarioRolService.findAll();
			List<UsuarioRol> usuarioRolList = new ArrayList<>();
			for(UsuarioRol ur:usuarioRols) {
				if(ur.getPk().getUsuario().getIdUsr() == usuario.getIdUsr()) {
					usuarioRolList.add(ur);
				}
			}
			usuario.setUsuarioRoles(usuarioRolList);
			List<UsuarioEmpresa> usuarioEmpresas = usuarioEmpresaService.findAll();
			if(usuarioEmpresas != null) {
				List<UsuarioEmpresa> usuarioEmpresaList = new ArrayList<>();
				for(UsuarioEmpresa ue:usuarioEmpresas) {
					if(ue.getPk().getUsuario().getIdUsr() == usuario.getIdUsr()) {
						usuarioEmpresaList.add(ue);
					}
				}
				usuario.setUsuarioEmpresa(usuarioEmpresaList);
			}
		}
		dp.setUsuario(usuario);
		usuario.setDatosPersonales(dp);
		
		usuarioDAO.save(usuario);
	}
	
	public void saveChangePassword(Usuario usuario) {

		String claveUsr = usuario.getClave();
		usuario.setClave(passwordEncoder.encode(claveUsr));
		DatosPersonales dp;
		dp = usuario.getDatosPersonales();
		DatosPersonales dpId = datosPersonalesService.findByUsrId(usuario.getIdUsr());
		dp.setIdDatosPers(dpId.getIdDatosPers());
		List<UsuarioRol> usuarioRols = usuarioRolService.findAll();
		List<UsuarioRol> usuarioRolList = new ArrayList<>();
		for(UsuarioRol ur:usuarioRols) {
			if(ur.getPk().getUsuario().getIdUsr() == usuario.getIdUsr()) {
				usuarioRolList.add(ur);
			}
		}
		usuario.setUsuarioRoles(usuarioRolList);
		dp.setUsuario(usuario);
		usuario.setDatosPersonales(dp);
		
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
		for(Usuario usuario : usuarios) {
			List<UsuarioRol> roles = usuario.getUsuarioRoles();
			for(UsuarioRol rol: roles) {
				if(rol.getRol().getRol().equalsIgnoreCase("ROL_CLIENTE")) {
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

}
