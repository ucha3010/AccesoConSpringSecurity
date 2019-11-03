package com.damian.service;

import java.util.List;

import com.damian.pojo.Empresa;

public interface EmpresaService {

	Empresa findById(int id);

	List<Empresa> findByIdList(int id);

	void save(Empresa empresa);

	List<Empresa> findAll();

	void update(Empresa empresa);

	void delete(int idEmpresa);

	List<Empresa> findByEmpresaName(String empresaName);

}
