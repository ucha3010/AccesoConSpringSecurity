package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.Pais;

public interface PaisService {
	
	Pais findById(int id);

	void save(Pais pais, HttpServletRequest request);

	List<Pais> findAll();
	
	void update(Pais pais, HttpServletRequest request);
	
	void delete(int idPais, HttpServletRequest request);
	
	List<Pais> findByPaisName(String paisName);

}
