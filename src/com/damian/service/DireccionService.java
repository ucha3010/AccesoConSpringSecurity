package com.damian.service;

import java.util.List;

import com.damian.pojo.Direccion;

public interface DireccionService {
	
	Direccion findById(int idDir);
	
	void save(int idUsr, Direccion direccion);

	List<Direccion> findListFromUsuario(int idUsr);
	
	void delete(int idDir);

}
