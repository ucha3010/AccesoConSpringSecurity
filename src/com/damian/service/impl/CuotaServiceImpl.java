package com.damian.service.impl;

import java.util.List;

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
	public int save(Cuota cuota) {
		return cuotaDAO.save(cuota);
	}

	@Override
	public int update(Cuota cuota) {
		return cuotaDAO.update(cuota);
	}

	@Override
	public int delete(int id) throws NotEmptyException {
		List<Factura> facturaList = facturaService.findByIdCuo(id);
		if (facturaList != null && !facturaList.isEmpty()) {
			throw new NotEmptyException("Tiene asociado facturas");
		}
		return cuotaDAO.delete(id);
	}

}
