package com.damian.dao;

import com.damian.dao.model.ModelUsuarioOrden;

public interface UsuarioOrdenDAO {

	public ModelUsuarioOrden findByIdUsrTabla(int idUsr, String tabla);

	public int save(ModelUsuarioOrden uo);

	public int update(ModelUsuarioOrden uo);

	public int getMaxId();

}
