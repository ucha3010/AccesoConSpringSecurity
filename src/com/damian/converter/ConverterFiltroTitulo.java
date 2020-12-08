package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelFiltroTitulo;
import com.damian.pojo.FiltroTitulo;
import com.damian.pojo.Subcategoria;

@Component
public class ConverterFiltroTitulo {

	@Autowired
	private ConverterRellenaObjeto converterRellenaObjeto;

	public FiltroTitulo convertAll(ModelFiltroTitulo mft) {

		FiltroTitulo ft = convert(mft);
		converterRellenaObjeto.rellenaFiltroTitulo(ft, mft);

		return ft;

	}

	public FiltroTitulo convert(ModelFiltroTitulo mft) {

		FiltroTitulo ft = new FiltroTitulo();
		ft.setIdTitulo(mft.getIdTitulo());
		Subcategoria subcategoria = new Subcategoria();
		subcategoria.setIdSub(mft.getIdSub());
		ft.setSubcategoria(subcategoria);
		ft.setNombreES(mft.getNombreES());
		ft.setNombreEN(mft.getNombreEN());
		ft.setNombrePT(mft.getNombrePT());
		ft.setNombreFR(mft.getNombreFR());
		ft.setNombreIT(mft.getNombreIT());
		ft.setNombreGE(mft.getNombreGE());
		ft.setNombreCA(mft.getNombreCA());
		ft.setNombreEU(mft.getNombreEU());

		return ft;

	}

	public ModelFiltroTitulo convert(FiltroTitulo ft) {

		ModelFiltroTitulo mft = new ModelFiltroTitulo();
		mft.setIdTitulo(ft.getIdTitulo());
		mft.setIdSub(ft.getSubcategoria().getIdSub());
		mft.setNombreES(ft.getNombreES());
		mft.setNombreEN(ft.getNombreEN());
		mft.setNombrePT(ft.getNombrePT());
		mft.setNombreFR(ft.getNombreFR());
		mft.setNombreIT(ft.getNombreIT());
		mft.setNombreGE(ft.getNombreGE());
		mft.setNombreCA(ft.getNombreCA());
		mft.setNombreEU(ft.getNombreEU());

		return mft;

	}

}
