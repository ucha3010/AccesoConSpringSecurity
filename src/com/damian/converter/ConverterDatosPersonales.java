package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelDatosPersonales;
import com.damian.pojo.DatosPersonales;
import com.damian.pojo.Pais;
import com.damian.pojo.Usuario;

@Component
public class ConverterDatosPersonales {

	@Autowired
	private ConverterRellenaObjeto converterRellenaObjeto;

	public DatosPersonales convertAll(ModelDatosPersonales mdp) {

		DatosPersonales dp = convert(mdp);
		converterRellenaObjeto.rellenaDatosPersonales(dp, mdp);

		return dp;

	}

	public DatosPersonales convert(ModelDatosPersonales mdp) {

		DatosPersonales dp = new DatosPersonales();
		dp.setIdDatosPers(mdp.getIdDatosPers());
		dp.setNombre(mdp.getNombre());
		dp.setApellido1(mdp.getApellido1());
		dp.setApellido2(mdp.getApellido2());
		dp.setSexo(mdp.getSexo());
		dp.setFechaNacimiento(mdp.getFechaNacimiento());
		Pais pais = new Pais();
		pais.setIdPais(mdp.getNacionalidad_idPais());
		dp.setNacionalidad(pais);
		dp.setDni(mdp.getDni());
		dp.setEmail(mdp.getEmail());
		dp.setTelefono(mdp.getTelefono());
		dp.setObservaciones(mdp.getObservaciones());
		Usuario u = new Usuario();
		u.setIdUsr(mdp.getDatospersonales_idUsr());
		dp.setUsuario(u);

		return dp;

	}

	public ModelDatosPersonales convert(DatosPersonales dp) {

		ModelDatosPersonales mdp = new ModelDatosPersonales();
		mdp.setIdDatosPers(dp.getIdDatosPers());
		mdp.setNombre(dp.getNombre());
		mdp.setApellido1(dp.getApellido1());
		mdp.setApellido2(dp.getApellido2());
		mdp.setSexo(dp.getSexo());
		mdp.setFechaNacimiento(dp.getFechaNacimiento());
		if (dp.getNacionalidad() != null) {
			mdp.setNacionalidad_idPais(dp.getNacionalidad().getIdPais());
		}
		mdp.setDni(dp.getDni());
		mdp.setEmail(dp.getEmail());
		mdp.setTelefono(dp.getTelefono());
		mdp.setObservaciones(dp.getObservaciones());
		if (dp.getUsuario() != null) {
			mdp.setDatospersonales_idUsr(dp.getUsuario().getIdUsr());
		}

		return mdp;

	}

}
