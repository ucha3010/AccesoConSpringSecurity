package com.damian.dao;

import java.util.List;

import com.damian.pojo.UsuarioRol;

public interface UsuarioRolDAO {

	public void save(UsuarioRol usuarioRol);

	public List<UsuarioRol> findAll();
	
	public void update(UsuarioRol usuarioRol);
	
	public void delete(UsuarioRol usuarioRol);
	
	public List<UsuarioRol> findByIdUsr(int idUsr);
	
	public List<UsuarioRol> findByIdRol(int idRol);
	
	public List<UsuarioRol> findByIdUsrModel(int idUsr);
	
	public List<UsuarioRol> findByIdRolModel(int idRol);

}
