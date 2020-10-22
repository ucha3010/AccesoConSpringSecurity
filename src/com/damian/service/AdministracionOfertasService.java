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

	public int saveOfertas(AdministracionOfertas administracionOfertas, HttpServletRequest request);

	public int updateOfertas(AdministracionOfertas administracionOfertas, HttpServletRequest request);

	public int savePopulares(AdministracionOfertas administracionOfertas, HttpServletRequest request);

	public int updatePopulares(AdministracionOfertas administracionOfertas, HttpServletRequest request);

	public int saveNovedades(AdministracionOfertas administracionOfertas, HttpServletRequest request);

	public int updateNovedades(AdministracionOfertas administracionOfertas, HttpServletRequest request);

	public int saveCampania(AdministracionOfertas administracionOfertas, HttpServletRequest request);

	public int updateCampania(AdministracionOfertas administracionOfertas, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request);

	public List<Integer> listadoSelect(String tipoSeleccion);

	public void orderByOrdenOferta(HttpServletRequest request);

	public void orderOferta(int idPro, int ordenOferta, HttpServletRequest request);

	public void orderByOrdenPopular(HttpServletRequest request);

	public void orderPopular(int idPro, int ordenPopular, HttpServletRequest request);

}
