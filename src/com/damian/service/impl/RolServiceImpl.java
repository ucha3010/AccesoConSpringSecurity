package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

	public void save(Rol rol, HttpServletRequest request) {

		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		rol.setModificadoPor(context.getAuthentication().getPrincipal().toString());
		rol.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		rolDAO.save(rol);
	}

	public List<Rol> findAll() {
		return rolDAO.findAll();
	}

	public void delete(int idRol) {
		rolDAO.delete(idRol);
	}

	@Override
	public List<Rol> findByRolName(String rolName) {
		return rolDAO.findByRolName(rolName);
	}

}
