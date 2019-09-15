package com.damian.service;

import java.util.List;

import com.damian.pojo.Pais;

public interface PaisService {
	
	Pais findById(int id);

	void save(Pais pais);

	List<Pais> findAll();
	
	void update(Pais pais);
	
	void delete(int idPais);
	
	List<Pais> findByPaisName(String paisName);

}
