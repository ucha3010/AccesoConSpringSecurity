package com.damian.service;

import java.util.List;

import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Factura;

public interface FacturaService {

	public List<Factura> findAll();

	public Factura findById(int id);

	public Factura findByIdModel(int id);

	public int save(Factura factura);

	public int update(Factura factura);

	public int delete(int id) throws NotEmptyException;

	public List<Factura> findByIdEstModel(int idEst);

	public List<Factura> findByIdForModel(int idFor);

}
