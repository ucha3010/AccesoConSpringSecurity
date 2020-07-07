package com.damian.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.FacturaEnviarFacturarDAO;
import com.damian.pojo.FacturaEnviarFacturar;
import com.damian.service.FacturaEnviarFacturarService;

@Service
public class FacturaEnviarFacturarServiceImpl implements FacturaEnviarFacturarService {

	@Autowired
	private FacturaEnviarFacturarDAO facturaEnviarFacturarDAO;

	@Override
	public FacturaEnviarFacturar findById(int id) {
		return facturaEnviarFacturarDAO.findById(id);
	}

	@Override
	public int save(FacturaEnviarFacturar facturaEnviarFacturar, HttpServletRequest request) {
		return facturaEnviarFacturarDAO.save(facturaEnviarFacturar, request);
	}

	@Override
	public int update(FacturaEnviarFacturar facturaEnviarFacturar, HttpServletRequest request) {
		return facturaEnviarFacturarDAO.update(facturaEnviarFacturar, request);
	}

	@Override
	public int delete(int id, HttpServletRequest request) {
		return facturaEnviarFacturarDAO.delete(id, request);
	}

	@Override
	public List<FacturaEnviarFacturar> findByIdFac(int idFac) {
		return facturaEnviarFacturarDAO.findByIdFac(idFac);
	}

}
