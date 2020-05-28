package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.CuotaDAO;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Cuota;
import com.damian.pojo.Factura;
import com.damian.service.CuotaService;
import com.damian.service.FacturaService;

@Service
public class CuotaServiceImpl implements CuotaService {

	@Autowired
	private CuotaDAO cuotaDAO;

	@Autowired
	private FacturaService facturaService;

	@Override
	public List<Cuota> findAll() {
		return cuotaDAO.findAll();
	}

	@Override
	public Cuota findById(int id) {
		return cuotaDAO.findById(id);
	}

	@Override
	public Cuota findByIdModel(int id) {
		return cuotaDAO.findByIdModel(id);
	}

	@Override
	public int save(Cuota cuota, HttpServletRequest request) {

		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		cuota.setModificadoPor(context.getAuthentication().getPrincipal().toString());
		cuota.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		return cuotaDAO.save(cuota, request);
	}

	@Override
	public int update(Cuota cuota, HttpServletRequest request) {

		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		cuota.setModificadoPor(context.getAuthentication().getPrincipal().toString());
		cuota.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		return cuotaDAO.update(cuota, request);
	}

	@Override
	public int delete(int id, HttpServletRequest request) throws NotEmptyException {
		List<Factura> facturaList = facturaService.findByIdCuo(id);
		if (facturaList != null && !facturaList.isEmpty()) {
			throw new NotEmptyException("Tiene asociado facturas");
		}
		return cuotaDAO.delete(id, request);
	}

}
