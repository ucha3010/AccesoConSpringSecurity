package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.FiltroTitulo;
import com.damian.pojo.ProductoFiltro;

public interface ProductoFiltroService {

	public List<ProductoFiltro> findAll();

	public int save(int idPro, int idNombre, HttpServletRequest request);

	public int update(ProductoFiltro productoFiltro, HttpServletRequest request);

	public void delete(int idPro, int idNombre, HttpServletRequest request);

	public List<ProductoFiltro> findByIdPro(int idPro);

	public List<ProductoFiltro> findByIdNombre(int idNombre);

	public List<ProductoFiltro> findByIdProModel(int idPro);

	public List<ProductoFiltro> findByIdNombreModel(int idNombre);

	public ProductoFiltro findByIdProAndIdNombre(int idPro, int idNombre);

	public void marcarSeleccionados(List<FiltroTitulo> filtroTitulos, int idPro);

	public void deleteByIdPro(int idPro);

}
