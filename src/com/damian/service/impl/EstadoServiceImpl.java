package com.damian.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.EstadoDAO;
import com.damian.dao.FacturaDAO;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Estado;
import com.damian.pojo.Factura;
import com.damian.service.EstadoService;

@Service
public class EstadoServiceImpl implements EstadoService {

	@Autowired
	private EstadoDAO estadoDAO;

	@Autowired
	private FacturaDAO facturaDAO;

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
	public int save(Estado estado) {
		return estadoDAO.save(estado);
	}

	@Override
	public int update(Estado estado) {
		return estadoDAO.update(estado);
	}

	@Override
	public int delete(int id, String column, HttpServletRequest request) throws NotEmptyException {
		List<Factura> lista = facturaDAO.findByIdEstModel(id, column, request);
		if (lista != null && !lista.isEmpty()) {
			throw new NotEmptyException("Tiene asociado facturas");
		}
		return estadoDAO.delete(id);
	}

}
