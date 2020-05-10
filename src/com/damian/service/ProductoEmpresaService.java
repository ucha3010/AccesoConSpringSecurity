package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.ProductoEmpresa;

public interface ProductoEmpresaService {

	public List<ProductoEmpresa> findAll();

	public void save(int idPro, int idEmp, HttpServletRequest request);

	public void update(ProductoEmpresa productoEmpresa);

	public void delete(int idPro, int idEmp);

	public List<ProductoEmpresa> findByIdPro(int idPro);

	public List<ProductoEmpresa> findByIdEmp(int idEmp);

	public List<ProductoEmpresa> findByIdProModel(int idPro);

	public List<ProductoEmpresa> findByIdEmpModel(int idEmp);

	public ProductoEmpresa findByIdProAndIdEmp(int idPro, int idEmp);

}
