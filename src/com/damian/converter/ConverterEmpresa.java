package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelEmpresa;
import com.damian.pojo.Empresa;

@Component
public class ConverterEmpresa {

	@Autowired
	private ConverterRellenaObjeto converterRellenaObjeto;

	public Empresa convertAll(ModelEmpresa me) {

		Empresa e = convert(me);
		converterRellenaObjeto.rellenaEmpresa(e, me);

		return e;

	}

	public Empresa convert(ModelEmpresa me) {

		Empresa e = new Empresa();
		e.setIdEmp(me.getIdEmp());
		e.setNombreComercial(me.getNombreComercial());
		e.setTipoSociedad(me.getTipoSociedad());
		e.setActividad(me.getActividad());
		e.setCif(me.getCif());
		e.setEmail(me.getEmail());
		e.setPaginaWeb(me.getPaginaWeb());
		e.setTelefono(me.getTelefono());
		e.setFax(me.getFax());
		e.setObservaciones(me.getObservaciones());

		return e;

	}

	public ModelEmpresa convert(Empresa e) {

		ModelEmpresa me = new ModelEmpresa();
		me.setIdEmp(e.getIdEmp());
		me.setNombreComercial(e.getNombreComercial());
		me.setTipoSociedad(e.getTipoSociedad());
		me.setActividad(e.getActividad());
		me.setCif(e.getCif());
		me.setEmail(e.getEmail());
		me.setPaginaWeb(e.getPaginaWeb());
		me.setTelefono(e.getTelefono());
		me.setFax(e.getFax());
		me.setObservaciones(e.getObservaciones());

		return me;

	}

}
