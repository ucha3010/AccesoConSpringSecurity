package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.AdministracionOfertas;

public interface AdministracionOfertasDAO {

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

	public List<AdministracionOfertas> findOrderedByOrdenOferta(int ordenOferta);

	public List<AdministracionOfertas> findOrderedByOrdenPopular(int ordenPopular);

	public List<AdministracionOfertas> findOrderedByOrdenNovedades(int ordenNovedades);

}
