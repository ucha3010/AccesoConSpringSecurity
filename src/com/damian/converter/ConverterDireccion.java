package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelDireccion;
import com.damian.pojo.DatosPersonales;
import com.damian.pojo.Direccion;

@Component
public class ConverterDireccion {

	@Autowired
	private ConverterRellenaObjeto converterRellenaObjeto;

	public Direccion convertAll(ModelDireccion md) {

		Direccion d = convert(md);
		converterRellenaObjeto.rellenaDireccion(d, md);

		return d;

	}

	public Direccion convert(ModelDireccion md) {

		Direccion d = new Direccion();
		d.setIdDir(md.getIdDir());
		d.setTipoVia(md.getTipoVia());
		d.setNombreVia(md.getNombreVia());
		d.setNumero(md.getNumero());
		d.setResto(md.getResto());
		d.setCp(md.getCp());
		d.setProvincia(md.getProvincia());
		d.setCiudad(md.getCiudad());
		d.setPais(md.getPais());
		DatosPersonales datosPersonales = new DatosPersonales();
		datosPersonales.setIdDatosPers(md.getIdDatosPers());
		d.setDatosPersonales(datosPersonales);

		return d;

	}

	public ModelDireccion convert(Direccion d) {

		ModelDireccion md = new ModelDireccion();
		md.setIdDir(d.getIdDir());
		md.setTipoVia(d.getTipoVia());
		md.setNombreVia(d.getNombreVia());
		md.setNumero(d.getNumero());
		md.setResto(d.getResto());
		md.setCp(d.getCp());
		md.setProvincia(d.getProvincia());
		md.setCiudad(d.getCiudad());
		md.setPais(d.getPais());
		md.setIdDatosPers(d.getDatosPersonales().getIdDatosPers());

		return md;

	}
}
