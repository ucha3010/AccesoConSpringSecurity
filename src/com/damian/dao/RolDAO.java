package com.damian.dao;

import java.util.List;

import com.damian.pojo.Rol;

public interface RolDAO {

	public void save(Rol rol);

	public List<Rol> findAll();
	
	public void update(Rol rol);
	
	public void delete(Rol rol);

	public Rol findById(int id);
	
	public List<Rol> findByRolName(String rolName);

}
