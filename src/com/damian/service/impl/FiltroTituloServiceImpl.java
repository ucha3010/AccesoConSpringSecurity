package com.damian.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.FiltroTituloDAO;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.FiltroNombre;
import com.damian.pojo.FiltroTitulo;
import com.damian.service.FiltroNombreService;
import com.damian.service.FiltroTituloService;

@Service
public class FiltroTituloServiceImpl implements FiltroTituloService {

	@Autowired
	private FiltroTituloDAO filtroTituloDAO;

	@Autowired
	private FiltroNombreService filtroNombreService;

	@Override
	public List<FiltroTitulo> findAll() {
		return filtroTituloDAO.findAll();
	}

	@Override
	public FiltroTitulo findById(int id) {
		return filtroTituloDAO.findById(id);
	}

	@Override
	public int save(FiltroTitulo filtroTitulo, HttpServletRequest request) {
		return filtroTituloDAO.save(filtroTitulo, request);
	}

	@Override
	public int update(FiltroTitulo filtroTitulo, HttpServletRequest request) {
		return filtroTituloDAO.update(filtroTitulo, request);
	}

	@Override
	public int delete(int id, HttpServletRequest request) throws NotEmptyException {
		List<FiltroNombre> filtroNombreList = filtroNombreService.findByIdTituloModel(id);
		if (filtroNombreList != null && !filtroNombreList.isEmpty()) {
			throw new NotEmptyException("Tiene asociado filtroNombres");
		}
		return filtroTituloDAO.delete(id, request);
	}

	@Override
	public int getMaxId() {
		return filtroTituloDAO.getMaxId();
	}

	@Override
	public List<FiltroTitulo> findByIdSub(int id) {
		return filtroTituloDAO.findByIdSub(id);
	}

	@Override
	public List<FiltroTitulo> findByIdSubModel(int id) {
		return filtroTituloDAO.findByIdSubModel(id);
	}

}
