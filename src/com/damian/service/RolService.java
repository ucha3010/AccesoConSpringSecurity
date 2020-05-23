package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.Rol;

public interface RolService {

	Rol findById(int id);

	void save(Rol rol, HttpServletRequest request);

	List<Rol> findAll();

	void delete(int idRol);

	List<Rol> findByRolName(String rolName);

}
