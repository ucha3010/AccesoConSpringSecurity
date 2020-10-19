package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.AdministracionOfertasDAO;
import com.damian.pojo.AdministracionOfertas;
import com.damian.service.AdministracionOfertasService;
import com.damian.service.ProductoService;

@Service
public class AdministracionOfertasServiceImpl implements AdministracionOfertasService {

	@Autowired
	private AdministracionOfertasDAO administracionOfertasDAO;

	@Autowired
	private ProductoService productoService;

	@Override
	public List<AdministracionOfertas> findAll() {
		return administracionOfertasDAO.findAll();
	}

	@Override
	public AdministracionOfertas findById(int id) {
		return administracionOfertasDAO.findById(id);
	}

	@Override
	public List<AdministracionOfertas> findByOfertas(int cantMax) {
		List<AdministracionOfertas> administracionOfertasList = administracionOfertasDAO.findByOfertas(cantMax);
		for (AdministracionOfertas ao : administracionOfertasList) {
			ao.setProducto(productoService.findByIdModel(ao.getIdPro()));
		}
		return administracionOfertasList;
	}

	@Override
	public int getMaxOrdenOferta() {
		return administracionOfertasDAO.getMaxOrdenOferta();
	}

	@Override
	public List<AdministracionOfertas> findByProductosPopulares(int cantMax) {
		return administracionOfertasDAO.findByProductosPopulares(cantMax);
	}

	@Override
	public int getMaxOrdenPopulares() {
		return administracionOfertasDAO.getMaxOrdenPopulares();
	}

	@Override
	public List<AdministracionOfertas> findByNovedades(int cantMax) {
		return administracionOfertasDAO.findByNovedades(cantMax);
	}

	@Override
	public int getMaxOrdenNovedades() {
		return administracionOfertasDAO.getMaxOrdenNovedades();
	}

	@Override
	public List<AdministracionOfertas> findByCampania(int idCam, int cantMax) {
		return administracionOfertasDAO.findByCampania(idCam, cantMax);
	}

	@Override
	public int save(AdministracionOfertas administracionOfertas, HttpServletRequest request) {

		int realizado = 0;
		AdministracionOfertas administracionOfertasAux = findById(administracionOfertas.getIdPro());
		administracionOfertas.setBooleanOferta(true);
		administracionOfertas.setFecha(new Timestamp(System.currentTimeMillis()));
		administracionOfertas.setOrdenOferta(getMaxOrdenOferta() + 1);
		if (administracionOfertasAux != null) {
			administracionOfertas.setBooleanPopular(administracionOfertasAux.isBooleanPopular());
			administracionOfertas.setOrdenPopular(administracionOfertasAux.getOrdenPopular());
			administracionOfertas.setBooleanNovedades(administracionOfertasAux.isBooleanNovedades());
			administracionOfertas.setOrdenNovedades(administracionOfertasAux.getOrdenNovedades());
			administracionOfertas.setIdCam(administracionOfertasAux.getIdCam());
			realizado = administracionOfertasDAO.update(administracionOfertas, request);
		} else {
			realizado = administracionOfertasDAO.save(administracionOfertas, request);
		}

		return realizado;
	}

	@Override
	public int update(AdministracionOfertas administracionOfertas, HttpServletRequest request) {
		int realizado = 0;
		if (!administracionOfertas.isBooleanOferta() && !administracionOfertas.isBooleanPopular()
				&& !administracionOfertas.isBooleanNovedades() && administracionOfertas.getIdCam() == 0) {
			realizado = administracionOfertasDAO.delete(administracionOfertas.getIdPro(), request);
		} else {
			realizado = administracionOfertasDAO.update(administracionOfertas, request);
		}
		orderByOrdenOfertas(request);
		return realizado;
	}

	@Override
	public int delete(int id, HttpServletRequest request) {
		return administracionOfertasDAO.delete(id, request);
	}

	@Override
	public void orderByOrdenOfertas(HttpServletRequest request) {
		List<AdministracionOfertas> administracionOfertasList = administracionOfertasDAO.findOrderedByOrdenOfertas(0);
		int i = 1;
		for (AdministracionOfertas ao : administracionOfertasList) {
			if (ao.getOrdenOferta() > 0) {
				if (ao.getOrdenOferta() != i) {
					ao.setOrdenOferta(i);
					administracionOfertasDAO.update(ao, request);
				}
				i++;
			}
		}

	}

	@Override
	public List<Integer> listadoSelect() {
		int max = getMaxOrdenOferta();
		List<Integer> listado = new ArrayList<>();
		for (int i = 1; i <= max; i++) {
			listado.add(i - 1, i);
		}
		return listado;
	}

	@Override
	public void orderOfertas(int idPro, int ordenOferta, HttpServletRequest request) {

		AdministracionOfertas administracionOfertas = findById(idPro);
		administracionOfertas.setOrdenOferta(0);
		administracionOfertasDAO.update(administracionOfertas, request);
		List<AdministracionOfertas> administracionOfertasList = administracionOfertasDAO
				.findOrderedByOrdenOfertas(1);
		int nuevoOrden = 0;
		if (administracionOfertasList != null && !administracionOfertasList.isEmpty()) {
			for (AdministracionOfertas ao : administracionOfertasList) {
				if(ao.getIdPro() != idPro) {
					nuevoOrden++;
					if(nuevoOrden == ordenOferta) {
						nuevoOrden++;
					}
					ao.setOrdenOferta(nuevoOrden);
					administracionOfertasDAO.update(ao, request);
				}
			}
		}
		administracionOfertas.setOrdenOferta(ordenOferta);
		administracionOfertasDAO.update(administracionOfertas, request);
	}

}
