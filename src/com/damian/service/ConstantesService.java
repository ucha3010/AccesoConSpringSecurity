package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DuplicateKeyException;

import com.damian.pojo.Constantes;
import com.damian.pojo.front.FrontAdministrarConfiguracion;

public interface ConstantesService {

	public List<Constantes> findAll();

	public Constantes findByClave(String clave);

	public List<Constantes> findValorString(String valor);

	public List<Constantes> findValorDouble(double valor);

	public int save(Constantes constantes, HttpServletRequest request) throws DuplicateKeyException;

	public int update(Constantes constantes, HttpServletRequest request);

	public int delete(String clave, HttpServletRequest request);

	public FrontAdministrarConfiguracion findAdminConfiguration();

}
