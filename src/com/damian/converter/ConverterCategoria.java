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
		c.setNombreES(mc.getNombreES());
		c.setNombreEN(mc.getNombreEN());
		c.setNombrePT(mc.getNombrePT());
		c.setNombreFR(mc.getNombreFR());
		c.setNombreIT(mc.getNombreIT());
		c.setNombreGE(mc.getNombreGE());
		c.setNombreCA(mc.getNombreCA());
		c.setNombreEU(mc.getNombreEU());
		c.setModificadoPor(mc.getModificadoPor());
		c.setFechaModificacion(mc.getFechaModificacion());

		return c;

	}

	public ModelCategoria convert(Categoria c) {

		ModelCategoria mc = new ModelCategoria();
		mc.setIdCat(c.getIdCat());
		mc.setNombreES(c.getNombreES());
		mc.setNombreEN(c.getNombreEN());
		mc.setNombrePT(c.getNombrePT());
		mc.setNombreFR(c.getNombreFR());
		mc.setNombreIT(c.getNombreIT());
		mc.setNombreGE(c.getNombreGE());
		mc.setNombreCA(c.getNombreCA());
		mc.setNombreEU(c.getNombreEU());
		mc.setModificadoPor(c.getModificadoPor());
		mc.setFechaModificacion(c.getFechaModificacion());

		return mc;

	}

}
