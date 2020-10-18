package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.AdministracionOfertas;

public interface AdministracionOfertasService {

	public List<AdministracionOfertas> findAll();

	public AdministracionOfertas findById(int id);

	public List<AdministracionOfertas> findByOfertas(int cantMax);

	public int getMaxOrdenOferta();

	public List<AdministracionOfertas> findByProductosPopulares(int cantMax);

	public int getMaxOrdenPopulares();

	public List<AdministracionOfertas> findByNovedades(int cantMax);

	public int getMaxOrdenNovedades();

	public List<AdministracionOfertas> findByCampania(int idCam, int cantMax);

	public int save(AdministracionOfertas administracionOfertas, HttpServletRequest request);

	public int update(AdministracionOfertas administracionOfertas, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request);

	public void orderByOrdenOfertas(HttpServletRequest request);

	public List<Integer> listadoSelect();

	public void orderOfertas(int idPro, int ordenOferta, HttpServletRequest request);

}
