package com.damian.service;

import java.util.List;

import com.damian.pojo.UsuarioRol;

public interface UsuarioRolService {

	void save(UsuarioRol usuarioRol);

	List<UsuarioRol> findAll();

	void update(UsuarioRol usuarioRol);

	void delete(int idUsr, int idRol);

	List<UsuarioRol> findByIdUsr(int idUsr);

	List<UsuarioRol> findByIdRol(int idRol);

	UsuarioRol findByIdUsrAndIdRol(int idUsr, int idRol);

}
