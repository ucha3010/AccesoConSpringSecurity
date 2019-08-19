package com.damian.service;

import java.util.List;

import com.damian.pojo.Admin;

public interface AdminService {
	
	void save(Admin admin);

	List<Admin> findAll();

	Admin findById(int id);

	void saveOrUpdate(Admin admin);

	void delete(int idAd);

}
