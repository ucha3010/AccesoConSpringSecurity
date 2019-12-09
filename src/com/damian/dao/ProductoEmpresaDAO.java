package com.damian.dao;

import java.util.List;

import com.damian.pojo.ProductoEmpresa;

public interface ProductoEmpresaDAO {

	public List<ProductoEmpresa> findAll();

	public void save(ProductoEmpresa productoEmpresa);

	public void update(ProductoEmpresa productoEmpresa);

	public void delete(ProductoEmpresa productoEmpresa);

	public List<ProductoEmpresa> findByIdPro(int idPro);

	public List<ProductoEmpresa> findByIdEmp(int idEmp);

	public List<ProductoEmpresa> findByIdProModel(int idPro);

	public List<ProductoEmpresa> findByIdEmpModel(int idEmp);

	public ProductoEmpresa findByIdProAndIdEmp(int idPro, int idEmp);

}
