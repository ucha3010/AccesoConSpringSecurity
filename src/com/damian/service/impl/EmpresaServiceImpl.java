package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.EmpresaDAO;
import com.damian.pojo.DireccionEmpresa;
import com.damian.pojo.Empresa;
import com.damian.pojo.ProductoEmpresa;
import com.damian.pojo.UsuarioEmpresa;
import com.damian.service.DireccionEmpresaService;
import com.damian.service.EmpresaService;
import com.damian.service.ProductoEmpresaService;
import com.damian.service.UsuarioEmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaDAO empresaDAO;

	@Autowired
	private UsuarioEmpresaService usuarioEmpresaService;

	@Autowired
	private DireccionEmpresaService direccionEmpresaService;

	@Autowired
	private ProductoEmpresaService productoEmpresaService;

	@Override
	public Empresa findById(int id) {
		return empresaDAO.findById(id);
	}

	@Override
	public List<Empresa> findByIdList(int id) {
		return empresaDAO.findByIdList(id);
	}

	@Override
	public void save(Empresa empresa, HttpServletRequest request) {

		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		empresa.setModificadoPor(context.getAuthentication().getPrincipal().toString());
		empresa.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		empresaDAO.save(empresa);
	}

	@Override
	public List<Empresa> findAll(String column, int paginaInicio, int totalPaginas, HttpServletRequest request) {
		return empresaDAO.findAll(column, paginaInicio, totalPaginas, request);
	}

	@Override
	public void update(Empresa empresa, HttpServletRequest request) {

		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		empresa.setModificadoPor(context.getAuthentication().getPrincipal().toString());
		empresa.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		empresaDAO.update(empresa);
	}

	@Override
	public boolean delete(int idEmp) {
		List<UsuarioEmpresa> usuarioEmpresas = usuarioEmpresaService.findByIdEmp(idEmp);
		if (usuarioEmpresas != null) {
			for (UsuarioEmpresa usuarioEmpresa : usuarioEmpresas) {
				usuarioEmpresaService.delete(usuarioEmpresa.getUsuario().getIdUsr(), idEmp);
			}
		}
		List<DireccionEmpresa> direccionEmpresaList = direccionEmpresaService.findListFromEmpresa(idEmp);
		if (direccionEmpresaList != null) {
			for (DireccionEmpresa direccionEmpresa : direccionEmpresaList) {
				direccionEmpresaService.delete(direccionEmpresa.getIdDirEmp());
			}
		}
		List<ProductoEmpresa> peList = productoEmpresaService.findByIdEmp(idEmp);
		for (ProductoEmpresa pe : peList) {
			productoEmpresaService.delete(pe.getProducto().getIdPro(), idEmp);
		}
		int borrado = empresaDAO.delete(idEmp);
		if (borrado == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Empresa> findByEmpresaName(String empresaName) {
		return empresaDAO.findByEmpresaName(empresaName);
	}

	@Override
	public List<Empresa> findSearchAll() {
		return empresaDAO.findSearchAll();
	}

}
