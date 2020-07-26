package com.damian.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.UsuarioEmpresaDAO;
import com.damian.pojo.Empresa;
import com.damian.pojo.Usuario;
import com.damian.pojo.UsuarioEmpresa;
import com.damian.service.UsuarioEmpresaService;
import com.damian.utils.Utils;

@Service
public class UsuarioEmpresaServiceImpl implements UsuarioEmpresaService {

	@Autowired
	private UsuarioEmpresaDAO usuarioEmpresaDAO;

	@Override
	public void save(int idUsr, int idEmp, HttpServletRequest request) {

		Usuario usuario = new Usuario();
		usuario.setIdUsr(idUsr);
		Empresa empresa = new Empresa();
		empresa.setIdEmp(idEmp);
		UsuarioEmpresa usuarioEmpresa = new UsuarioEmpresa();
		usuarioEmpresa.setUsuario(usuario);
		usuarioEmpresa.setEmpresa(empresa);
		usuarioEmpresa.setCreadoPor(Utils.getLoggedUser(request));
		usuarioEmpresa.setFechaCreacion(new Date());
		usuarioEmpresaDAO.save(usuarioEmpresa, request);
	}

	@Override
	public List<UsuarioEmpresa> findAll() {
		return usuarioEmpresaDAO.findAll();
	}

	@Override
	public void update(UsuarioEmpresa usuarioEmpresa, HttpServletRequest request) {
		usuarioEmpresaDAO.update(usuarioEmpresa, request);
	}

	@Override
	public void delete(int idUsr, int idEmp, HttpServletRequest request) {
		UsuarioEmpresa usuarioEmpresa = findByIdUsrAndIdEmp(idUsr, idEmp);
		if (usuarioEmpresa != null) {
			usuarioEmpresaDAO.delete(usuarioEmpresa, request);
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

	@Override
	public UsuarioEmpresa findByIdUsrAndIdEmp(int idUsr, int idEmp) {
		return usuarioEmpresaDAO.findByIdUsrAndIdEmp(idUsr, idEmp);
	}

}
