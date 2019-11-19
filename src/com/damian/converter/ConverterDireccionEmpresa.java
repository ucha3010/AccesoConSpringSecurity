package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;

import com.damian.dao.model.ModelDireccionEmpresa;
import com.damian.pojo.DireccionEmpresa;
import com.damian.service.EmpresaService;

public class ConverterDireccionEmpresa {

	@Autowired
	private static EmpresaService empresaService;

	public static DireccionEmpresa convert(ModelDireccionEmpresa mde) {

		DireccionEmpresa de = new DireccionEmpresa();
		de.setIdDirEmp(mde.getIdDirEmp());
		de.setTipoVia(mde.getTipoVia());
		de.setNombreVia(mde.getNombreVia());
		de.setNumero(mde.getNumero());
		de.setResto(mde.getResto());
		de.setCp(mde.getCp());
		de.setProvincia(mde.getProvincia());
		de.setCiudad(mde.getCiudad());
		de.setPais(mde.getPais());
		de.setEmpresa(empresaService.findById(mde.getIdEmp()));

		return de;

	}

	public static ModelDireccionEmpresa convert(DireccionEmpresa de) {

		ModelDireccionEmpresa mde = new ModelDireccionEmpresa();
		mde.setIdDirEmp(de.getIdDirEmp());
		mde.setTipoVia(de.getTipoVia());
		mde.setNombreVia(de.getNombreVia());
		mde.setNumero(de.getNumero());
		mde.setResto(de.getResto());
		mde.setCp(de.getCp());
		mde.setProvincia(de.getProvincia());
		mde.setCiudad(de.getCiudad());
		mde.setPais(de.getPais());
		mde.setIdEmp(de.getEmpresa().getIdEmp());

		return mde;

	}

}
