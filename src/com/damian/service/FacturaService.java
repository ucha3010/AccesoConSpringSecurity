package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Factura;

public interface FacturaService {

	public List<Factura> findAll(String column, HttpServletRequest request);

	public Factura findById(int id);

	public Factura findByIdModel(int id);

	public int save(Factura factura, HttpServletRequest request);

	public int update(Factura factura, HttpServletRequest request);

	public int delete(int id) throws NotEmptyException;

	public List<Factura> findByIdEstModel(int idEst, String column, HttpServletRequest request);

	public List<Factura> findByIdForModel(int idFor);

	public List<Factura> findByIdList(int id);

	public List<Factura> findByIdEstList(int idEst, String column, HttpServletRequest request);

	public List<Factura> findByIdCuo(int idCuo);

	public List<Factura> selectExpire(List<Factura> facturas);

}
