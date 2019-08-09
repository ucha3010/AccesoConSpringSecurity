package com.damian.dao;

import java.util.List;

import com.damian.pojo.Admin;
import com.damian.pojo.Direccion;

public interface DireccionDao {
	
	public void save(Direccion direccion);
	public List<Direccion> findAll(Admin admin);

}
