package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.DireccionEmpresa;

public interface DireccionEmpresaDAO {
	
	DireccionEmpresa findById(int id);
	
	List<DireccionEmpresa> findAll();
	
	void save(DireccionEmpresa direccionEmpresa, HttpServletRequest request);
	
	void update(DireccionEmpresa direccionEmpresa, HttpServletRequest request);
	
	void delete(int id, HttpServletRequest request);
	
	List<DireccionEmpresa> findByIdEmp(int idEmp);
	
	DireccionEmpresa findByIdModel(int id);
	
	List<DireccionEmpresa> findByIdEmpModel(int idEmp);

	public int getMaxId();

}
