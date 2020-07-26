package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.DireccionDao;
import com.damian.pojo.DatosPersonales;
import com.damian.pojo.Direccion;
import com.damian.service.DatosPersonalesService;
import com.damian.service.DireccionService;
import com.damian.utils.Utils;

@Service
public class DireccionServiceImpl implements DireccionService {

	@Autowired
	private DireccionDao direccionDao;

	@Autowired
	private DatosPersonalesService datosPersonalesService;

	@Override
	public Direccion findById(int idDir) {
		return direccionDao.findById(idDir);
	}

	@Override
	public int save(int idUsr, Direccion direccion, HttpServletRequest request) {

		DatosPersonales datosPersonales = datosPersonalesService.findByUsrId(idUsr);
		direccion.setDatosPersonales(datosPersonales);
		direccion.setModificadoPor(Utils.getLoggedUser(request));
		direccion.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
		direccionDao.save(direccion, request);
		return direccionDao.getMaxId();

	}

	@Override
	public List<Direccion> findListFromUsuario(int idUsr) {

		DatosPersonales datosPersonales = datosPersonalesService.findByUsrId(idUsr);
		return direccionDao.findListFromUsuario(datosPersonales.getIdDatosPers());
	}

	@Override
	public void delete(int idDir, HttpServletRequest request) {
		direccionDao.delete(idDir, request);
	}

}
