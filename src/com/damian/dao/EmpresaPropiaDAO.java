package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.EmpresaPropia;

public interface EmpresaPropiaDAO {

	public List<EmpresaPropia> findAll();

	public EmpresaPropia findById(int id);

	public EmpresaPropia findByIdModel(int id);

	public int save(EmpresaPropia estado, HttpServletRequest request);

	public int update(EmpresaPropia estado, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request);

	public int getMaxId();

}
