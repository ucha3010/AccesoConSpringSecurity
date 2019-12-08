package com.damian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.DireccionEmpresaDAO;
import com.damian.pojo.DireccionEmpresa;
import com.damian.pojo.Empresa;
import com.damian.service.DireccionEmpresaService;
import com.damian.service.EmpresaService;

@Service
public class DireccionEmpresaServiceImpl implements DireccionEmpresaService {

	@Autowired
	private DireccionEmpresaDAO direccionEmpresaDao;

	@Autowired
	private EmpresaService empresaService;

	@Override
	public DireccionEmpresa findById(int idDirEmp) {
		return direccionEmpresaDao.findById(idDirEmp);
	}

	@Override
	public void save(int idEmp, DireccionEmpresa direccionEmpresa) {

		Empresa empresa = new Empresa();
		empresa.setIdEmp(idEmp);
		direccionEmpresa.setEmpresa(empresa);
		direccionEmpresaDao.save(direccionEmpresa);

	}

	@Override
	public List<DireccionEmpresa> findListFromEmpresa(int idEmp) {

		return direccionEmpresaDao.findByIdEmp(idEmp);
	}

	@Override
	public void delete(int idDirEmp) {
		DireccionEmpresa direccionEmpresa = findById(idDirEmp);
		direccionEmpresaDao.delete(direccionEmpresa);
	}

}
