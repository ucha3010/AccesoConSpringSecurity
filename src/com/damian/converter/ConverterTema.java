package com.damian.converter;

import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelTema;
import com.damian.pojo.Tema;

@Component
public class ConverterTema {

	public Tema convertAll(ModelTema mc) {

		Tema c = convert(mc);

		return c;

	}

	public Tema convert(ModelTema mt) {

		Tema t = new Tema();
		t.setIdTem(mt.getIdTem());
		t.setNombre(mt.getNombre());

		return t;

	}

	public ModelTema convert(Tema t) {

		ModelTema mt = new ModelTema();
		mt.setIdTem(t.getIdTem());
		mt.setNombre(t.getNombre());

		return mt;

	}

}
