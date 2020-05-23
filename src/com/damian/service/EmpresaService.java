package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.Empresa;

public interface EmpresaService {

	Empresa findById(int id);

	List<Empresa> findByIdList(int id);

	void save(Empresa empresa, HttpServletRequest request);

	List<Empresa> findAll(String column, int paginaInicio, int totalPaginas, HttpServletRequest request);

	void update(Empresa empresa, HttpServletRequest request);

	boolean delete(int idEmpresa);

	List<Empresa> findByEmpresaName(String empresaName);

	public List<Empresa> findSearchAll();

}
