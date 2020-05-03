package com.damian.service;

import com.damian.pojo.front.Paginacion;

public interface PaginacionService {
	
	Paginacion pagination(int paginaInicio, int registrosPorPagina, String tabla);

}
