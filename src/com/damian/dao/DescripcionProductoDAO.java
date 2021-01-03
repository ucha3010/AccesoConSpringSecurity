package com.damian.dao;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.DescripcionProducto;

public interface DescripcionProductoDAO {

	public DescripcionProducto findById(int id);

	public int save(DescripcionProducto descripcionProducto, HttpServletRequest request);

	public int update(DescripcionProducto descripcionProducto, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request);

}
