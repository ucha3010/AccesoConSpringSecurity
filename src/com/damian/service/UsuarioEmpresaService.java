package com.damian.service;

import java.util.List;

import com.damian.pojo.UsuarioEmpresa;

public interface UsuarioEmpresaService {
	
	void save(UsuarioEmpresa usuarioEmpresa);

	List<UsuarioEmpresa> findAll();
	
	void update(UsuarioEmpresa usuarioEmpresa);
	
	void delete(int idUsr, int idRol);
	
	List<UsuarioEmpresa> findByIdUsr(int idUsr);
	
	List<UsuarioEmpresa> findByIdEmp(int idEmp);

}
