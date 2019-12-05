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

	List<Usuario> findAll();

	List<Usuario> findAllOrderByNombre();

	void update(Usuario usuario);

	void delete(int idUsr);

	List<Usuario> findCustomers();

	List<Usuario> findFilteredCustomers(int idUsr);

	void fillNewUser(Usuario usuario);

	void fillExistingUser(Usuario usuario);

	Usuario reset(int idUsr);

	public Usuario findByUsername(String usuario);

}
