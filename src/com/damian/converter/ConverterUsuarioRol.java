package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;

import com.damian.dao.model.ModelUsuarioRol;
import com.damian.pojo.UsuarioRol;
import com.damian.service.RolService;
import com.damian.service.UsuarioService;

public class ConverterUsuarioRol {

	@Autowired
	private static UsuarioService usuarioService;

	@Autowired
	private static RolService rolService;

	public static UsuarioRol convert(ModelUsuarioRol mur) {

		UsuarioRol ur = new UsuarioRol();
		ur.setUsuario(usuarioService.findById(mur.getIdUsr()));
		ur.setRol(rolService.findById(mur.getIdRol()));
		ur.setFechaCreacion(mur.getFechaCreacion());
		ur.setCreadoPor(mur.getCreadoPor());

		return ur;

	}

	public static ModelUsuarioRol convert(UsuarioRol ur) {

		ModelUsuarioRol mur = new ModelUsuarioRol();
		mur.setIdUsr(ur.getUsuario().getIdUsr());
		mur.setIdRol(ur.getRol().getIdRol());
		mur.setFechaCreacion(ur.getFechaCreacion());
		mur.setCreadoPor(ur.getCreadoPor());

		return mur;

	}

}
