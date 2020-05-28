package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.DatosPersonalesDAO;
import com.damian.pojo.DatosPersonales;
import com.damian.service.DatosPersonalesService;

@Service
public class DatosPersonalesServiceImpl implements DatosPersonalesService {

	@Autowired
	private DatosPersonalesDAO datosPersonalesDAO;

	public DatosPersonales findById(int id) {
		return datosPersonalesDAO.findById(id);
	}

	public void save(DatosPersonales datosPersonales, HttpServletRequest request) {

		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		datosPersonales.setModificadoPor(context.getAuthentication().getPrincipal().toString());
		datosPersonales.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		datosPersonalesDAO.save(datosPersonales, request);
	}

	public List<DatosPersonales> findAll() {
		return datosPersonalesDAO.findAll();
	}

	public void update(DatosPersonales datosPersonales, HttpServletRequest request) {

		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		datosPersonales.setModificadoPor(context.getAuthentication().getPrincipal().toString());
		datosPersonales.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

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

}
