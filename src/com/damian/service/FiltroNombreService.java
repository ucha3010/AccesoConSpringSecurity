package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.FiltroNombre;

public interface FiltroNombreService {

	public List<FiltroNombre> findAll();

	public FiltroNombre findById(int id);

	public int save(FiltroNombre filtroNombre, HttpServletRequest request);

	public int update(FiltroNombre filtroNombre, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request);

	public int getMaxId();

	public List<FiltroNombre> findByIdTituloModel(int idTitulo);

}
