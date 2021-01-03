package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.FiltroTitulo;

public interface FiltroTituloDAO {

	public List<FiltroTitulo> findAll();

	public FiltroTitulo findById(int id);

	public FiltroTitulo findByIdModel(int id);

	public int save(FiltroTitulo filtroTitulo, HttpServletRequest request);

	public int update(FiltroTitulo filtroTitulo, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request);

	public int getMaxId();

	public List<FiltroTitulo> findByIdSub(int id);

	public List<FiltroTitulo> findByIdSubModel(int id);

}
