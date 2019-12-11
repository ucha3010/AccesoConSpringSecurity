package com.damian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.ProductoDAO;
import com.damian.pojo.Producto;
import com.damian.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoDAO productoDAO;

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
		return productoDAO.delete(id);
	}

}
