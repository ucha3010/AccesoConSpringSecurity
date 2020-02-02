package com.damian.service;

import java.util.List;

import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Cuota;

public interface CuotaService {

	public List<Cuota> findAll();

	public Cuota findById(int id);

	public Cuota findByIdModel(int id);

	public int save(Cuota cuota);

	public int update(Cuota cuota);

	public int delete(int id) throws NotEmptyException;

}
