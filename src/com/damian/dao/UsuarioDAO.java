package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.Usuario;

public interface UsuarioDAO {

	public Usuario findByUsername(String usuario);

	public void save(Usuario usuario);

	public List<Usuario> findAll(boolean customer, String column, String order, int paginaInicio, int totalPaginas,
			HttpServletRequest request);

	public void update(Usuario usuario);

	public void delete(int id);

	public Usuario findById(int id);

	public List<Usuario> findByIdList(int id);

	public Usuario findByIdModel(int id);

	public int getMaxId();

	public List<Usuario> findSearchAll(boolean customer);

}
