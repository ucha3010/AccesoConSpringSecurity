package com.damian.service;

import java.util.List;

import com.damian.pojo.DatosPersonales;

public interface DatosPersonalesService {
	
	DatosPersonales findById(int id);

	void save(DatosPersonales datosPersonales);

	List<DatosPersonales> findAll();
	
	void update(DatosPersonales datosPersonales);
	
	void delete(int idDatosPersonales);
	
	DatosPersonales findByUsrId(int datosUsrId);

}
