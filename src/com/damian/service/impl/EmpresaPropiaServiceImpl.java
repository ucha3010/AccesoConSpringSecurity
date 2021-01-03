package com.damian.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.EmpresaPropiaDAO;
import com.damian.pojo.DireccionEmpresaPropia;
import com.damian.pojo.EmpresaPropia;
import com.damian.pojo.Foto;
import com.damian.service.DireccionEmpresaPropiaService;
import com.damian.service.EmpresaPropiaService;
import com.damian.service.FotoService;

@Service
public class EmpresaPropiaServiceImpl implements EmpresaPropiaService {

	@Autowired
	private EmpresaPropiaDAO empresaPropiaDAO;

	@Autowired
	private FotoService fotoService;

	@Autowired
	private DireccionEmpresaPropiaService direccionEmpresaPropiaService;

	@Override
	public List<EmpresaPropia> findAll() {
		List<EmpresaPropia> empresaPropiaList = empresaPropiaDAO.findAll();
		for(EmpresaPropia ep: empresaPropiaList) {
			ep.setDireccionEmpresaPropia(direccionEmpresaPropiaService.findByIdPropiaModel(ep.getIdPropia()).get(0));
			cargoFoto(ep, ep.getIdPropia());
		}
		return empresaPropiaList;
	}

	@Override
	public EmpresaPropia findById(int id) {
		EmpresaPropia empresaPropia = empresaPropiaDAO.findById(id);
		empresaPropia.setDireccionEmpresaPropia(direccionEmpresaPropiaService.findByIdPropiaModel(id).get(0));
		cargoFoto(empresaPropia, id);
		return empresaPropia;
	}

	@Override
	public EmpresaPropia findByIdModel(int id) {
		return empresaPropiaDAO.findByIdModel(id);
	}

	@Override
	public int save(EmpresaPropia empresaPropia, HttpServletRequest request) {
		int resultado = empresaPropiaDAO.save(empresaPropia, request);
		if (empresaPropia.getDireccionEmpresaPropia() != null) {
			DireccionEmpresaPropia direccionEmpresaPropia = empresaPropia.getDireccionEmpresaPropia();
			int maxId = getMaxId();
			if (empresaPropia.getIdPropia() == 0) {
				direccionEmpresaPropia.setIdDirPropia(maxId);
				empresaPropia.setIdPropia(maxId);
			} else {
				direccionEmpresaPropia.setIdDirPropia(empresaPropia.getIdPropia());
			}
			direccionEmpresaPropia.setEmpresaPropia(empresaPropia);
			direccionEmpresaPropiaService.save(direccionEmpresaPropia, request);
		}
		return resultado;
	}

	@Override
	public int update(EmpresaPropia empresaPropia, HttpServletRequest request) {
		return empresaPropiaDAO.update(empresaPropia, request);
	}

	@Override
	public int delete(int id, HttpServletRequest request) {
		EmpresaPropia empresaPropia = findById(id);
		if (empresaPropia.getDireccionEmpresaPropia() != null
				&& empresaPropia.getDireccionEmpresaPropia().getIdDirPropia() != 0) {
			direccionEmpresaPropiaService.delete(empresaPropia.getDireccionEmpresaPropia().getIdDirPropia(), request);
		}
		List<Foto> fotoList = fotoService.findByIdPropia(id);
		if (fotoList != null && !fotoList.isEmpty()) {
			fotoService.delete(fotoList.get(0).getIdFot(), request);
		}
		int resultado = empresaPropiaDAO.delete(id, request);
		if (empresaPropia.isFacturacion()) {
			changeAvailable(getMaxId(), request);
		}
		return resultado;
	}

	@Override
	public void changeAvailable(int idPropia, HttpServletRequest request) {

		List<EmpresaPropia> empresasPropias = findAll();
		for (EmpresaPropia ep : empresasPropias) {
			if (ep.isFacturacion()) {
				ep.setFacturacion(false);
				update(ep, request);
			}
		}
		EmpresaPropia empresaPropia = findById(idPropia);
		empresaPropia.setFacturacion(true);
		update(empresaPropia, request);
	}

	@Override
	public int getMaxId() {
		return empresaPropiaDAO.getMaxId();
	}
	
	private void cargoFoto(EmpresaPropia empresaPropia, int idPropia) {
		List<Foto> fotoList = fotoService.findByIdPropia(idPropia);
		if (fotoList != null && !fotoList.isEmpty()) {
			empresaPropia.setFoto(fotoList.get(0));
		}
		
	}

}
