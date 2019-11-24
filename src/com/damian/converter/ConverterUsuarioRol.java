package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.DatosPersonalesDAO;
import com.damian.dao.RolDAO;
import com.damian.dao.UsuarioDAO;
import com.damian.dao.model.ModelUsuarioRol;
import com.damian.pojo.Usuario;
import com.damian.pojo.UsuarioRol;

@Component
public class ConverterUsuarioRol {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private RolDAO rolDAO;

	@Autowired
	private DatosPersonalesDAO datosPersonalesDAO;

	public UsuarioRol convertAll(ModelUsuarioRol mur) {

		UsuarioRol ur = convert(mur);
		Usuario u = usuarioDAO.findByIdModel(mur.getIdUsr());
		u.setDatosPersonales(datosPersonalesDAO.findByUsrIdModel(u.getIdUsr()));
		ur.setUsuario(u);
		ur.setRol(rolDAO.findByIdModel(mur.getIdRol()));

		return ur;

	}

	public UsuarioRol convert(ModelUsuarioRol mur) {

		UsuarioRol ur = new UsuarioRol();
		ur.setFechaCreacion(mur.getFechaCreacion());
		ur.setCreadoPor(mur.getCreadoPor());

		return ur;

	}

	public ModelUsuarioRol convert(UsuarioRol ur) {

		ModelUsuarioRol mur = new ModelUsuarioRol();
		mur.setIdUsr(ur.getUsuario().getIdUsr());
		mur.setIdRol(ur.getRol().getIdRol());
		mur.setFechaCreacion(ur.getFechaCreacion());
		mur.setCreadoPor(ur.getCreadoPor());

		return mur;

	}

}
