package com.damian.dao;

import java.util.List;

import com.damian.pojo.Categoria;

public interface CategoriaDAO {

	public List<Categoria> findAll();

	public Categoria findById(int id);

	public Categoria findByIdModel(int id);

	public int save(Categoria categoria);

	public int update(Categoria categoria);

	public int delete(int id);

	public int getMaxId();

}
