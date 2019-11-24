package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelRol;
import com.damian.pojo.Rol;

@Component
public class ConverterRol {

	@Autowired
	private ConverterRellenaObjeto converterRellenaObjeto;

	public Rol convertAll(ModelRol mr) {

		Rol r = convert(mr);
		converterRellenaObjeto.rellenaRol(r, mr);

		return r;

	}

	public Rol convert(ModelRol mr) {

		Rol r = new Rol();
		r.setIdRol(mr.getIdRol());
		r.setRol(mr.getRol());

		return r;

	}

	public ModelRol convert(Rol r) {

		ModelRol mr = new ModelRol();
		mr.setIdRol(r.getIdRol());
		mr.setRol(r.getRol());

		return mr;

	}

}
