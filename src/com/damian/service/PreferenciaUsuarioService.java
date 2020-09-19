package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.PreferenciaUsuario;

public interface PreferenciaUsuarioService {

	public List<PreferenciaUsuario> findAll();

	public PreferenciaUsuario findById(int id);

	public int save(PreferenciaUsuario preferenciaUsuario, HttpServletRequest request);

	public int update(PreferenciaUsuario preferenciaUsuario, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request);

	public List<PreferenciaUsuario> findByPublicity(boolean receive);

}
