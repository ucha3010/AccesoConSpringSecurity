package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.Marca;

public interface MarcaDAO {

	public List<Marca> findAll();

	public Marca findById(int id);

	public void save(Marca marca, HttpServletRequest request);

	public void delete(int id, HttpServletRequest request);

	public List<Marca> findByMarcaNombre(String nombre);

	public int getMaxId();

}
