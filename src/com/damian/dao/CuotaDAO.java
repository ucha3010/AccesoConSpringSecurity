package com.damian.dao;

import java.util.List;

import com.damian.pojo.Cuota;

public interface CuotaDAO {

	public List<Cuota> findAll();

	public Cuota findById(int id);

	public Cuota findByIdModel(int id);

	public int save(Cuota cuota);

	public int update(Cuota cuota);

	public int delete(int id);

	public int getMaxId();

}
