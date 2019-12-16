package com.damian.service;

import java.util.List;

import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Subcategoria;

public interface SubcategoriaService {

	public List<Subcategoria> findAll();

	public Subcategoria findById(int id);

	public Subcategoria findByIdModel(int id);

	public int save(Subcategoria subcategoria);

	public int update(Subcategoria subcategoria);

	public int delete(int id) throws NotEmptyException;

	public List<Subcategoria> findByIdCatModel(int idCat);

}
