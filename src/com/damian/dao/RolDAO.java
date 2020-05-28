package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.Rol;

public interface RolDAO {

	public void save(Rol rol, HttpServletRequest request);

	public List<Rol> findAll();
	
	public void delete(int id, HttpServletRequest request);

	public Rol findById(int id);
	
	public List<Rol> findByRolName(String rolName);

	public Rol findByIdModel(int id);

	public int getMaxId();

}
