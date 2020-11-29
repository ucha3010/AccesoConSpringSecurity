package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.Producto;

public interface ProductoDAO {

	public List<Producto> findAll(String column, int paginaInicio, int totalPaginas, HttpServletRequest request);

	public Producto findById(int id);

	public Producto findByIdModel(int id);

	public void save(Producto producto, HttpServletRequest request);

	public void update(Producto producto, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request);

	public List<Producto> findByIdSubModel(int idSub);

	public List<Producto> findByIdList(int id);

	public int getMaxId();

	public List<Producto> findSearchAll();
	
	public List<Producto> findAllReducedData();

}
