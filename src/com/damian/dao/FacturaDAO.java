package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.Factura;

public interface FacturaDAO {

	public List<Factura> findAll(String column, HttpServletRequest request);

	public Factura findById(int id);

	public Factura findByIdModel(int id);

	public int save(Factura factura);

	public int update(Factura factura);

	public int delete(int id);

	public List<Factura> findByIdEstModel(int idEst, String column, HttpServletRequest request);

	public List<Factura> findByIdForModel(int idFor);

	public int getMaxId();

	public List<Factura> findByIdList(int id);

	public List<Factura> findByIdCuo(int idCuo);

}
