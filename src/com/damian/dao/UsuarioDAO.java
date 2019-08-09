package com.damian.dao;

import java.util.List;

import com.damian.pojo.Usuario;

public interface UsuarioDAO {
	
	public Usuario findByUsername(String usuario);

	public void save(Usuario usuario);

	public List<Usuario> findAll();

}
