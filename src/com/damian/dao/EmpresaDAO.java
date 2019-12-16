package com.damian.dao;

import java.util.List;

import com.damian.pojo.Empresa;

public interface EmpresaDAO {

	public void save(Empresa empresa);

	public List<Empresa> findAll();
	
	public void update(Empresa empresa);
	
	public int delete(int id);

	public Empresa findById(int id);
	
	public List<Empresa> findByIdList(int id);
	
	public List<Empresa> findByEmpresaName(String empresaName);

	public Empresa findByIdModel(int id);

}
