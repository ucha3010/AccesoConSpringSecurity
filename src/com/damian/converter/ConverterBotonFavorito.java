package com.damian.converter;

import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelBotonFavorito;
import com.damian.pojo.BotonFavorito;

@Component
public class ConverterBotonFavorito {

	public BotonFavorito convertAll(ModelBotonFavorito mb) {

		BotonFavorito b = convert(mb);

		return b;

	}

	public BotonFavorito convert(ModelBotonFavorito mb) {

		BotonFavorito b = new BotonFavorito();
		b.setIdBot(mb.getIdBot());
		b.setNombre(mb.getNombre());

		return b;

	}

	public ModelBotonFavorito convert(BotonFavorito b) {

		ModelBotonFavorito mb = new ModelBotonFavorito();
		mb.setIdBot(b.getIdBot());
		mb.setNombre(b.getNombre());

		return mb;

	}

}
