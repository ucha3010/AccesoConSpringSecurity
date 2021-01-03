package com.damian.service.impl;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.DescripcionProductoDAO;
import com.damian.pojo.DescripcionProducto;
import com.damian.service.DescripcionProductoService;
import com.damian.utils.Utils;

@Service
public class DescripcionProductoServiceImpl implements DescripcionProductoService {

	@Autowired
	private DescripcionProductoDAO descripcionProductoDAO;

	@Override
	public DescripcionProducto findById(int id) {
		return descripcionProductoDAO.findById(id);
	}

	@Override
	public int save(DescripcionProducto descripcionProducto, HttpServletRequest request) {

		descripcionProducto.setModificadoPor(Utils.getLoggedUser(request));
		descripcionProducto.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		return descripcionProductoDAO.save(descripcionProducto, request);
	}

	@Override
	public int update(DescripcionProducto descripcionProducto, HttpServletRequest request) {

		descripcionProducto.setModificadoPor(Utils.getLoggedUser(request));
		descripcionProducto.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		return descripcionProductoDAO.update(descripcionProducto, request);
	}

	@Override
	public int delete(int id, HttpServletRequest request) {
		return descripcionProductoDAO.delete(id, request);
	}

}
