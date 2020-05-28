package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.UsuarioRol;

public interface UsuarioRolDAO {

	public void save(UsuarioRol usuarioRol, HttpServletRequest request);

	public List<UsuarioRol> findAll();
	
	public void update(UsuarioRol usuarioRol, HttpServletRequest request);
	
	public void delete(UsuarioRol usuarioRol, HttpServletRequest request);
	
	public List<UsuarioRol> findByIdUsr(int idUsr);
	
	public List<UsuarioRol> findByIdRol(int idRol);
	
	public List<UsuarioRol> findByIdUsrModel(int idUsr);
	
	public List<UsuarioRol> findByIdRolModel(int idRol);
	
	public UsuarioRol findByIdUsrAndIdRol(int idUsr, int idRol);

}
