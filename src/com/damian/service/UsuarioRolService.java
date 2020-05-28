package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.UsuarioRol;

public interface UsuarioRolService {

	void save(UsuarioRol usuarioRol, HttpServletRequest request);

	List<UsuarioRol> findAll();

	void update(UsuarioRol usuarioRol, HttpServletRequest request);

	void delete(int idUsr, int idRol, HttpServletRequest request);

	List<UsuarioRol> findByIdUsr(int idUsr);

	List<UsuarioRol> findByIdRol(int idRol);

	UsuarioRol findByIdUsrAndIdRol(int idUsr, int idRol);

}
