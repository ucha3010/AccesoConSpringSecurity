package com.damian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.UsuarioEmpresaDAO;
import com.damian.pojo.UsuarioEmpresa;
import com.damian.service.UsuarioEmpresaService;

@Service("UsuarioEmpresaService")
public class UsuarioEmpresaServiceImpl implements UsuarioEmpresaService {

	@Autowired
	private UsuarioEmpresaDAO usuarioEmpresaDAO;

	@Override
	public void save(UsuarioEmpresa usuarioEmpresa) {
		usuarioEmpresaDAO.save(usuarioEmpresa);
	}

	@Override
	public List<UsuarioEmpresa> findAll() {
		return usuarioEmpresaDAO.findAll();
	}

	@Override
	public void update(UsuarioEmpresa usuarioEmpresa) {
		usuarioEmpresaDAO.update(usuarioEmpresa);
	}

	@Override
	public void delete(int idUsr, int idEmp) {
		List<UsuarioEmpresa> usuarioEmpresaList = findByIdUsr(idUsr);
		UsuarioEmpresa usuarioEmpresa = null;
		for (UsuarioEmpresa ur : usuarioEmpresaList) {
			if (ur.getPk().getEmpresa().getIdEmp() == idEmp) {
				usuarioEmpresa = ur;
			}
		}
		if (usuarioEmpresa != null) {
			usuarioEmpresaDAO.delete(usuarioEmpresa);
		}
	}

	@Override
	public List<UsuarioEmpresa> findByIdUsr(int idUsr) {
		return usuarioEmpresaDAO.findByIdUsr(idUsr);
	}

	@Override
	public List<UsuarioEmpresa> findByIdEmp(int idEmp) {
		return usuarioEmpresaDAO.findByIdEmp(idEmp);
	}

}
