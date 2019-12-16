package com.damian.dao;

import java.util.List;

import com.damian.pojo.Direccion;

public interface DireccionDao {

	public Direccion findById(int idDir);
	
	public void save(Direccion direccion);
	
	public List<Direccion> findListFromUsuario(int idDatosPers);
	
	public void delete(int id);

	public Direccion findByIdModel(int id);
	
	public List<Direccion> findListFromUsuarioModel(int idDatosPers);

}
