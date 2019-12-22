package com.damian.dao;

import java.util.List;

import com.damian.pojo.Producto;

public interface ProductoDAO {

	public List<Producto> findAll();

	public Producto findById(int id);

	public Producto findByIdModel(int id);

	public void save(Producto producto);

	public void update(Producto producto);

	public int delete(int id);

	public List<Producto> findByIdSubModel(int idSub);
	
	public List<Producto> findByIdList(int id);

}
