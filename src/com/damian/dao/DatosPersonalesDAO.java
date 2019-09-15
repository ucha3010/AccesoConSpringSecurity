package com.damian.dao;

import java.util.List;

import com.damian.pojo.DatosPersonales;

public interface DatosPersonalesDAO {

	public void save(DatosPersonales datosPersonales);

	public List<DatosPersonales> findAll();
	
	public void update(DatosPersonales datosPersonales);
	
	public void delete(DatosPersonales datosPersonales);

	public DatosPersonales findById(int id);
	
	public DatosPersonales findByUsrId(int datosUsrId);

}
