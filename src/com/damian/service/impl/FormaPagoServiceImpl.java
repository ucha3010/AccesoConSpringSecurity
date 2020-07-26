package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.FormaPagoDAO;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Factura;
import com.damian.pojo.FormaPago;
import com.damian.service.FacturaService;
import com.damian.service.FormaPagoService;
import com.damian.utils.Utils;

@Service
public class FormaPagoServiceImpl implements FormaPagoService {

	@Autowired
	private FormaPagoDAO formaPagoDAO;

	@Autowired
	private FacturaService facturaService;

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
	public int save(FormaPago formaPago, HttpServletRequest request) {

		formaPago.setModificadoPor(Utils.getLoggedUser(request));
		formaPago.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		return formaPagoDAO.save(formaPago, request);
	}

	@Override
	public int update(FormaPago formaPago, HttpServletRequest request) {

		formaPago.setModificadoPor(Utils.getLoggedUser(request));
		formaPago.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		return formaPagoDAO.update(formaPago, request);
	}

	@Override
	public int delete(int id, HttpServletRequest request) throws NotEmptyException {
		List<Factura> lista = facturaService.findByIdForModel(id);
		if (lista != null && !lista.isEmpty()) {
			throw new NotEmptyException("Tiene asociado facturas");
		}
		return formaPagoDAO.delete(id, request);
	}

}
