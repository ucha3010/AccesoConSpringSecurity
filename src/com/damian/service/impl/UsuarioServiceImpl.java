package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.damian.dao.UsuarioDAO;
import com.damian.pojo.Usuario;
import com.damian.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Usuario findById(int id) {
		return usuarioDAO.findById(id);
	}
	
	public Usuario findByUsername(String usuario) {
		return usuarioDAO.findByUsername(usuario);
	}

	public void save(Usuario usuario) {
		usuario.setFechaCreacion(new Timestamp(new Date().getTime()));
		usuario.setHabilitado(true);
		String claveUsr = usuario.getClave();
		usuario.setClave(passwordEncoder.encode(claveUsr));
		usuarioDAO.save(usuario);
	}

	public List<Usuario> findAll() {
		return usuarioDAO.findAll();
	}
	
	public void update(Usuario usuario) {
		usuarioDAO.update(usuario);
	}
	
	public void delete(int idUsr) {
		Usuario usuario = findById(idUsr);
		usuarioDAO.delete(usuario);
	}

}
