package com.damian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.damian.dao.ProductoEmpresaDAO;
import com.damian.pojo.ProductoEmpresa;
import com.damian.service.ProductoEmpresaService;

public class ProductoEmpresaServiceImpl implements ProductoEmpresaService {

	@Autowired
	private ProductoEmpresaDAO productoEmpresaDAO;

	@Override
	public List<ProductoEmpresa> findAll() {
		return productoEmpresaDAO.findAll();
	}

	@Override
	public void save(ProductoEmpresa productoEmpresa) {
		productoEmpresaDAO.save(productoEmpresa);
	}

	@Override
	public void update(ProductoEmpresa productoEmpresa) {
		productoEmpresaDAO.update(productoEmpresa);
	}

	@Override
	public void delete(int idPro, int idEmp) {
		productoEmpresaDAO.delete(idPro, idEmp);
	}

	@Override
	public List<ProductoEmpresa> findByIdPro(int idPro) {
		return productoEmpresaDAO.findByIdPro(idPro);
	}

	@Override
	public List<ProductoEmpresa> findByIdEmp(int idEmp) {
		return productoEmpresaDAO.findByIdEmp(idEmp);
	}

	@Override
	public List<ProductoEmpresa> findByIdProModel(int idPro) {
		return productoEmpresaDAO.findByIdProModel(idPro);
	}

	@Override
	public List<ProductoEmpresa> findByIdEmpModel(int idEmp) {
		return productoEmpresaDAO.findByIdEmpModel(idEmp);
	}

	@Override
	public ProductoEmpresa findByIdProAndIdEmp(int idPro, int idEmp) {
		return productoEmpresaDAO.findByIdProAndIdEmp(idPro, idEmp);
	}

}
