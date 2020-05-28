package com.damian.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.PaisDAO;
import com.damian.pojo.Pais;
import com.damian.service.PaisService;

@Service
public class PaisServiceImpl implements PaisService {

	@Autowired
	private PaisDAO paisDAO;

	public Pais findById(int id) {
		return paisDAO.findById(id);
	}

	public void save(Pais pais, HttpServletRequest request) {
		paisDAO.save(pais, request);
	}

	public List<Pais> findAll() {
		return paisDAO.findAll();
	}

	public void update(Pais pais, HttpServletRequest request) {
		paisDAO.update(pais, request);
	}

	public void delete(int idUsr, HttpServletRequest request) {
		Pais pais = findById(idUsr);
		paisDAO.delete(pais.getIdPais(), request);
	}

	@Override
	public List<Pais> findByPaisName(String paisName) {
		return paisDAO.findByPaisName(paisName);
	}

}
