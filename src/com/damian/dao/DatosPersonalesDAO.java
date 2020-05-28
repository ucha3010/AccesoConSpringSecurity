package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.dao.model.ModelDatosPersonales;
import com.damian.pojo.DatosPersonales;

public interface DatosPersonalesDAO {

	public void save(DatosPersonales datosPersonales, HttpServletRequest request);

	public List<DatosPersonales> findAll();

	public void update(DatosPersonales datosPersonales, HttpServletRequest request);

	public void delete(int id, HttpServletRequest request);

	public DatosPersonales findById(int id);

	public DatosPersonales findByUsrId(int datosUsrId);

	public ModelDatosPersonales findModelById(int id);

	public DatosPersonales findByIdModel(int id);

	public DatosPersonales findByUsrIdModel(int datosUsrId);

	public int getMaxId();
	
	public DatosPersonales findByUsrIdSearch(int datosUsrId);

}
