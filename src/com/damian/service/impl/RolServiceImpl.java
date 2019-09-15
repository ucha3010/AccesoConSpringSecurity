package com.damian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.RolDAO;
import com.damian.pojo.Rol;
import com.damian.service.RolService;

@Service
public class RolServiceImpl implements RolService {
	
	@Autowired
	private RolDAO rolDAO;
	
	public Rol findById(int id) {
		return rolDAO.findById(id);
	}

	public void save(Rol rol) {
		rolDAO.save(rol);
	}

	public List<Rol> findAll() {
		return rolDAO.findAll();
	}
	
	public void update(Rol rol) {
		rolDAO.update(rol);
	}
	
	public void delete(int idUsr) {
		Rol rol = findById(idUsr);
		rolDAO.delete(rol);
	}

	@Override
	public List<Rol> findByRolName(String rolName) {
		return rolDAO.findByRolName(rolName);
	}

}
