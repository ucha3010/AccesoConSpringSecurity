package com.damian.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.PreferenciaUsuarioDAO;
import com.damian.pojo.PreferenciaUsuario;
import com.damian.service.BotonFavoritoService;
import com.damian.service.PreferenciaUsuarioService;
import com.damian.service.TemaService;

@Service
public class PreferenciaUsuarioServiceImpl implements PreferenciaUsuarioService {

	@Autowired
	private PreferenciaUsuarioDAO preferenciaUsuarioDAO;

	@Autowired
	private BotonFavoritoService botonFavoritoService;

	@Autowired
	private TemaService temaService;

	@Override
	public List<PreferenciaUsuario> findAll() {
		return preferenciaUsuarioDAO.findAll();
	}

	@Override
	public PreferenciaUsuario findById(int id) {
		return preferenciaUsuarioDAO.findById(id);
	}

	@Override
	public int save(PreferenciaUsuario preferenciaUsuario, HttpServletRequest request) {
		preferenciaUsuario.setTema(temaService.findById(1).getNombre());
		preferenciaUsuario.setBotonFavorito(botonFavoritoService.findById(1).getNombre());
		preferenciaUsuario.setRecibirPublicidad(true);
		return preferenciaUsuarioDAO.save(preferenciaUsuario, request);
	}

	@Override
	public int update(PreferenciaUsuario preferenciaUsuario, HttpServletRequest request) {
		return preferenciaUsuarioDAO.update(preferenciaUsuario, request);
	}

	@Override
	public int delete(int id, HttpServletRequest request) {
		return preferenciaUsuarioDAO.delete(id, request);
	}

	@Override
	public List<PreferenciaUsuario> findByPublicity(boolean receive) {
		return preferenciaUsuarioDAO.findByPublicity(receive);
	}

}
