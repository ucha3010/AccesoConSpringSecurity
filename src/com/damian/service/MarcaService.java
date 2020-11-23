package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.Marca;

public interface MarcaService {

	List<Marca> findAll();

	Marca findById(int id);

	void save(Marca marca, HttpServletRequest request);

	void delete(int id, HttpServletRequest request);

	List<Marca> findByMarcaNombre(String nombre);

	int getMaxId();

}
