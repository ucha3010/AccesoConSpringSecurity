package com.damian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.ProductoDAO;
import com.damian.dao.ProductoEmpresaDAO;
import com.damian.dao.ProductoFacturaDAO;
import com.damian.pojo.Producto;
import com.damian.pojo.ProductoEmpresa;
import com.damian.pojo.ProductoFactura;
import com.damian.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoDAO productoDAO;

	@Autowired
	private ProductoFacturaDAO productoFacturaDAO;

	@Autowired
	private ProductoEmpresaDAO productoEmpresaDAO;

	@Override
	public List<Producto> findAll() {
		return productoDAO.findAll();
	}

	@Override
	public Producto findById(int id) {
		return productoDAO.findById(id);
	}

	@Override
	public Producto findByIdModel(int id) {
		return productoDAO.findByIdModel(id);
	}

	@Override
	public void save(Producto producto) {
		productoDAO.save(producto);
	}

	@Override
	public void update(Producto producto) {
		productoDAO.update(producto);
	}

	@Override
	public int delete(int id) {
		List<ProductoFactura> productoFacturaList = productoFacturaDAO.findByIdPro(id);
		if (productoFacturaList != null) {
			for (ProductoFactura p : productoFacturaList) {
				productoFacturaDAO.delete(id, p.getFactura().getIdFac());
			}
		}
		List<ProductoEmpresa> productoEmpresaList = productoEmpresaDAO.findByIdPro(id);
		if (productoEmpresaList != null) {
			for (ProductoEmpresa p : productoEmpresaList) {
				productoEmpresaDAO.delete(id, p.getEmpresa().getIdEmp());
			}
		}
		return productoDAO.delete(id);
	}

}
