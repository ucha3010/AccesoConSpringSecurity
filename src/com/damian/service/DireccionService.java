package com.damian.service;

import java.util.List;

import com.damian.pojo.DatosPersonales;
import com.damian.pojo.Direccion;

public interface DireccionService {
	
	Direccion findById(int idDir);
	
	void save(DatosPersonales datosPersonales, Direccion direccion);

	List<Direccion> findListFromUsuario(int idUsr);

}
