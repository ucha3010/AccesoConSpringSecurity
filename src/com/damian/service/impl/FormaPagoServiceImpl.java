package com.damian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.FacturaDAO;
import com.damian.dao.FormaPagoDAO;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Factura;
import com.damian.pojo.FormaPago;
import com.damian.service.FormaPagoService;

@Service
public class FormaPagoServiceImpl implements FormaPagoService {

	@Autowired
	private FormaPagoDAO formaPagoDAO;

	@Autowired
	private FacturaDAO facturaDAO;

	@Override
	public List<FormaPago> findAll() {
		return formaPagoDAO.findAll();
	}

	@Override
	public FormaPago findById(int id) {
		return formaPagoDAO.findById(id);
	}

	@Override
	public FormaPago findByIdModel(int id) {
		return formaPagoDAO.findByIdModel(id);
	}

	@Override
	public int save(FormaPago formaPago) {
		return formaPagoDAO.save(formaPago);
	}

	@Override
	public int update(FormaPago formaPago) {
		return formaPagoDAO.update(formaPago);
	}

	@Override
	public int delete(int id) throws NotEmptyException {
		List<Factura> lista = facturaDAO.findByIdForModel(id);
		if (lista != null && !lista.isEmpty()) {
			throw new NotEmptyException("Tiene asociado facturas");
		}
		return formaPagoDAO.delete(id);
	}

}
