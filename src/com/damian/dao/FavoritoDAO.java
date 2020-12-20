package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.Favorito;

public interface FavoritoDAO {

	public List<Favorito> findAll();

	public Favorito findById(int idUsr, int idPro);

	public List<Favorito> findByIdUsr(int idUsr);

	public List<Favorito> findByIdPro(int idPro);

	public int save(Favorito favorito, HttpServletRequest request);

	public int delete(int idUsr, int idPro, HttpServletRequest request);

}
