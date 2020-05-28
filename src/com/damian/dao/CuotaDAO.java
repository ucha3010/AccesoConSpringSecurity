package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.Cuota;

public interface CuotaDAO {

	public List<Cuota> findAll();

	public Cuota findById(int id);

	public Cuota findByIdModel(int id);

	public int save(Cuota cuota, HttpServletRequest request);

	public int update(Cuota cuota, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request);

	public int getMaxId();

}
