package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Categoria;

public interface CategoriaService {

	public List<Categoria> findAll();

	public Categoria findById(int id);

	public Categoria findByIdModel(int id);

	public int save(Categoria categoria, HttpServletRequest request);

	public int update(Categoria categoria, HttpServletRequest request);

	public int delete(int id) throws NotEmptyException;

}
