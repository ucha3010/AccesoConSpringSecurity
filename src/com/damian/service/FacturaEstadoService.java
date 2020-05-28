package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.FacturaEstado;

public interface FacturaEstadoService {

	public List<FacturaEstado> findAll();
	
	public FacturaEstado findById(int id);

	public void save(FacturaEstado facturaEstado, HttpServletRequest request);

	public void update(FacturaEstado facturaEstado, HttpServletRequest request);

	public void delete(int id, HttpServletRequest request);

	public List<FacturaEstado> findByIdEst(int idEst);

	public List<FacturaEstado> findByIdFac(int idFac);

	public List<FacturaEstado> findByIdEstModel(int idEst);

	public List<FacturaEstado> findByIdFacModel(int idFac);

	public List<FacturaEstado> findByIdEstAndIdFac(int idEst, int idFac);

}
