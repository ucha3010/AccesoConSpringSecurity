package com.damian.service;

import java.util.List;

import com.damian.pojo.DireccionEmpresa;

public interface DireccionEmpresaService {
	
	DireccionEmpresa findById(int idDirEmp);
	
	void save(int idEmp, DireccionEmpresa direccionEmpresa);

	List<DireccionEmpresa> findListFromEmpresa(int idEmp);
	
	void delete(int idDirEmp);

}
