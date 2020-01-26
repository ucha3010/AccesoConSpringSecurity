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
		e.setNombreES(me.getNombreES());
		e.setNombreEN(me.getNombreEN());
		e.setNombrePT(me.getNombrePT());
		e.setNombreFR(me.getNombreFR());
		e.setNombreIT(me.getNombreIT());
		e.setNombreGE(me.getNombreGE());
		e.setNombreCA(me.getNombreCA());
		e.setNombreEU(me.getNombreEU());

		return e;

	}

	public ModelEstado convert(Estado e) {

		ModelEstado me = new ModelEstado();
		me.setIdEst(e.getIdEst());
		me.setNombreES(e.getNombreES());
		me.setNombreEN(e.getNombreEN());
		me.setNombrePT(e.getNombrePT());
		me.setNombreFR(e.getNombreFR());
		me.setNombreIT(e.getNombreIT());
		me.setNombreGE(e.getNombreGE());
		me.setNombreCA(e.getNombreCA());
		me.setNombreEU(e.getNombreEU());

		return me;

	}

}
