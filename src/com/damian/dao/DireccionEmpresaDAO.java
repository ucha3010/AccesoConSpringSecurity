package com.damian.dao;

import java.util.List;

import com.damian.pojo.DireccionEmpresa;

public interface DireccionEmpresaDAO {
	
	DireccionEmpresa findById(int id);
	
	List<DireccionEmpresa> findAll();
	
	void save(DireccionEmpresa direccionEmpresa);
	
	void update(DireccionEmpresa direccionEmpresa);
	
	void delete(int id);
	
	List<DireccionEmpresa> findByIdEmp(int idEmp);
	
	DireccionEmpresa findByIdModel(int id);
	
	List<DireccionEmpresa> findByIdEmpModel(int idEmp);

}
