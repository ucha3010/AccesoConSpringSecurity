package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.FormaPago;

public interface FormaPagoDAO {

	public List<FormaPago> findAll();

	public FormaPago findById(int id);

	public FormaPago findByIdModel(int id);

	public int save(FormaPago formaPago, HttpServletRequest request);

	public int update(FormaPago formaPago, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request);

	public int getMaxId();

}
