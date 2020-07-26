package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.RolDAO;
import com.damian.pojo.Rol;
import com.damian.service.RolService;
import com.damian.utils.Utils;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	private RolDAO rolDAO;

	public Rol findById(int id) {
		return rolDAO.findById(id);
	}

	public void save(Rol rol, HttpServletRequest request) {

		rol.setModificadoPor(Utils.getLoggedUser(request));
		rol.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		rolDAO.save(rol, request);
	}

	public List<Rol> findAll() {
		return rolDAO.findAll();
	}

	public void delete(int idRol, HttpServletRequest request) {
		rolDAO.delete(idRol, request);
	}

	@Override
	public List<Rol> findByRolName(String rolName) {
		return rolDAO.findByRolName(rolName);
	}

}
