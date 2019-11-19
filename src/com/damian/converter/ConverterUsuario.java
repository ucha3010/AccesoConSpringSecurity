package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;

import com.damian.dao.model.ModelUsuario;
import com.damian.pojo.Usuario;
import com.damian.service.DatosPersonalesService;
import com.damian.service.UsuarioEmpresaService;
import com.damian.service.UsuarioRolService;

public class ConverterUsuario {

	@Autowired
	private static DatosPersonalesService datosPersonalesService;

	@Autowired
	private static UsuarioEmpresaService usuarioEmpresaService;

	@Autowired
	private static UsuarioRolService usuarioRolService;

	public static Usuario convert(ModelUsuario mu) {

		Usuario u = new Usuario();
		u.setIdUsr(mu.getIdUsr());
		u.setUsuario(mu.getUsuario());
		u.setClave(mu.getClave());
		u.setHabilitado(mu.isHabilitado());
		u.setFechaCreacion(mu.getFechaCreacion());
		u.setDatosPersonales(datosPersonalesService.findByUsrId(mu.getIdUsr()));
		u.setUsuarioEmpresa(usuarioEmpresaService.findByIdUsr(mu.getIdUsr()));
		u.setUsuarioRol(usuarioRolService.findByIdUsr(mu.getIdUsr()));

		return u;

	}

	public static ModelUsuario convert(Usuario u) {

		ModelUsuario mu = new ModelUsuario();
		mu.setIdUsr(u.getIdUsr());
		mu.setUsuario(u.getUsuario());
		mu.setClave(u.getClave());
		mu.setHabilitado(u.isHabilitado());
		mu.setFechaCreacion(u.getFechaCreacion());

		return mu;

	}

}
