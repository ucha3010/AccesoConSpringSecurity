package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.ProductoFiltro;

public interface ProductoFiltroDAO {

	public List<ProductoFiltro> findAll();

	public void save(ProductoFiltro productoFiltro, HttpServletRequest request);

	public void update(ProductoFiltro productoFiltro, HttpServletRequest request);

	public void delete(int idPro, int idNombre, HttpServletRequest request);

	public List<ProductoFiltro> findByIdPro(int idPro);

	public List<ProductoFiltro> findByIdNombre(int idNombre);

	public List<ProductoFiltro> findByIdProModel(int idPro);

	public List<ProductoFiltro> findByIdNombreModel(int idNombre);

	public ProductoFiltro findByIdProAndIdNombre(int idPro, int idNombre);

}
