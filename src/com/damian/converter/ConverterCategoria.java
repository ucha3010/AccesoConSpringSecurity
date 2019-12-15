package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelCategoria;
import com.damian.pojo.Categoria;

@Component
public class ConverterCategoria {

	@Autowired
	private ConverterRellenaObjeto converterRellenaObjeto;

	public Categoria convertAll(ModelCategoria mc) {

		Categoria c = convert(mc);
		converterRellenaObjeto.rellenaCategoria(c, mc);

		return c;

	}

	public Categoria convert(ModelCategoria mc) {

		Categoria c = new Categoria();
		c.setIdCat(mc.getIdCat());
		c.setNombre(mc.getNombre());

		return c;

	}

	public ModelCategoria convert(Categoria c) {

		ModelCategoria mc = new ModelCategoria();
		mc.setIdCat(c.getIdCat());
		mc.setNombre(c.getNombre());

		return mc;

	}

}
