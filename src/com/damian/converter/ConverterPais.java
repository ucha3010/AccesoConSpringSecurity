package com.damian.converter;

import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelPais;
import com.damian.pojo.Pais;

@Component
public class ConverterPais {

	public Pais convert(ModelPais mp) {

		Pais p = new Pais();
		p.setIdPais(mp.getIdPais());
		p.setNombreES(mp.getNombreES());
		p.setNombreEN(mp.getNombreEN());

		return p;

	}

	public ModelPais convert(Pais p) {

		ModelPais mp = new ModelPais();
		mp.setIdPais(p.getIdPais());
		mp.setNombreES(p.getNombreES());
		mp.setNombreEN(p.getNombreEN());

		return mp;

	}

}
