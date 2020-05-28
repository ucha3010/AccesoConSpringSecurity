package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.Estado;

public interface EstadoDAO {

	public List<Estado> findAll();

	public Estado findById(int id);

	public Estado findByIdModel(int id);

	public int save(Estado estado, HttpServletRequest request);

	public int update(Estado estado, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request);

	public int getMaxId();

}
