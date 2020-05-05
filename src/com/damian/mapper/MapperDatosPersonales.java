package com.damian.mapper;

import java.util.Date;
import java.util.Map;

import com.damian.dao.model.ModelDatosPersonales;

public class MapperDatosPersonales {

	public static ModelDatosPersonales mapeo(Map<String, Object> mapa) {
		ModelDatosPersonales mdp = new ModelDatosPersonales();
		mdp.setIdDatosPers((int) mapa.get("idDatosPers"));
		mdp.setNombre((String) mapa.get("nombre"));
		mdp.setApellido1((String) mapa.get("apellido1"));
		mdp.setApellido2((String) mapa.get("apellido2"));
		mdp.setSexo((String) mapa.get("sexo"));
		mdp.setFechaNacimiento((Date) mapa.get("fechaNacimiento"));
		mdp.setNacionalidad_idPais((int) mapa.get("nacionalidad_idPais"));
		mdp.setDni((String) mapa.get("dni"));
		mdp.setEmail((String) mapa.get("email"));
		mdp.setTelefono((String) mapa.get("telefono"));
		mdp.setObservaciones((String) mapa.get("observaciones"));
		mdp.setDatospersonales_idUsr((int) mapa.get("datospersonales_idUsr"));
		return mdp;
	}

}
