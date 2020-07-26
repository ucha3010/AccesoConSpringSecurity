package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.CuotaDAO;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Cuota;
import com.damian.pojo.CuotaDetalle;
import com.damian.service.CuotaDetalleService;
import com.damian.service.CuotaService;
import com.damian.utils.Utils;

@Service
public class CuotaServiceImpl implements CuotaService {

	@Autowired
	private CuotaDAO cuotaDAO;

	@Autowired
	private CuotaDetalleService cuotaDetalleService;

	@Override
	public List<Cuota> findAll() {
		return cuotaDAO.findAll();
	}

	@Override
	public Cuota findById(int id) {
		return cuotaDAO.findById(id);
	}

	@Override
	public Cuota findByIdModel(int id) {
		return cuotaDAO.findByIdModel(id);
	}

	@Override
	public int save(Cuota cuota, HttpServletRequest request) {

		cuota.setModificadoPor(Utils.getLoggedUser(request));
		cuota.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		return cuotaDAO.save(cuota, request);
	}

	@Override
	public int update(Cuota cuota, HttpServletRequest request) {

		cuota.setModificadoPor(Utils.getLoggedUser(request));
		cuota.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		return cuotaDAO.update(cuota, request);
	}

	@Override
	public int delete(int id, HttpServletRequest request) throws NotEmptyException {
		List<CuotaDetalle> cuotaDetalleList = cuotaDetalleService.findByIdCuo(id);
		for(CuotaDetalle cd: cuotaDetalleList) {
			cuotaDetalleService.delete(cd.getIdCuDe(), request);
		}
		return cuotaDAO.delete(id, request);
	}

}
