package com.damian.dao;

import java.util.List;

import com.damian.pojo.Estado;

public interface EstadoDAO {

	public List<Estado> findAll();

	public Estado findById(int id);

	public Estado findByIdModel(int id);

	public int save(Estado estado);

	public int update(Estado estado);

	public int delete(int id);

}
