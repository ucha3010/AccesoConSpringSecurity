package com.damian.service.impl;

import com.damian.dao.PaginacionDAO;
import com.damian.pojo.front.Paginacion;
import com.damian.service.PaginacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaginacionServiceImpl implements PaginacionService {

	@Autowired
	private PaginacionDAO paginacionDAO;

	@Override
	public Paginacion pagination(int paginaInicio, int totalPaginas, String tabla) {
		return paginacionDAO.pagination(paginaInicio, totalPaginas, tabla);
	}

	@Override
	public Paginacion pagination(int paginaInicio, int registrosPorPagina, int totalRegistros) {
		return paginacionDAO.pagination(paginaInicio, registrosPorPagina, totalRegistros);
	}

}
