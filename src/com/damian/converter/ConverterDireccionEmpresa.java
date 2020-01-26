package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.PaisDAO;
import com.damian.dao.model.ModelDireccionEmpresa;
import com.damian.pojo.DireccionEmpresa;
import com.damian.pojo.Empresa;

@Component
public class ConverterDireccionEmpresa {

	@Autowired
	private ConverterRellenaObjeto converterRellenaObjeto;

	@Autowired
	private PaisDAO paisDAO;

	public DireccionEmpresa convertAll(ModelDireccionEmpresa mde) {

		DireccionEmpresa de = convert(mde);
		converterRellenaObjeto.rellenaDireccionEmpresa(de, mde);

		return de;

	}

	public DireccionEmpresa convert(ModelDireccionEmpresa mde) {

		DireccionEmpresa de = new DireccionEmpresa();
		de.setIdDirEmp(mde.getIdDirEmp());
		de.setTipoVia(mde.getTipoVia());
		de.setNombreVia(mde.getNombreVia());
		de.setNumero(mde.getNumero());
		de.setResto(mde.getResto());
		de.setCp(mde.getCp());
		de.setProvincia(mde.getProvincia());
		de.setCiudad(mde.getCiudad());
		de.setPais(paisDAO.findById(mde.getPais_idPais()));
		Empresa empresa = new Empresa();
		empresa.setIdEmp(mde.getIdEmp());
		de.setEmpresa(empresa);

		return de;

	}

	public ModelDireccionEmpresa convert(DireccionEmpresa de) {

		ModelDireccionEmpresa mde = new ModelDireccionEmpresa();
		mde.setIdDirEmp(de.getIdDirEmp());
		mde.setTipoVia(de.getTipoVia());
		mde.setNombreVia(de.getNombreVia());
		mde.setNumero(de.getNumero());
		mde.setResto(de.getResto());
		mde.setCp(de.getCp());
		mde.setProvincia(de.getProvincia());
		mde.setCiudad(de.getCiudad());
		if (de.getPais() != null) {
			mde.setPais_idPais(de.getPais().getIdPais());
		}
		mde.setIdEmp(de.getEmpresa().getIdEmp());

		return mde;

	}

}
