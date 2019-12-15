package com.damian.dao;

import java.util.List;

import com.damian.pojo.Factura;

public interface FacturaDAO {

	public List<Factura> findAll();

	public Factura findById(int id);

	public Factura findByIdModel(int id);

	public int save(Factura factura);

	public int update(Factura factura);

	public int delete(int id);
	
	public List<Factura> findByIdEstModel(int idEst);
	
	public List<Factura> findByIdForModel(int idFor);

}
