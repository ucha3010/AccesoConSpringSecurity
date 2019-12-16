package com.damian.service;

import java.util.List;

import com.damian.pojo.FormaPago;

public interface FormaPagoService {

	public List<FormaPago> findAll();

	public FormaPago findById(int id);

	public FormaPago findByIdModel(int id);

	public int save(FormaPago formaPago);

	public int update(FormaPago formaPago);

	public int delete(int id);

}
