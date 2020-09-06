package com.damian.converter;

import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelConstantes;
import com.damian.pojo.Constantes;

@Component
public class ConverterConstantes {

	public Constantes convertAll(ModelConstantes mc) {

		Constantes c = convert(mc);

		return c;

	}

	public Constantes convert(ModelConstantes mc) {

		Constantes c = new Constantes();
		c.setClave(mc.getClave());
		c.setValorDouble(mc.getValorDouble());
		c.setValorString100(mc.getValorString100());
		c.setValorText(mc.getValorText());
		c.setModificadoPor(mc.getModificadoPor());
		c.setFechaModificacion(mc.getFechaModificacion());

		return c;

	}

	public ModelConstantes convert(Constantes c) {

		ModelConstantes mc = new ModelConstantes();
		mc.setClave(c.getClave());
		mc.setValorDouble(c.getValorDouble());
		mc.setValorString100(c.getValorString100());
		mc.setValorText(c.getValorText());
		mc.setModificadoPor(c.getModificadoPor());
		mc.setFechaModificacion(c.getFechaModificacion());

		return mc;

	}

}
