package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.exceptions.RepeatedUsernameException;
import com.damian.pojo.Usuario;

public interface UsuarioService {

	Usuario findById(int id);

	List<Usuario> findByIdList(int id);

	void save(Usuario usuario, String[] usuarioRol, HttpServletRequest request) throws RepeatedUsernameException;

	void saveChangePassword(Usuario usuario);

	List<Usuario> findAll(String column, String order, int paginaInicio, int totalPaginas, HttpServletRequest request);

	void update(Usuario usuario);

	void delete(int idUsr);

	List<Usuario> findCustomers(String column, String order, int paginaInicio, int totalPaginas,
			HttpServletRequest request);

	List<Usuario> findFilteredCustomers(int idUsr);

	void fillNewUser(Usuario usuario);

	void fillExistingUser(Usuario usuario);

	Usuario reset(int idUsr);

	public Usuario findByUsername(String usuario);

	public String getColumn(HttpServletRequest request);
	
	public List<Usuario> findSearchAll(boolean customer);

}
