package com.damian.converter;

import com.damian.dao.model.ModelPais;
import com.damian.pojo.Pais;

public class ConverterPais {

	public static Pais convert(ModelPais mp) {

		Pais p = new Pais();
		p.setIdPais(mp.getIdPais());
		p.setNombreES(mp.getNombreES());
		p.setNombreEN(mp.getNombreEN());

		return p;

	}

	public static ModelPais convert(Pais p) {

		ModelPais mp = new ModelPais();
		mp.setIdPais(p.getIdPais());
		mp.setNombreES(p.getNombreES());
		mp.setNombreEN(p.getNombreEN());

		return mp;

	}

}
