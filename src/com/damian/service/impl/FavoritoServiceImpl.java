package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.FavoritoDAO;
import com.damian.pojo.Favorito;
import com.damian.service.FavoritoService;

@Service
public class FavoritoServiceImpl implements FavoritoService {

	@Autowired
	private FavoritoDAO favoritoDAO;

	@Override
	public List<Favorito> findAll() {
		return favoritoDAO.findAll();
	}

	@Override
	public Favorito findById(int idUsr, int idPro) {
		return favoritoDAO.findById(idUsr, idPro);
	}

	@Override
	public List<Favorito> findByIdUsr(int idUsr) {
		return favoritoDAO.findByIdUsr(idUsr);
	}

	@Override
	public List<Favorito> findByIdPro(int idPro) {
		return favoritoDAO.findByIdPro(idPro);
	}

	@Override
	public int save(Favorito favorito, HttpServletRequest request) {
		favorito.setFechaAlta(new Timestamp(System.currentTimeMillis()));
		return favoritoDAO.save(favorito, request);
	}

	@Override
	public int delete(int idUsr, int idPro, HttpServletRequest request) {
		return favoritoDAO.delete(idUsr, idPro, request);
	}

}
