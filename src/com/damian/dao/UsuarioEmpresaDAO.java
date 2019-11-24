package com.damian.dao;

import java.util.List;

import com.damian.pojo.UsuarioEmpresa;

public interface UsuarioEmpresaDAO {

	public void save(UsuarioEmpresa usuarioEmpresa);

	public List<UsuarioEmpresa> findAll();
	
	public void update(UsuarioEmpresa usuarioEmpresa);
	
	public void delete(UsuarioEmpresa usuarioEmpresa);
	
	public List<UsuarioEmpresa> findByIdUsr(int idUsr);
	
	public List<UsuarioEmpresa> findByIdEmp(int idEmp);
	
	public List<UsuarioEmpresa> findByIdUsrModel(int idUsr);
	
	public List<UsuarioEmpresa> findByIdEmpModel(int idEmp);

}
