package com.damian.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.EmpresaPropiaDAO;
import com.damian.pojo.EmpresaPropia;
import com.damian.service.DireccionEmpresaService;
import com.damian.service.EmpresaPropiaService;

@Service
public class EmpresaPropiaServiceImpl implements EmpresaPropiaService {

	@Autowired
	private EmpresaPropiaDAO empresaPropiaDAO;

	@Autowired
	private DireccionEmpresaService direccionEmpresaService;

	@Override
	public List<EmpresaPropia> findAll() {
		return empresaPropiaDAO.findAll();
	}

	@Override
	public EmpresaPropia findById(int id) {
		return empresaPropiaDAO.findById(id);
	}

	@Override
	public EmpresaPropia findByIdModel(int id) {
		return empresaPropiaDAO.findByIdModel(id);
	}

	@Override
	public int save(EmpresaPropia empresaPropia, HttpServletRequest request) {
		return empresaPropiaDAO.save(empresaPropia, request);
	}

	@Override
	public int update(EmpresaPropia empresaPropia, HttpServletRequest request) {
		return empresaPropiaDAO.update(empresaPropia, request);
	}

	@Override
	public int delete(int id, HttpServletRequest request) {
		EmpresaPropia empresaPropia = findByIdModel(id);
		if (empresaPropia.getDireccionEmpresa().getIdDirEmp() != 0) {
			direccionEmpresaService.delete(empresaPropia.getDireccionEmpresa().getIdDirEmp(), request);
		}
		return empresaPropiaDAO.delete(id, request);
	}

}
