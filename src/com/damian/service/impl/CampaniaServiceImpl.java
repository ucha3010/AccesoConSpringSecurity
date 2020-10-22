package com.damian.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.CampaniaDAO;
import com.damian.pojo.AdministracionOfertas;
import com.damian.pojo.Campania;
import com.damian.service.AdministracionOfertasService;
import com.damian.service.CampaniaService;

@Service
public class CampaniaServiceImpl implements CampaniaService {

	@Autowired
	private CampaniaDAO campaniaDAO;

	@Autowired
	private AdministracionOfertasService administracionOfertasService;

	@Override
	public List<Campania> findAll() {
		return campaniaDAO.findAll();
	}

	@Override
	public Campania findById(int id) {
		return campaniaDAO.findById(id);
	}

	@Override
	public int save(Campania campania, HttpServletRequest request) {
		return campaniaDAO.save(campania, request);
	}

	@Override
	public int update(Campania campania, HttpServletRequest request) {
		return campaniaDAO.update(campania, request);
	}

	@Override
	public int delete(int id, HttpServletRequest request) {

		List<AdministracionOfertas> aoList = administracionOfertasService.findByCampania(id, 0);
		for (AdministracionOfertas ao : aoList) {
			ao.setIdCam(0);
			administracionOfertasService.updateCampania(ao, request);
		}
		return campaniaDAO.delete(id, request);
	}

}
