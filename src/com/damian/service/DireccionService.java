package com.damian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.AdminDao;
import com.damian.dao.DireccionDao;
import com.damian.pojo.Admin;
import com.damian.pojo.Direccion;

@Service
public class DireccionService {

	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private DireccionDao direccionDao;

	public void save(Admin admin, Direccion direccion) {
		
		direccion.setAdmin(admin);
		direccionDao.save(direccion);
		
	}

	public List<Direccion> findAll(int idAd) {
		
		Admin admin = adminDao.findById(idAd);
		return direccionDao.findAll(admin);
	}

}
