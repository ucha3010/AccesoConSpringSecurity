package com.damian.dao;

import java.util.List;

import com.damian.pojo.Empresa;

public interface EmpresaDAO {

	public void save(Empresa empresa);

	public List<Empresa> findAll();
	
	public void update(Empresa empresa);
	
	public void delete(Empresa empresa);

	public Empresa findById(int id);
	
	public List<Empresa> findByEmpresaName(String empresaName);

}
