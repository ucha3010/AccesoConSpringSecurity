package com.damian.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.damian.dao.ConstantesDAO;
import com.damian.pojo.Constantes;
import com.damian.service.ConstantesService;

@Service
public class ConstantesServiceImpl implements ConstantesService {

	@Autowired
	private ConstantesDAO constantesDAO;

	@Override
	public List<Constantes> findAll() {
		return constantesDAO.findAll();
	}

	@Override
	public Constantes findByClave(String clave) {
		return constantesDAO.findByClave(clave);
	}

	@Override
	public List<Constantes> findValorString(String valor) {
		return constantesDAO.findValorString(valor);
	}

	@Override
	public List<Constantes> findValorDouble(double valor) {
		return constantesDAO.findValorDouble(valor);
	}

	@Override
	public int save(Constantes constantes, HttpServletRequest request) throws DuplicateKeyException {
		return constantesDAO.save(constantes, request);
	}

	@Override
	public int update(Constantes constantes, HttpServletRequest request) {
		return constantesDAO.update(constantes, request);
	}

	@Override
	public int delete(String clave, HttpServletRequest request) {
		return constantesDAO.delete(clave, request);
	}

}
