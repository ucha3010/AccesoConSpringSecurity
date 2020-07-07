package com.damian.converter;

import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelFacturaEnviarFacturar;
import com.damian.pojo.Factura;
import com.damian.pojo.FacturaEnviarFacturar;

@Component
public class ConverterFacturaEnviarFacturar {

	public FacturaEnviarFacturar convert(ModelFacturaEnviarFacturar mfef) {

		FacturaEnviarFacturar fef = new FacturaEnviarFacturar();

		fef.setIdEnFa(mfef.getIdEnFa());
		fef.setNombre(mfef.getNombre());
		fef.setDireccion(mfef.getDireccion());
		fef.setCp(mfef.getCp());
		fef.setCiudad(mfef.getCiudad());
		fef.setProvincia(mfef.getProvincia());
		fef.setPais(mfef.getPais());
		fef.setTelefono(mfef.getTelefono());
		fef.setFacturar(mfef.isFacturar());
		fef.setEnviar(mfef.isEnviar());
		Factura factura = new Factura();
		factura.setIdFac(mfef.getIdFac());
		fef.setFactura(factura);

		return fef;

	}

	public ModelFacturaEnviarFacturar convert(FacturaEnviarFacturar fef) {

		ModelFacturaEnviarFacturar mfef = new ModelFacturaEnviarFacturar();

		mfef.setIdEnFa(fef.getIdEnFa());
		mfef.setNombre(fef.getNombre());
		mfef.setDireccion(fef.getDireccion());
		mfef.setCp(fef.getCp());
		mfef.setCiudad(fef.getCiudad());
		mfef.setProvincia(fef.getProvincia());
		mfef.setPais(fef.getPais());
		mfef.setTelefono(fef.getTelefono());
		mfef.setFacturar(fef.isFacturar());
		mfef.setEnviar(fef.isEnviar());
		mfef.setIdFac(fef.getFactura().getIdFac());

		return mfef;

	}

}
