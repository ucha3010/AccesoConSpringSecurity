package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.DatosPersonales;

public interface DatosPersonalesService {

	DatosPersonales findById(int id);

	void save(DatosPersonales datosPersonales, HttpServletRequest request);

	List<DatosPersonales> findAll();

	void update(DatosPersonales datosPersonales, HttpServletRequest request);

	void delete(int idDatosPersonales, HttpServletRequest request);

	DatosPersonales findByUsrId(int datosUsrId);

	DatosPersonales findByUsrIdSearch(int datosUsrId);

}
