package com.damian.dao;

import java.util.List;

import com.damian.pojo.FacturaEstado;

public interface FacturaEstadoDAO {

	public List<FacturaEstado> findAll();

	public void save(FacturaEstado facturaEstado);

	public void update(FacturaEstado facturaEstado);

	public void delete(int id);

	public List<FacturaEstado> findByIdEst(int idEst);

	public List<FacturaEstado> findByIdFac(int idFac);

	public List<FacturaEstado> findByIdEstModel(int idEst);

	public List<FacturaEstado> findByIdFacModel(int idFac);

	public List<FacturaEstado> findByIdEstAndIdFac(int idEst, int idFac);

}
