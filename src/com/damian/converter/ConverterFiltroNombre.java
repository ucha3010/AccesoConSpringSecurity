package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelFiltroNombre;
import com.damian.pojo.FiltroNombre;
import com.damian.pojo.FiltroTitulo;

@Component
public class ConverterFiltroNombre {

	@Autowired
	private ConverterRellenaObjeto converterRellenaObjeto;

	public FiltroNombre convertAll(ModelFiltroNombre mfn) {

		FiltroNombre fn = convert(mfn);
		converterRellenaObjeto.rellenaFiltroNombre(fn, mfn);

		return fn;

	}

	public FiltroNombre convert(ModelFiltroNombre mfn) {

		FiltroNombre fn = new FiltroNombre();
		FiltroTitulo ft = new FiltroTitulo();
		ft.setIdTitulo(mfn.getIdTitulo());
		fn.setIdNombre(mfn.getIdNombre());
		fn.setNombreES(mfn.getNombreES());
		fn.setNombreEN(mfn.getNombreEN());
		fn.setNombrePT(mfn.getNombrePT());
		fn.setNombreFR(mfn.getNombreFR());
		fn.setNombreIT(mfn.getNombreIT());
		fn.setNombreGE(mfn.getNombreGE());
		fn.setNombreCA(mfn.getNombreCA());
		fn.setNombreEU(mfn.getNombreEU());

		return fn;

	}

	public ModelFiltroNombre convert(FiltroNombre fn) {

		ModelFiltroNombre mfn = new ModelFiltroNombre();
		mfn.setIdNombre(fn.getIdNombre());
		if(fn.getFiltroTitulo() != null) {
			mfn.setIdTitulo(fn.getFiltroTitulo().getIdTitulo());
		}
		mfn.setNombreES(fn.getNombreES());
		mfn.setNombreEN(fn.getNombreEN());
		mfn.setNombrePT(fn.getNombrePT());
		mfn.setNombreFR(fn.getNombreFR());
		mfn.setNombreIT(fn.getNombreIT());
		mfn.setNombreGE(fn.getNombreGE());
		mfn.setNombreCA(fn.getNombreCA());
		mfn.setNombreEU(fn.getNombreEU());

		return mfn;

	}

}
