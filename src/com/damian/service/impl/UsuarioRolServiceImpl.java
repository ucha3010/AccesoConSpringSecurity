package com.damian.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.UsuarioRolDAO;
import com.damian.pojo.UsuarioRol;
import com.damian.service.UsuarioRolService;

@Service
public class UsuarioRolServiceImpl implements UsuarioRolService {

	@Autowired
	private UsuarioRolDAO usuarioRolDAO;

	@Override
	public void save(UsuarioRol usuarioRol, HttpServletRequest request) {
		usuarioRolDAO.save(usuarioRol, request);
	}

	@Override
	public List<UsuarioRol> findAll() {
		return usuarioRolDAO.findAll();
	}

	@Override
	public void update(UsuarioRol usuarioRol, HttpServletRequest request) {
		usuarioRolDAO.update(usuarioRol, request);
	}

	@Override
	public void delete(int idUsr, int idRol, HttpServletRequest request) {
		UsuarioRol usuarioRol = findByIdUsrAndIdRol(idUsr, idRol);
		if (usuarioRol != null) {
			usuarioRolDAO.delete(usuarioRol, request);
		}
	}

	@Override
	public List<UsuarioRol> findByIdUsr(int idUsr) {
		return usuarioRolDAO.findByIdUsr(idUsr);
	}

	@Override
	public List<UsuarioRol> findByIdRol(int idRol) {
		return usuarioRolDAO.findByIdRol(idRol);
	}

	@Override
	public UsuarioRol findByIdUsrAndIdRol(int idUsr, int idRol) {
		return usuarioRolDAO.findByIdUsrAndIdRol(idUsr, idRol);
	}

}
