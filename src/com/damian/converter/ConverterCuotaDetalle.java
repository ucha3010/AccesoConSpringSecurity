package com.damian.converter;

import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelCuotaDetalle;
import com.damian.pojo.Cuota;
import com.damian.pojo.CuotaDetalle;

@Component
public class ConverterCuotaDetalle {

	public CuotaDetalle convertAll(ModelCuotaDetalle mc) {

		CuotaDetalle c = convert(mc);

		return c;

	}

	public CuotaDetalle convert(ModelCuotaDetalle mc) {

		CuotaDetalle c = new CuotaDetalle();
		c.setIdCuDe(mc.getIdCuDe());
		Cuota cuota = new Cuota();
		cuota.setIdCuo(mc.getIdCuo());
		c.setCuota(cuota);
		c.setImporteSinInteres(mc.getImporteSinInteres());
		c.setImporteInteres(mc.getImporteInteres());
		c.setImporteCuota(mc.getImporteCuota());
		c.setFecha(mc.getFecha());
		c.setCapitalPendienteAntes(mc.getCapitalPendienteAntes());
		c.setCapitalPendienteDespues(mc.getCapitalPendienteDespues());
		c.setNumeroCuota(mc.getNumeroCuota());

		return c;

	}

	public ModelCuotaDetalle convert(CuotaDetalle c) {

		ModelCuotaDetalle mc = new ModelCuotaDetalle();
		mc.setIdCuDe(c.getIdCuDe());
		mc.setIdCuo(c.getCuota().getIdCuo());
		mc.setImporteSinInteres(c.getImporteSinInteres());
		mc.setImporteInteres(c.getImporteInteres());
		mc.setImporteCuota(c.getImporteCuota());
		mc.setFecha(c.getFecha());
		mc.setCapitalPendienteAntes(c.getCapitalPendienteAntes());
		mc.setCapitalPendienteDespues(c.getCapitalPendienteDespues());
		mc.setNumeroCuota(c.getNumeroCuota());

		return mc;

	}

}
