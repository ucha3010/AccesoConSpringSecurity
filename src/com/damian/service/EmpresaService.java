package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.Empresa;

public interface EmpresaService {

	Empresa findById(int id);

	List<Empresa> findByIdList(int id);

	void save(Empresa empresa);

	List<Empresa> findAll(String column, HttpServletRequest request);

	void update(Empresa empresa);

	boolean delete(int idEmpresa);

	List<Empresa> findByEmpresaName(String empresaName);

}
