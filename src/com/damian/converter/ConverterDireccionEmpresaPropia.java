package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.PaisDAO;
import com.damian.dao.model.ModelDireccionEmpresaPropia;
import com.damian.pojo.DireccionEmpresaPropia;
import com.damian.pojo.EmpresaPropia;

@Component
public class ConverterDireccionEmpresaPropia {

	@Autowired
	private ConverterRellenaObjeto converterRellenaObjeto;

	@Autowired
	private PaisDAO paisDAO;

	public DireccionEmpresaPropia convertAll(ModelDireccionEmpresaPropia mde) {

		DireccionEmpresaPropia de = convert(mde);
		converterRellenaObjeto.rellenaDireccionEmpresaPropia(de, mde);

		return de;

	}

	public DireccionEmpresaPropia convert(ModelDireccionEmpresaPropia mde) {

		DireccionEmpresaPropia de = new DireccionEmpresaPropia();
		de.setIdDirPropia(mde.getIdDirPropia());
		de.setTipoVia(mde.getTipoVia());
		de.setNombreVia(mde.getNombreVia());
		de.setNumero(mde.getNumero());
		de.setResto(mde.getResto());
		de.setCp(mde.getCp());
		de.setProvincia(mde.getProvincia());
		de.setCiudad(mde.getCiudad());
		de.setPais(paisDAO.findById(mde.getPais_idPais()));
		EmpresaPropia empresaPropia = new EmpresaPropia();
		empresaPropia.setIdPropia(mde.getIdPropia());
		de.setEmpresaPropia(empresaPropia);
		de.setModificadoPor(mde.getModificadoPor());
		de.setFechaModificacion(mde.getFechaModificacion());

		return de;

	}

	public ModelDireccionEmpresaPropia convert(DireccionEmpresaPropia de) {

		ModelDireccionEmpresaPropia mde = new ModelDireccionEmpresaPropia();
		mde.setIdDirPropia(de.getIdDirPropia());
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
		if (de.getEmpresaPropia() != null) {
			mde.setIdPropia(de.getEmpresaPropia().getIdPropia());
		}
		mde.setModificadoPor(de.getModificadoPor());
		mde.setFechaModificacion(de.getFechaModificacion());

		return mde;

	}

}
