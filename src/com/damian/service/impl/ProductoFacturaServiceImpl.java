package com.damian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.ProductoFacturaDAO;
import com.damian.pojo.ProductoFactura;
import com.damian.service.ProductoFacturaService;

@Service
public class ProductoFacturaServiceImpl implements ProductoFacturaService {

	@Autowired
	private ProductoFacturaDAO productoFacturaDAO;

	@Override
	public List<ProductoFactura> findAll() {
		return productoFacturaDAO.findAll();
	}

	@Override
	public void save(ProductoFactura productoFactura) {
		productoFacturaDAO.save(productoFactura);
	}

	@Override
	public void update(ProductoFactura productoFactura) {
		productoFacturaDAO.update(productoFactura);
	}

	@Override
	public void delete(int idPro, int idFac) {
		productoFacturaDAO.delete(idPro, idFac);
	}

	@Override
	public List<ProductoFactura> findByIdPro(int idPro) {
		return productoFacturaDAO.findByIdPro(idPro);
	}

	@Override
	public List<ProductoFactura> findByIdFac(int idFac) {
		return productoFacturaDAO.findByIdFac(idFac);
	}

	@Override
	public List<ProductoFactura> findByIdProModel(int idPro) {
		return productoFacturaDAO.findByIdProModel(idPro);
	}

	@Override
	public List<ProductoFactura> findByIdFacModel(int idFac) {
		return productoFacturaDAO.findByIdFacModel(idFac);
	}

	@Override
	public ProductoFactura findByIdProAndIdFac(int idPro, int idFac) {
		return productoFacturaDAO.findByIdProAndIdFac(idPro, idFac);
	}

}
