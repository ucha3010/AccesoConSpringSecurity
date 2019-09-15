package com.damian.dao;

import java.util.List;

import com.damian.pojo.Pais;

public interface PaisDAO {

	public void save(Pais pais);

	public List<Pais> findAll();
	
	public void update(Pais pais);
	
	public void delete(Pais pais);

	public Pais findById(int id);
	
	public List<Pais> findByPaisName(String paisName);

}
