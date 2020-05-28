package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.EstadoDAO;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Estado;
import com.damian.pojo.Factura;
import com.damian.service.EstadoService;
import com.damian.service.FacturaService;

@Service
public class EstadoServiceImpl implements EstadoService {

	@Autowired
	private EstadoDAO estadoDAO;

	@Autowired
	private FacturaService facturaService;

	@Override
	public List<Estado> findAll() {
		return estadoDAO.findAll();
	}

	@Override
	public Estado findById(int id) {
		return estadoDAO.findById(id);
	}

	@Override
	public Estado findByIdModel(int id) {
		return estadoDAO.findByIdModel(id);
	}

	@Override
	public int save(Estado estado, HttpServletRequest request) {

		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		estado.setModificadoPor(context.getAuthentication().getPrincipal().toString());
		estado.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		return estadoDAO.save(estado, request);
	}

	@Override
	public int update(Estado estado, HttpServletRequest request) {

		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		estado.setModificadoPor(context.getAuthentication().getPrincipal().toString());
		estado.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		return estadoDAO.update(estado, request);
	}

	@Override
	public int delete(int id, String column, HttpServletRequest request) throws NotEmptyException {
		List<Factura> lista = facturaService.findByIdEstModel(id, column, request);
		if (lista != null && !lista.isEmpty()) {
			throw new NotEmptyException("Tiene asociado facturas");
		}
		return estadoDAO.delete(id, request);
	}

}
