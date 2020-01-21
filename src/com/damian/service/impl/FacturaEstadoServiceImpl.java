package com.damian.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.FacturaEstadoDAO;
import com.damian.pojo.FacturaEstado;
import com.damian.service.FacturaEstadoService;

@Service
public class FacturaEstadoServiceImpl implements FacturaEstadoService {

	@Autowired
	private FacturaEstadoDAO facturaEstadoDAO;

	@Override
	public List<FacturaEstado> findAll() {
		return facturaEstadoDAO.findAll();
	}

	@Override
	public FacturaEstado findById(int id) {
		return facturaEstadoDAO.findById(id);
	}

	@Override
	public void save(FacturaEstado facturaEstado, HttpServletRequest request) {
		facturaEstadoDAO.save(facturaEstado);
	}

	@Override
	public void update(FacturaEstado facturaEstado, HttpServletRequest request) {
		facturaEstadoDAO.update(facturaEstado);
	}

	@Override
	public void delete(int id) {
		facturaEstadoDAO.delete(id);
	}

	@Override
	public List<FacturaEstado> findByIdEst(int idEst) {
		return facturaEstadoDAO.findByIdEst(idEst);
	}

	@Override
	public List<FacturaEstado> findByIdFac(int idFac) {
		return facturaEstadoDAO.findByIdFac(idFac);
	}

	@Override
	public List<FacturaEstado> findByIdEstModel(int idEst) {
		return facturaEstadoDAO.findByIdEstModel(idEst);
	}

	@Override
	public List<FacturaEstado> findByIdFacModel(int idFac) {
		return facturaEstadoDAO.findByIdFacModel(idFac);
	}

	@Override
	public List<FacturaEstado> findByIdEstAndIdFac(int idEst, int idFac) {
		return facturaEstadoDAO.findByIdEstAndIdFac(idEst, idFac);
	}

}
