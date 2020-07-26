package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.DatosPersonalesDAO;
import com.damian.pojo.DatosPersonales;
import com.damian.service.DatosPersonalesService;
import com.damian.utils.Utils;

@Service
public class DatosPersonalesServiceImpl implements DatosPersonalesService {

	@Autowired
	private DatosPersonalesDAO datosPersonalesDAO;

	public DatosPersonales findById(int id) {
		return datosPersonalesDAO.findById(id);
	}

	public void save(DatosPersonales datosPersonales, HttpServletRequest request) {

		fillModificadoPor(datosPersonales, request);
		datosPersonalesDAO.save(datosPersonales, request);
	}

	public List<DatosPersonales> findAll() {
		return datosPersonalesDAO.findAll();
	}

	public void update(DatosPersonales datosPersonales, HttpServletRequest request) {

		fillModificadoPor(datosPersonales, request);
		datosPersonalesDAO.update(datosPersonales, request);
	}

	public void delete(int idUsr, HttpServletRequest request) {
		DatosPersonales datosPersonales = findById(idUsr);
		datosPersonalesDAO.delete(datosPersonales.getIdDatosPers(), request);
	}

	@Override
	public DatosPersonales findByUsrId(int datosUsrId) {
		return datosPersonalesDAO.findByUsrId(datosUsrId);
	}

	@Override
	public DatosPersonales findByUsrIdSearch(int datosUsrId) {
		return datosPersonalesDAO.findByUsrIdSearch(datosUsrId);
	}

	private void fillModificadoPor(DatosPersonales datosPersonales, HttpServletRequest request) {

		datosPersonales.setModificadoPor(Utils.getLoggedUser(request));
		datosPersonales.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

	}

}
