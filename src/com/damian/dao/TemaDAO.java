package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.Tema;

public interface TemaDAO {

	public List<Tema> findAll();

	public Tema findById(int id);

	public int save(Tema tema, HttpServletRequest request);

	public int update(Tema tema, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request);

	public int getMaxId();

}
