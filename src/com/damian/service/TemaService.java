package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Tema;

public interface TemaService {

	public List<Tema> findAll();

	public Tema findById(int id);

	public int save(Tema tema, HttpServletRequest request);

	public int update(Tema tema, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request) throws NotEmptyException;

	public int getMaxId();

}
