package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.Campania;

public interface CampaniaDAO {

	public List<Campania> findAll();

	public Campania findById(int id);

	public int save(Campania campania, HttpServletRequest request);

	public int update(Campania campania, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request);

}
