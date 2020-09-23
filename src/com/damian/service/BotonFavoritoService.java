package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.BotonFavorito;
import com.damian.pojo.front.FrontBotonFavorito;

public interface BotonFavoritoService {

	public List<BotonFavorito> findAll();

	public BotonFavorito findById(int id);

	public int save(BotonFavorito botonFavorito, HttpServletRequest request);

	public int update(BotonFavorito botonFavorito, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request) throws NotEmptyException;

	public int getMaxId();

	public List<FrontBotonFavorito> findAllFront();

}
