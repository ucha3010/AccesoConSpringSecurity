package com.damian.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.MarcaDAO;
import com.damian.pojo.Marca;
import com.damian.service.MarcaService;

@Service
public class MarcaServiceImpl implements MarcaService {

	@Autowired
	private MarcaDAO marcaDAO;

	public List<Marca> findAll() {
		return marcaDAO.findAll();
	}

	public Marca findById(int id) {
		return marcaDAO.findById(id);
	}

	public void save(Marca marca, HttpServletRequest request) {
		marcaDAO.save(marca, request);
	}

	public void delete(int idMarca, HttpServletRequest request) {
		marcaDAO.delete(idMarca, request);
	}

	@Override
	public List<Marca> findByMarcaNombre(String nombre) {
		return marcaDAO.findByMarcaNombre(nombre);
	}

	@Override
	public int getMaxId() {
		return marcaDAO.getMaxId();
	}

}
