package com.damian.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.CuotaDetalleDAO;
import com.damian.dao.model.ModelCuotaDetalle;
import com.damian.pojo.CuotaDetalle;
import com.damian.service.CuotaDetalleService;

@Service
public class CuotaDetalleServiceImpl implements CuotaDetalleService {

	@Autowired
	private CuotaDetalleDAO cuotaDetalleDAO;

	@Override
	public List<CuotaDetalle> findAll() {
		return cuotaDetalleDAO.findAll();
	}

	@Override
	public CuotaDetalle findById(int id) {
		return cuotaDetalleDAO.findById(id);
	}

	@Override
	public CuotaDetalle findByIdModel(int id) {
		return cuotaDetalleDAO.findByIdModel(id);
	}

	@Override
	public int save(CuotaDetalle cuotaDetalle, HttpServletRequest request) {
		return cuotaDetalleDAO.save(cuotaDetalle, request);
	}

	@Override
	public int update(CuotaDetalle cuotaDetalle, HttpServletRequest request) {
		return cuotaDetalleDAO.update(cuotaDetalle, request);
	}

	@Override
	public int delete(int id, HttpServletRequest request) {
		return cuotaDetalleDAO.delete(id, request);
	}

	@Override
	public int getMaxId() {
		return cuotaDetalleDAO.getMaxId();
	}

	@Override
	public List<CuotaDetalle> findByIdCuo(int idCuo) {
		return cuotaDetalleDAO.findByIdCuo(idCuo);
	}

	@Override
	public List<ModelCuotaDetalle> findModelByIdCuo(int idCuo) {
		return cuotaDetalleDAO.findModelByIdCuo(idCuo);
	}

}
