package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Producto;
import com.damian.pojo.front.FrontProductoStock;

public interface ProductoService {

	public List<Producto> findAll(String column, int paginaInicio, int totalPaginas, HttpServletRequest request);

	public Producto findById(int id);

	public Producto findByIdModel(int id);

	public int save(Producto producto, HttpServletRequest request);

	public void update(Producto producto, HttpServletRequest request);

	public int delete(int id) throws NotEmptyException;

	public List<Producto> findByIdList(int id);

	public void saveProductoStock(FrontProductoStock frontProductoStock, HttpServletRequest request);

	public FrontProductoStock fillFrontProductoStock(Producto producto);

	public List<Producto> findSearchAll();

	public List<Producto> findByIdSubModel(int idSub);

	public int getMaxId();

}
