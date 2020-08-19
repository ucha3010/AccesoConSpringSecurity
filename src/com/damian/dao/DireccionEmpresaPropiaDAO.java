package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.DireccionEmpresaPropia;

public interface DireccionEmpresaPropiaDAO {

	List<DireccionEmpresaPropia> findAll();

	DireccionEmpresaPropia findById(int id);

	DireccionEmpresaPropia findByIdModel(int id);

	List<DireccionEmpresaPropia> findByIdPropiaModel(int idPropia);

	void save(DireccionEmpresaPropia direccionEmpresaPropia, HttpServletRequest request);

	void update(DireccionEmpresaPropia direccionEmpresaPropia, HttpServletRequest request);

	void delete(int id, HttpServletRequest request);

	public int getMaxId();

}
