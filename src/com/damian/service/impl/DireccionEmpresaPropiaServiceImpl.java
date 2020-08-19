package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.DireccionEmpresaPropiaDAO;
import com.damian.pojo.DireccionEmpresaPropia;
import com.damian.service.DireccionEmpresaPropiaService;
import com.damian.utils.Utils;

@Service
public class DireccionEmpresaPropiaServiceImpl implements DireccionEmpresaPropiaService {

	@Autowired
	private DireccionEmpresaPropiaDAO direccionEmpresaPropiaDao;

	@Override
	public List<DireccionEmpresaPropia> findAll() {
		return direccionEmpresaPropiaDao.findAll();
	}

	@Override
	public DireccionEmpresaPropia findById(int id) {
		return direccionEmpresaPropiaDao.findById(id);
	}

	@Override
	public DireccionEmpresaPropia findByIdModel(int id) {
		return direccionEmpresaPropiaDao.findByIdModel(id);
	}

	@Override
	public List<DireccionEmpresaPropia> findByIdPropiaModel(int idPropia) {
		return direccionEmpresaPropiaDao.findByIdPropiaModel(idPropia);
	}

	@Override
	public void save(DireccionEmpresaPropia direccionEmpresaPropia, HttpServletRequest request) {

		direccionEmpresaPropia.setModificadoPor(Utils.getLoggedUser(request));
		direccionEmpresaPropia.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
		direccionEmpresaPropiaDao.save(direccionEmpresaPropia, request);
	}

	@Override
	public void update(DireccionEmpresaPropia direccionEmpresaPropia, HttpServletRequest request) {

		direccionEmpresaPropia.setModificadoPor(Utils.getLoggedUser(request));
		direccionEmpresaPropia.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
		direccionEmpresaPropiaDao.update(direccionEmpresaPropia, request);
	}

	@Override
	public void delete(int id, HttpServletRequest request) {
		direccionEmpresaPropiaDao.delete(id, request);
	}

	@Override
	public int getMaxId() {
		return direccionEmpresaPropiaDao.getMaxId();
	}

}
