package com.damian.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.EmpresaDAO;
import com.damian.pojo.DireccionEmpresa;
import com.damian.pojo.Empresa;
import com.damian.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaDAO empresaDAO;

	@Override
	public Empresa findById(int id) {
		return empresaDAO.findById(id);
	}

	@Override
	public List<Empresa> findByIdList(int id) {
		return empresaDAO.findByIdList(id);
	}

	@Override
	public void save(Empresa empresa) {
		if (empresa.getIdEmp() == 0) {
			empresa.setDireccionesEmpresa(new ArrayList<DireccionEmpresa>());
		}
		empresaDAO.save(empresa);
	}

	@Override
	public List<Empresa> findAll() {
		return empresaDAO.findAll();
	}

	@Override
	public void update(Empresa empresa) {
		empresaDAO.update(empresa);
	}

	@Override
	public void delete(int idEmp) {
		Empresa empresa = findById(idEmp);
		empresaDAO.delete(empresa);
	}

	@Override
	public List<Empresa> findByEmpresaName(String empresaName) {
		return empresaDAO.findByEmpresaName(empresaName);
	}

}
