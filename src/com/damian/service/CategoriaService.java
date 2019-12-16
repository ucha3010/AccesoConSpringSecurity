package com.damian.service;

import java.util.List;

import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Categoria;

public interface CategoriaService {

	public List<Categoria> findAll();

	public Categoria findById(int id);

	public Categoria findByIdModel(int id);

	public int save(Categoria categoria);

	public int update(Categoria categoria);

	public int delete(int id) throws NotEmptyException;

}
