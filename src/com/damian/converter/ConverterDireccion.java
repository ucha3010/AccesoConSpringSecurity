package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;

import com.damian.dao.model.ModelDireccion;
import com.damian.pojo.Direccion;
import com.damian.service.DatosPersonalesService;

public class ConverterDireccion {

	@Autowired
	private static DatosPersonalesService datosPersonalesService;

	public static Direccion convert(ModelDireccion md) {

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
		d.setDatosPersonales(datosPersonalesService.findById(md.getIdDatosPers()));

		return d;

	}

	public static ModelDireccion convert(Direccion d) {

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
