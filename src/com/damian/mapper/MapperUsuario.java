package com.damian.mapper;

import java.sql.Timestamp;
import java.util.Map;

import com.damian.dao.model.ModelUsuario;

public class MapperUsuario {

	public static ModelUsuario mapeo(Map<String, Object> mapa) {
		ModelUsuario mu = new ModelUsuario();
		mu.setIdUsr((int) mapa.get("idUsr"));
		mu.setUsuario((String) mapa.get("usuario"));
		mu.setClave((String) mapa.get("clave"));
		if((int) mapa.get("habilitado") == 0) {
			mu.setHabilitado(false);
		} else {
			mu.setHabilitado(true);
		}
		mu.setFechaCreacion((Timestamp) mapa.get("fechaCreacion"));
		return mu;
	}

}
