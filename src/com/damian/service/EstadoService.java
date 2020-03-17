package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Estado;

public interface EstadoService {

	public List<Estado> findAll();

	public Estado findById(int id);

	public Estado findByIdModel(int id);

	public int save(Estado estado);

	public int update(Estado estado);

	public int delete(int id, String column, HttpServletRequest request) throws NotEmptyException;

}
