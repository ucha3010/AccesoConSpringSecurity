package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.dao.model.ModelCuotaDetalle;
import com.damian.pojo.CuotaDetalle;

public interface CuotaDetalleService {

	public List<CuotaDetalle> findAll();

	public CuotaDetalle findById(int id);

	public CuotaDetalle findByIdModel(int id);

	public int save(CuotaDetalle cuotaDetalle, HttpServletRequest request);

	public int update(CuotaDetalle cuotaDetalle, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request);

	public int getMaxId();

	public List<CuotaDetalle> findByIdCuo(int idCuo);

	public List<ModelCuotaDetalle> findModelByIdCuo(int idCuo);

}
