package com.damian.dao;

import com.damian.pojo.front.Paginacion;

public interface PaginacionDAO {
	
	public Paginacion pagination(int paginaInicio, int totalPaginas, String tabla);
	
	public Paginacion pagination(int paginaInicio, int totalPaginas, int totalRegistros);

}
