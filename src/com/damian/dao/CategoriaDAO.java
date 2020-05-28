package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.Categoria;

public interface CategoriaDAO {

	public List<Categoria> findAll();

	public Categoria findById(int id);

	public Categoria findByIdModel(int id);

	public int save(Categoria categoria, HttpServletRequest request);

	public int update(Categoria categoria, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request);

	public int getMaxId();

}
