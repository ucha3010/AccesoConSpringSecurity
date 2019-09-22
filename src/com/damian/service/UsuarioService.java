package com.damian.service;

import java.util.List;

import com.damian.pojo.Usuario;

public interface UsuarioService {
	
	Usuario findById(int id);

	void save(Usuario usuario);
	
	void saveChangePassword(Usuario usuario);

	List<Usuario> findAll();
	
	void update(Usuario usuario);
	
	void delete(int idUsr);
	
	List<Usuario> findCustomers();
	
	void fillNewUser(Usuario usuario);

}
