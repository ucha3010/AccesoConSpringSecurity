package com.damian.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.damian.dao.model.ModelFactura;
import com.damian.pojo.Factura;

public interface FacturaDAO {

	public List<Factura> findAll(String column, int paginaInicio, int totalPaginas, HttpServletRequest request);

	public Factura findById(int id);

	public Factura findByIdModel(int id);

	public int save(Factura factura, HttpServletRequest request);

	public int update(Factura factura, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request);

	public List<Factura> findByIdEstModel(int idEst, String column, HttpServletRequest request);

	public List<Factura> findByIdForModel(int idFor);

	public int getMaxId();

	public List<Factura> findByIdList(int id);

	public List<Factura> findByIdCuo(int idCuo);

	public List<Factura> findSearchAll();

	public List<Map<String, Object>> facturaMap(int id);

	public ModelFactura findModelById(int idFac);

}
