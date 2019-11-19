package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;

import com.damian.dao.model.ModelEmpresa;
import com.damian.pojo.Empresa;
import com.damian.service.DireccionEmpresaService;
import com.damian.service.UsuarioEmpresaService;

public class ConverterEmpresa {

	@Autowired
	private static DireccionEmpresaService direccionEmpresaService;

	@Autowired
	private static UsuarioEmpresaService usuarioEmpresaService;

	public static Empresa convert(ModelEmpresa me) {

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
		e.setDireccionesEmpresa(direccionEmpresaService.findListFromEmpresa(me.getIdEmp()));
		e.setUsuarioEmpresa(usuarioEmpresaService.findByIdEmp(me.getIdEmp()));

		return e;

	}

	public static ModelEmpresa convert(Empresa e) {

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
