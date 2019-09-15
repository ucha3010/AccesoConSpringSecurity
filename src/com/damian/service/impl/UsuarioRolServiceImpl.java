package com.damian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.UsuarioRolDAO;
import com.damian.pojo.UsuarioRol;
import com.damian.service.UsuarioRolService;

@Service("UsuarioRolService")
public class UsuarioRolServiceImpl implements UsuarioRolService {

	@Autowired
	private UsuarioRolDAO usuarioRolDAO;

	@Override
	public void save(UsuarioRol usuarioRol) {
		usuarioRolDAO.save(usuarioRol);
	}

	@Override
	public List<UsuarioRol> findAll() {
		return usuarioRolDAO.findAll();
	}

	@Override
	public void update(UsuarioRol usuarioRol) {
		usuarioRolDAO.update(usuarioRol);
	}

	@Override
	public void delete(int idUsr, int idRol) {
		List<UsuarioRol> usuarioRolList = findByIdUsr(idUsr);
		UsuarioRol usuarioRol = null;
		for (UsuarioRol ur : usuarioRolList) {
			if (ur.getPk().getRol().getIdRol() == idRol) {
				usuarioRol = ur;
			}
		}
		if (usuarioRol != null) {
			usuarioRolDAO.delete(usuarioRol);
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

}
