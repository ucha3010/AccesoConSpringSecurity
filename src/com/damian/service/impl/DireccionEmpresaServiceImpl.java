package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.DireccionEmpresaDAO;
import com.damian.pojo.DireccionEmpresa;
import com.damian.pojo.Empresa;
import com.damian.service.DireccionEmpresaService;
import com.damian.utils.Utils;

@Service
public class DireccionEmpresaServiceImpl implements DireccionEmpresaService {

	@Autowired
	private DireccionEmpresaDAO direccionEmpresaDao;

	@Override
	public DireccionEmpresa findById(int idDirEmp) {
		return direccionEmpresaDao.findById(idDirEmp);
	}

	@Override
	public void save(int idEmp, DireccionEmpresa direccionEmpresa, HttpServletRequest request) {

		Empresa empresa = new Empresa();
		empresa.setIdEmp(idEmp);
		direccionEmpresa.setEmpresa(empresa);
		direccionEmpresa.setModificadoPor(Utils.getLoggedUser(request));
		direccionEmpresa.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		direccionEmpresaDao.save(direccionEmpresa, request);

	}

	@Override
	public List<DireccionEmpresa> findListFromEmpresa(int idEmp) {

		return direccionEmpresaDao.findByIdEmp(idEmp);
	}

	@Override
	public void delete(int idDirEmp, HttpServletRequest request) {
		direccionEmpresaDao.delete(idDirEmp, request);
	}

}
