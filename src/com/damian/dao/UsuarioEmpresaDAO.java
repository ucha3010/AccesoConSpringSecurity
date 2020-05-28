package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.UsuarioEmpresa;

public interface UsuarioEmpresaDAO {

	public void save(UsuarioEmpresa usuarioEmpresa, HttpServletRequest request);

	public List<UsuarioEmpresa> findAll();

	public void update(UsuarioEmpresa usuarioEmpresa, HttpServletRequest request);

	public void delete(UsuarioEmpresa usuarioEmpresa, HttpServletRequest request);

	public List<UsuarioEmpresa> findByIdUsr(int idUsr);

	public List<UsuarioEmpresa> findByIdEmp(int idEmp);

	public List<UsuarioEmpresa> findByIdUsrModel(int idUsr);

	public List<UsuarioEmpresa> findByIdEmpModel(int idEmp);

	public UsuarioEmpresa findByIdUsrAndIdEmp(int idUsr, int idEmp);

}
