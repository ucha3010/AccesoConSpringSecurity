package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.Pais;

public interface PaisDAO {

	public void save(Pais pais, HttpServletRequest request);

	public List<Pais> findAll();

	public void update(Pais pais, HttpServletRequest request);

	public void delete(int id, HttpServletRequest request);

	public Pais findById(int id);

	public List<Pais> findByPaisName(String paisName);

	public int getMaxId();

}
