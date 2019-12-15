package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelEstado;
import com.damian.pojo.Estado;

@Component
public class ConverterEstado {

	@Autowired
	private ConverterRellenaObjeto converterRellenaObjeto;

	public Estado convertAll(ModelEstado me) {

		Estado e = convert(me);
		converterRellenaObjeto.rellenaEstado(e, me);

		return e;

	}

	public Estado convert(ModelEstado me) {

		Estado e = new Estado();
		e.setIdEst(me.getIdEst());
		e.setNombre(me.getNombre());

		return e;

	}

	public ModelEstado convert(Estado e) {

		ModelEstado me = new ModelEstado();
		me.setIdEst(e.getIdEst());
		me.setNombre(e.getNombre());

		return me;

	}

}
