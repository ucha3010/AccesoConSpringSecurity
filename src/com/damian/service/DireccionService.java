package com.damian.service;

import java.util.List;

import com.damian.pojo.Admin;
import com.damian.pojo.Direccion;

public interface DireccionService {
	
	void save(Admin admin, Direccion direccion);

	List<Direccion> findAll(int idAd);

}
