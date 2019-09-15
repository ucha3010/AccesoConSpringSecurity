package com.damian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.DatosPersonalesDAO;
import com.damian.dao.DireccionDao;
import com.damian.pojo.DatosPersonales;
import com.damian.pojo.Direccion;
import com.damian.service.DireccionService;

@Service("DireccionService")
public class DireccionServiceImpl implements DireccionService {

	@Autowired
	private DatosPersonalesDAO datosPersonalesDAO;

	@Autowired
	private DireccionDao direccionDao;

	@Override
	public Direccion findById(int idDir) {
		return direccionDao.findById(idDir);
	}

	@Override
	public void save(DatosPersonales datosPersonales, Direccion direccion) {

		direccion.setDatosPersonales(datosPersonales);
		direccionDao.save(direccion);

	}

	@Override
	public List<Direccion> findListFromUsuario(int idUsr) {

		DatosPersonales datosPersonales = datosPersonalesDAO.findByUsrId(idUsr);
		return direccionDao.findListFromUsuario(datosPersonales.getIdDatosPers());
	}

}
