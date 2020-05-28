package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.Empresa;

public interface EmpresaDAO {

	public void save(Empresa empresa, HttpServletRequest request);

	public List<Empresa> findAll(String column, int paginaInicio, int totalPaginas, HttpServletRequest request);

	public void update(Empresa empresa, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request);

	public Empresa findById(int id);

	public List<Empresa> findByIdList(int id);

	public List<Empresa> findByEmpresaName(String empresaName);

	public Empresa findByIdModel(int id);

	public int getMaxId();

	public List<Empresa> findSearchAll();
}
