package com.damian.service;

import java.util.List;

import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Producto;

public interface ProductoService {

	public List<Producto> findAll();

	public Producto findById(int id);

	public Producto findByIdModel(int id);

	public void save(Producto producto);

	public void update(Producto producto);

	public int delete(int id) throws NotEmptyException;

}
