package com.damian.dao;

import javax.servlet.http.HttpServletRequest;

import com.damian.dao.model.ModelUsuarioOrden;

public interface UsuarioOrdenDAO {

	public ModelUsuarioOrden findByIdUsrTabla(int idUsr, String tabla);

	public int save(ModelUsuarioOrden uo, HttpServletRequest request);

	public int update(ModelUsuarioOrden uo, HttpServletRequest request);

	public int getMaxId();

}
