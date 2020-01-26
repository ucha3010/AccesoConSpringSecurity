package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelSubcategoria;
import com.damian.pojo.Categoria;
import com.damian.pojo.Subcategoria;

@Component
public class ConverterSubcategoria {

	@Autowired
	private ConverterRellenaObjeto converterRellenaObjeto;

	public Subcategoria convertAll(ModelSubcategoria ms, boolean cargoCategoria) {

		Subcategoria s = convert(ms);
		converterRellenaObjeto.rellenaSubcategoria(s, ms, cargoCategoria);

		return s;

	}

	public Subcategoria convert(ModelSubcategoria ms) {

		Subcategoria s = new Subcategoria();
		s.setIdSub(ms.getIdSub());
		s.setNombreES(ms.getNombreES());
		s.setNombreEN(ms.getNombreEN());
		s.setNombrePT(ms.getNombrePT());
		s.setNombreFR(ms.getNombreFR());
		s.setNombreIT(ms.getNombreIT());
		s.setNombreGE(ms.getNombreGE());
		s.setNombreCA(ms.getNombreCA());
		s.setNombreEU(ms.getNombreEU());
		Categoria categoria = new Categoria();
		categoria.setIdCat(ms.getIdCat());
		s.setCategoria(categoria);

		return s;

	}

	public ModelSubcategoria convert(Subcategoria s) {

		ModelSubcategoria ms = new ModelSubcategoria();
		ms.setIdSub(s.getIdSub());
		ms.setNombreES(s.getNombreES());
		ms.setNombreEN(s.getNombreEN());
		ms.setNombrePT(s.getNombrePT());
		ms.setNombreFR(s.getNombreFR());
		ms.setNombreIT(s.getNombreIT());
		ms.setNombreGE(s.getNombreGE());
		ms.setNombreCA(s.getNombreCA());
		ms.setNombreEU(s.getNombreEU());
		if (s.getCategoria() != null) {
			ms.setIdCat(s.getCategoria().getIdCat());
		}

		return ms;

	}

}
