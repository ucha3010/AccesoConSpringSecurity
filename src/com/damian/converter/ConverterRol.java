package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;

import com.damian.dao.model.ModelRol;
import com.damian.pojo.Rol;
import com.damian.service.UsuarioRolService;

public class ConverterRol {

	@Autowired
	private static UsuarioRolService usuarioRolService;

	public static Rol convert(ModelRol mr) {

		Rol r = new Rol();
		r.setIdRol(mr.getIdRol());
		r.setRol(mr.getRol());
		r.setUsuarioRol(usuarioRolService.findByIdRol(mr.getIdRol()));

		return r;

	}

	public static ModelRol convert(Rol r) {

		ModelRol mr = new ModelRol();
		mr.setIdRol(r.getIdRol());
		mr.setRol(r.getRol());

		return mr;

	}

}
