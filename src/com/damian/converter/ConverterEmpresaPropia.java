package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelEmpresaPropia;
import com.damian.pojo.DireccionEmpresa;
import com.damian.pojo.EmpresaPropia;

@Component
public class ConverterEmpresaPropia {

	@Autowired
	private ConverterRellenaObjeto converterRellenaObjeto;

	public EmpresaPropia convertAll(ModelEmpresaPropia me) {

		EmpresaPropia e = convert(me);
		converterRellenaObjeto.rellenaEmpresaPropia(e, me);

		return e;

	}

	public EmpresaPropia convert(ModelEmpresaPropia me) {

		EmpresaPropia e = new EmpresaPropia();
		e.setIdPropia(me.getIdPropia());
		e.setRazonSocial(me.getRazonSocial());
		e.setCif(me.getCif());
		e.setTelefono(me.getTelefono());
		e.setFax(me.getFax());
		e.setEmail(me.getEmail());
		e.setFacturacion(me.isFacturacion());
		DireccionEmpresa direccionEmpresa = new DireccionEmpresa();
		direccionEmpresa.setIdDirEmp(me.getIdDirEmp());
		e.setDireccionEmpresa(direccionEmpresa);

		return e;

	}

	public ModelEmpresaPropia convert(EmpresaPropia e) {

		ModelEmpresaPropia me = new ModelEmpresaPropia();
		me.setIdPropia(e.getIdPropia());
		me.setRazonSocial(e.getRazonSocial());
		me.setCif(e.getCif());
		me.setTelefono(e.getTelefono());
		me.setFax(e.getFax());
		me.setEmail(e.getEmail());
		me.setFacturacion(e.isFacturacion());
		if (e.getDireccionEmpresa() != null) {
			me.setIdDirEmp(e.getDireccionEmpresa().getIdDirEmp());
		} else {
			me.setIdDirEmp(0);
		}

		return me;

	}

}
