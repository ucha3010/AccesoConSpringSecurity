package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.UsuarioEmpresa;

public interface UsuarioEmpresaService {

	void save(int idUsr, int idEmp, HttpServletRequest request);

	List<UsuarioEmpresa> findAll();

	void update(UsuarioEmpresa usuarioEmpresa, HttpServletRequest request);

	void delete(int idUsr, int idEmp, HttpServletRequest request);

	List<UsuarioEmpresa> findByIdUsr(int idUsr);

	List<UsuarioEmpresa> findByIdEmp(int idEmp);

	UsuarioEmpresa findByIdUsrAndIdEmp(int idUsr, int idEmp);

}
