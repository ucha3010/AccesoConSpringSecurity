package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.FiltroTitulo;

public interface FiltroTituloService {

	public List<FiltroTitulo> findAll();

	public FiltroTitulo findById(int id);

	public int save(FiltroTitulo filtroTitulo, HttpServletRequest request);

	public int update(FiltroTitulo filtroTitulo, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request) throws NotEmptyException;

	public int getMaxId();

	public List<FiltroTitulo> findByIdSub(int id);

	public List<FiltroTitulo> findByIdSubModel(int id);

}
