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
		p.setNombrePT(mp.getNombrePT());
		p.setNombreFR(mp.getNombreFR());
		p.setNombreIT(mp.getNombreIT());
		p.setNombreGE(mp.getNombreGE());
		p.setNombreCA(mp.getNombreCA());
		p.setNombreEU(mp.getNombreEU());

		return p;

	}

	public ModelPais convert(Pais p) {

		ModelPais mp = new ModelPais();
		mp.setIdPais(p.getIdPais());
		mp.setNombreES(p.getNombreES());
		mp.setNombreEN(p.getNombreEN());
		mp.setNombrePT(p.getNombrePT());
		mp.setNombreFR(p.getNombreFR());
		mp.setNombreIT(p.getNombreIT());
		mp.setNombreGE(p.getNombreGE());
		mp.setNombreCA(p.getNombreCA());
		mp.setNombreEU(p.getNombreEU());

		return mp;

	}

}
