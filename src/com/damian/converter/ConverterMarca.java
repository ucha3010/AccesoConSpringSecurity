package com.damian.converter;

import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelMarca;
import com.damian.pojo.Marca;

@Component
public class ConverterMarca {

	public Marca convertAll(ModelMarca mr) {

		Marca r = convert(mr);

		return r;

	}

	public Marca convert(ModelMarca mr) {

		Marca r = new Marca();
		r.setIdMar(mr.getIdMar());
		r.setNombre(mr.getNombre());

		return r;

	}

	public ModelMarca convert(Marca r) {

		ModelMarca mr = new ModelMarca();
		mr.setIdMar(r.getIdMar());
		mr.setNombre(r.getNombre());

		return mr;

	}

}
