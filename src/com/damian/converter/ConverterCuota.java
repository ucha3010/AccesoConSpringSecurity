package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelCuota;
import com.damian.pojo.Cuota;

@Component
public class ConverterCuota {

	@Autowired
	private ConverterRellenaObjeto converterRellenaObjeto;

	public Cuota convertAll(ModelCuota mc) {

		Cuota c = convert(mc);
		converterRellenaObjeto.rellenaCuota(c, mc);

		return c;

	}

	public Cuota convert(ModelCuota mc) {

		Cuota c = new Cuota();
		c.setIdCuo(mc.getIdCuo());
		c.setCantidadCuotas(mc.getCantidadCuotas());
		c.setComisionAperturaPor(mc.getComisionAperturaPor());
		c.setComisionAperturaImp(mc.getComisionAperturaImp());
		c.setInteresPor(mc.getInteresPor());
		c.setInteresImp(mc.getInteresImp());

		return c;

	}

	public ModelCuota convert(Cuota c) {

		ModelCuota mc = new ModelCuota();
		mc.setIdCuo(c.getIdCuo());
		mc.setCantidadCuotas(c.getCantidadCuotas());
		mc.setComisionAperturaPor(c.getComisionAperturaPor());
		mc.setComisionAperturaImp(c.getComisionAperturaImp());
		mc.setInteresPor(c.getInteresPor());
		mc.setInteresImp(c.getInteresImp());

		return mc;

	}

}
