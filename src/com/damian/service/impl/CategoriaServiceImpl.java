package com.damian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.CategoriaDAO;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Categoria;
import com.damian.pojo.Subcategoria;
import com.damian.service.CategoriaService;
import com.damian.service.SubcategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaDAO categoriaDAO;

	@Autowired
	private SubcategoriaService subcategoriaService;

	@Override
	public List<Categoria> findAll() {
		return categoriaDAO.findAll();
	}

	@Override
	public Categoria findById(int id) {
		return categoriaDAO.findById(id);
	}

	@Override
	public Categoria findByIdModel(int id) {
		return categoriaDAO.findByIdModel(id);
	}

	@Override
	public int save(Categoria categoria) {
		return categoriaDAO.save(categoria);
	}

	@Override
	public int update(Categoria categoria) {
		return categoriaDAO.update(categoria);
	}

	@Override
	public int delete(int id) throws NotEmptyException {
		List<Subcategoria> subcategoriaList = subcategoriaService.findByIdCatModel(id);
		if (subcategoriaList != null && !subcategoriaList.isEmpty()) {
			throw new NotEmptyException("Tiene asociado subcategorias");
		}
		return categoriaDAO.delete(id);
	}

}
