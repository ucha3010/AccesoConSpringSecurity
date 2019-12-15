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

	public Subcategoria convertAll(ModelSubcategoria ms) {

		Subcategoria s = convert(ms);
		converterRellenaObjeto.rellenaSubcategoria(s, ms);

		return s;

	}

	public Subcategoria convert(ModelSubcategoria ms) {

		Subcategoria s = new Subcategoria();
		s.setIdSub(ms.getIdSub());
		s.setNombre(ms.getNombre());
		Categoria categoria = new Categoria();
		categoria.setIdCat(ms.getIdCat());
		s.setCategoria(categoria);

		return s;

	}

	public ModelSubcategoria convert(Subcategoria s) {

		ModelSubcategoria ms = new ModelSubcategoria();
		ms.setIdSub(s.getIdSub());
		ms.setNombre(s.getNombre());
		if (s.getCategoria() != null) {
			ms.setIdCat(s.getCategoria().getIdCat());
		}

		return ms;

	}

}
