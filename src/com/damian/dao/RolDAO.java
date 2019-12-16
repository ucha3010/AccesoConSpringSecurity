package com.damian.dao;

import java.util.List;

import com.damian.pojo.Rol;

public interface RolDAO {

	public void save(Rol rol);

	public List<Rol> findAll();
	
	public void delete(int id);

	public Rol findById(int id);
	
	public List<Rol> findByRolName(String rolName);

	public Rol findByIdModel(int id);

}
