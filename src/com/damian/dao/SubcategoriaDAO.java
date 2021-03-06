package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.Subcategoria;

public interface SubcategoriaDAO {

	public List<Subcategoria> findAll();

	public Subcategoria findById(int id);

	public Subcategoria findByIdModel(int id);

	public int save(Subcategoria subcategoria, HttpServletRequest request);

	public int update(Subcategoria subcategoria, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request);

	public List<Subcategoria> findByIdCatModel(int idCat);

	public int getMaxId();

}
