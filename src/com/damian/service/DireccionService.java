package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.Direccion;

public interface DireccionService {

	Direccion findById(int idDir);

	int save(int idUsr, Direccion direccion, HttpServletRequest request);

	List<Direccion> findListFromUsuario(int idUsr);

	void delete(int idDir);

}
