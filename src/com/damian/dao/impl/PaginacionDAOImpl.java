package com.damian.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.damian.dao.PaginacionDAO;
import com.damian.pojo.front.Paginacion;

@Repository
public class PaginacionDAOImpl implements PaginacionDAO {

	private JdbcTemplate jdbcTemplate;

	public PaginacionDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Paginacion pagination(int paginaInicio, int registrosPorPagina, String tabla) {
		
		Paginacion paginacion = new Paginacion();
		int totalRegistros = totalRows(tabla);
		
		paginacion.setActual(paginaInicio);
		paginacion.setTotalRegistros(totalRegistros);
		paginacion.setRegistrosPorPagina(registrosPorPagina);
		if(paginaInicio - registrosPorPagina < 0) {
			paginacion.setPrimeraPagina(true);
			paginacion.setAnterior(0);
		} else {
			paginacion.setPrimeraPagina(false);
			paginacion.setAnterior(paginaInicio - registrosPorPagina);
		}
		if(paginaInicio + registrosPorPagina > totalRegistros) {
			paginacion.setUltimaPagina(true);
			paginacion.setSiguiente(0);
		} else {
			paginacion.setUltimaPagina(false);
			paginacion.setSiguiente(paginaInicio + registrosPorPagina);
		}
		if(registrosPorPagina > 0) {
			paginacion.setTotalPaginas((int) Math.ceil((double)totalRegistros/(double)registrosPorPagina));
		} else {
			paginacion.setTotalPaginas(0);
		}
		List<Integer> comienzoPagina = new ArrayList<>();
		int comienzo = 0;
		while(registrosPorPagina > 0 && comienzo < totalRegistros) {
			comienzoPagina.add(comienzo);
			comienzo += registrosPorPagina;
		}
		paginacion.setComienzaPagina(comienzoPagina);
		
		return paginacion;
	}

	private int totalRows(String tabla) {
		String sql = "SELECT COUNT(*) FROM " + tabla;
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}
