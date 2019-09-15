package com.damian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.DatosPersonalesDAO;
import com.damian.pojo.DatosPersonales;
import com.damian.service.DatosPersonalesService;

@Service("DatosPersonalesService")
public class DatosPersonalesServiceImpl implements DatosPersonalesService {
	
	@Autowired
	private DatosPersonalesDAO datosPersonalesDAO;
	
	public DatosPersonales findById(int id) {
		return datosPersonalesDAO.findById(id);
	}

	public void save(DatosPersonales datosPersonales) {
		datosPersonalesDAO.save(datosPersonales);
	}

	public List<DatosPersonales> findAll() {
		return datosPersonalesDAO.findAll();
	}
	
	public void update(DatosPersonales datosPersonales) {
		datosPersonalesDAO.update(datosPersonales);
	}
	
	public void delete(int idUsr) {
		DatosPersonales datosPersonales = findById(idUsr);
		datosPersonalesDAO.delete(datosPersonales);
	}

	@Override
	public DatosPersonales findByUsrId(int datosUsrId) {
		return datosPersonalesDAO.findByUsrId(datosUsrId);
	}

}
