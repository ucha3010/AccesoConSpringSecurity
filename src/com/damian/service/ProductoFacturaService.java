package com.damian.service;

import java.util.List;

import com.damian.pojo.ProductoFactura;

public interface ProductoFacturaService {

	public List<ProductoFactura> findAll();

	public void save(ProductoFactura productoFactura);

	public void update(ProductoFactura productoFactura);

	public void delete(int idPro, int idFac);

	public List<ProductoFactura> findByIdPro(int idPro);

	public List<ProductoFactura> findByIdFac(int idFac);

	public List<ProductoFactura> findByIdProModel(int idPro);

	public List<ProductoFactura> findByIdFacModel(int idFac);

	public ProductoFactura findByIdProAndIdFac(int idPro, int idFac);

}
