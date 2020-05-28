package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.ProductoFactura;

public interface ProductoFacturaDAO {

	public List<ProductoFactura> findAll();

	public void save(ProductoFactura productoFactura, HttpServletRequest request);

	public void update(ProductoFactura productoFactura, HttpServletRequest request);

	public void delete(int idPro, int idFac, HttpServletRequest request);

	public List<ProductoFactura> findByIdPro(int idPro);

	public List<ProductoFactura> findByIdFac(int idFac);

	public List<ProductoFactura> findByIdProModel(int idPro);

	public List<ProductoFactura> findByIdFacModel(int idFac);

	public ProductoFactura findByIdProAndIdFac(int idPro, int idFac);

}
