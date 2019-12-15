package com.damian.dao;

import java.util.List;

import com.damian.pojo.Subcategoria;

public interface SubcategoriaDAO {

	public List<Subcategoria> findAll();

	public Subcategoria findById(int id);

	public Subcategoria findByIdModel(int id);

	public int save(Subcategoria subcategoria);

	public int update(Subcategoria subcategoria);

	public int delete(int id);

	public List<Subcategoria> findByIdCatModel(int idCat);

}
