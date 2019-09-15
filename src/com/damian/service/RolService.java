package com.damian.service;

import java.util.List;

import com.damian.pojo.Rol;

public interface RolService {
	
	Rol findById(int id);

	void save(Rol rol);

	List<Rol> findAll();
	
	void update(Rol rol);
	
	void delete(int idRol);
	
	List<Rol> findByRolName(String rolName);

}
