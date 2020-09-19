package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.BotonFavorito;

public interface BotonFavoritoDAO {

	public List<BotonFavorito> findAll();

	public BotonFavorito findById(int id);

	public int save(BotonFavorito botonFavorito, HttpServletRequest request);

	public int update(BotonFavorito botonFavorito, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request);

	public int getMaxId();

}
