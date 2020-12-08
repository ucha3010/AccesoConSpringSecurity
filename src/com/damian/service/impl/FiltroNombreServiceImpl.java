package com.damian.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.FiltroNombreDAO;
import com.damian.pojo.FiltroNombre;
import com.damian.service.FiltroNombreService;

@Service
public class FiltroNombreServiceImpl implements FiltroNombreService {

	@Autowired
	private FiltroNombreDAO filtroNombreDAO;

	@Override
	public List<FiltroNombre> findAll() {
		return filtroNombreDAO.findAll();
	}

	@Override
	public FiltroNombre findById(int id) {
		return filtroNombreDAO.findById(id);
	}

	@Override
	public int save(FiltroNombre filtroNombre, HttpServletRequest request) {
		return filtroNombreDAO.save(filtroNombre, request);
	}

	@Override
	public int update(FiltroNombre filtroNombre, HttpServletRequest request) {
		return filtroNombreDAO.update(filtroNombre, request);
	}

	@Override
	public int delete(int id, HttpServletRequest request) {
		return filtroNombreDAO.delete(id, request);
	}

	@Override
	public int getMaxId() {
		return filtroNombreDAO.getMaxId();
	}

	@Override
	public List<FiltroNombre> findByIdTituloModel(int idTitulo) {
		return filtroNombreDAO.findByIdTituloModel(idTitulo);
	}

}
