package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.AdministracionOfertasDAO;
import com.damian.pojo.AdministracionOfertas;
import com.damian.pojo.Favorito;
import com.damian.service.AdministracionOfertasService;
import com.damian.service.FavoritoService;
import com.damian.service.ProductoService;

@Service
public class AdministracionOfertasServiceImpl implements AdministracionOfertasService {

	@Autowired
	private AdministracionOfertasDAO administracionOfertasDAO;

	@Autowired
	private FavoritoService favoritoService;

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
		return fillProducto(administracionOfertasDAO.findByOfertas(cantMax));
	}

	@Override
	public int getMaxOrdenOferta() {
		return administracionOfertasDAO.getMaxOrdenOferta();
	}

	@Override
	public List<AdministracionOfertas> findByProductosPopulares(int cantMax) {
		return fillProducto(administracionOfertasDAO.findByProductosPopulares(cantMax));
	}

	@Override
	public int getMaxOrdenPopulares() {
		return administracionOfertasDAO.getMaxOrdenPopulares();
	}

	@Override
	public List<AdministracionOfertas> findByNovedades(int cantMax) {
		return fillProducto(administracionOfertasDAO.findByNovedades(cantMax));
	}

	@Override
	public int getMaxOrdenNovedades() {
		return administracionOfertasDAO.getMaxOrdenNovedades();
	}

	@Override
	public List<AdministracionOfertas> findByCampania(int idCam, int cantMax) {
		return fillProducto(administracionOfertasDAO.findByCampania(idCam, cantMax));
	}

	@Override
	public int saveOfertas(AdministracionOfertas administracionOfertas, HttpServletRequest request) {

		int realizado = 0;
		if (administracionOfertas != null && administracionOfertas.getIdPro() != 0) {
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
		}

		return realizado;
	}

	@Override
	public int updateOfertas(AdministracionOfertas administracionOfertas, HttpServletRequest request) {
		int realizado = updateOrDelete(administracionOfertas, request);
		orderByOrdenOferta(request);
		return realizado;
	}

	@Override
	public int savePopulares(AdministracionOfertas administracionOfertas, HttpServletRequest request) {

		int realizado = 0;
		if (administracionOfertas != null && administracionOfertas.getIdPro() != 0) {
			AdministracionOfertas administracionOfertasAux = findById(administracionOfertas.getIdPro());
			administracionOfertas.setBooleanPopular(true);
			administracionOfertas.setOrdenPopular(getMaxOrdenPopulares() + 1);
			if (administracionOfertasAux != null) {
				administracionOfertas.setBooleanOferta(administracionOfertasAux.isBooleanOferta());
				administracionOfertas.setFecha(administracionOfertasAux.getFecha());
				administracionOfertas.setOrdenOferta(administracionOfertasAux.getOrdenOferta());
				administracionOfertas.setPrecioConOferta(administracionOfertasAux.getPrecioConOferta());
				administracionOfertas.setPrecioSinOferta(administracionOfertasAux.getPrecioSinOferta());
				administracionOfertas.setBooleanNovedades(administracionOfertasAux.isBooleanNovedades());
				administracionOfertas.setOrdenNovedades(administracionOfertasAux.getOrdenNovedades());
				administracionOfertas.setIdCam(administracionOfertasAux.getIdCam());
				realizado = administracionOfertasDAO.update(administracionOfertas, request);
			} else {
				realizado = administracionOfertasDAO.save(administracionOfertas, request);
			}
		}

		return realizado;
	}

	@Override
	public int updatePopulares(AdministracionOfertas administracionOfertas, HttpServletRequest request) {
		int realizado = updateOrDelete(administracionOfertas, request);
		orderByOrdenPopular(request);
		return realizado;
	}

	@Override
	public int saveNovedades(AdministracionOfertas administracionOfertas, HttpServletRequest request) {

		int realizado = 0;
		if (administracionOfertas != null && administracionOfertas.getIdPro() != 0) {
			AdministracionOfertas administracionOfertasAux = findById(administracionOfertas.getIdPro());
			administracionOfertas.setBooleanNovedades(true);
			administracionOfertas.setOrdenNovedades(getMaxOrdenNovedades() + 1);
			if (administracionOfertasAux != null) {
				administracionOfertas.setBooleanOferta(administracionOfertasAux.isBooleanOferta());
				administracionOfertas.setFecha(administracionOfertasAux.getFecha());
				administracionOfertas.setOrdenOferta(administracionOfertasAux.getOrdenOferta());
				administracionOfertas.setPrecioConOferta(administracionOfertasAux.getPrecioConOferta());
				administracionOfertas.setPrecioSinOferta(administracionOfertasAux.getPrecioSinOferta());
				administracionOfertas.setBooleanPopular(administracionOfertasAux.isBooleanPopular());
				administracionOfertas.setOrdenPopular(administracionOfertasAux.getOrdenPopular());
				administracionOfertas.setIdCam(administracionOfertasAux.getIdCam());
				realizado = administracionOfertasDAO.update(administracionOfertas, request);
			} else {
				realizado = administracionOfertasDAO.save(administracionOfertas, request);
			}
		}

		return realizado;
	}

	@Override
	public int updateNovedades(AdministracionOfertas administracionOfertas, HttpServletRequest request) {
		int realizado = updateOrDelete(administracionOfertas, request);
		orderByOrdenNovedades(request);
		return realizado;
	}

	@Override
	public int saveCampania(AdministracionOfertas administracionOfertas, HttpServletRequest request) {

		int realizado = 0;
		if (administracionOfertas != null && administracionOfertas.getIdPro() != 0) {
			AdministracionOfertas administracionOfertasAux = findById(administracionOfertas.getIdPro());
			if (administracionOfertasAux != null) {
				administracionOfertasAux.setIdCam(administracionOfertas.getIdCam());
				realizado = administracionOfertasDAO.update(administracionOfertasAux, request);
			} else {
				realizado = administracionOfertasDAO.save(administracionOfertas, request);
			}
		}

		return realizado;
	}

	@Override
	public int updateCampania(AdministracionOfertas administracionOfertas, HttpServletRequest request) {
		return updateOrDelete(administracionOfertas, request);
	}

	@Override
	public int delete(int id, HttpServletRequest request) {
		return administracionOfertasDAO.delete(id, request);
	}

	@Override
	public List<Integer> listadoSelect(String tipoSeleccion) {
		int max = 0;
		if ("oferta".equalsIgnoreCase(tipoSeleccion)) {
			max = getMaxOrdenOferta();
		} else if ("popular".equalsIgnoreCase(tipoSeleccion)) {
			max = getMaxOrdenPopulares();
		} else if ("novedades".equalsIgnoreCase(tipoSeleccion)) {
			max = getMaxOrdenNovedades();
		}
		List<Integer> listado = new ArrayList<>();
		for (int i = 1; i <= max; i++) {
			listado.add(i - 1, i);
		}
		return listado;
	}

	/** Elimino huecos en el orden de numeración */
	@Override
	public void orderByOrdenOferta(HttpServletRequest request) {
		List<AdministracionOfertas> administracionOfertasList = administracionOfertasDAO.findOrderedByOrdenOferta(0);
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
	public void orderOferta(int idPro, int ordenOferta, HttpServletRequest request) {

		AdministracionOfertas administracionOfertas = findById(idPro);
		administracionOfertas.setOrdenOferta(0);
		administracionOfertasDAO.update(administracionOfertas, request);
		List<AdministracionOfertas> administracionOfertasList = administracionOfertasDAO.findOrderedByOrdenOferta(1);
		int nuevoOrden = 0;
		if (administracionOfertasList != null && !administracionOfertasList.isEmpty()) {
			for (AdministracionOfertas ao : administracionOfertasList) {
				if (ao.getIdPro() != idPro) {
					nuevoOrden++;
					if (nuevoOrden == ordenOferta) {
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

	/** Elimino huecos en el orden de numeración */
	@Override
	public void orderByOrdenPopular(HttpServletRequest request) {
		List<AdministracionOfertas> administracionOfertasList = administracionOfertasDAO.findOrderedByOrdenPopular(0);
		int i = 1;
		for (AdministracionOfertas ao : administracionOfertasList) {
			if (ao.getOrdenPopular() > 0) {
				if (ao.getOrdenPopular() != i) {
					ao.setOrdenPopular(i);
					administracionOfertasDAO.update(ao, request);
				}
				i++;
			}
		}

	}

	@Override
	public void orderPopular(int idPro, int ordenPopular, HttpServletRequest request) {

		AdministracionOfertas administracionOfertas = findById(idPro);
		administracionOfertas.setOrdenPopular(0);
		administracionOfertasDAO.update(administracionOfertas, request);
		List<AdministracionOfertas> administracionOfertasList = administracionOfertasDAO.findOrderedByOrdenPopular(1);
		int nuevoOrden = 0;
		if (administracionOfertasList != null && !administracionOfertasList.isEmpty()) {
			for (AdministracionOfertas ao : administracionOfertasList) {
				if (ao.getIdPro() != idPro) {
					nuevoOrden++;
					if (nuevoOrden == ordenPopular) {
						nuevoOrden++;
					}
					ao.setOrdenPopular(nuevoOrden);
					administracionOfertasDAO.update(ao, request);
				}
			}
		}
		administracionOfertas.setOrdenPopular(ordenPopular);
		administracionOfertasDAO.update(administracionOfertas, request);
	}

	@Override
	public void orderByOrdenNovedades(HttpServletRequest request) {
		List<AdministracionOfertas> administracionOfertasList = administracionOfertasDAO.findOrderedByOrdenNovedades(0);
		int i = 1;
		for (AdministracionOfertas ao : administracionOfertasList) {
			if (ao.getOrdenNovedades() > 0) {
				if (ao.getOrdenNovedades() != i) {
					ao.setOrdenNovedades(i);
					administracionOfertasDAO.update(ao, request);
				}
				i++;
			}
		}

	}

	@Override
	public void orderNovedades(int idPro, int ordenNovedades, HttpServletRequest request) {

		AdministracionOfertas administracionOfertas = findById(idPro);
		administracionOfertas.setOrdenNovedades(0);
		administracionOfertasDAO.update(administracionOfertas, request);
		List<AdministracionOfertas> administracionOfertasList = administracionOfertasDAO.findOrderedByOrdenNovedades(1);
		int nuevoOrden = 0;
		if (administracionOfertasList != null && !administracionOfertasList.isEmpty()) {
			for (AdministracionOfertas ao : administracionOfertasList) {
				if (ao.getIdPro() != idPro) {
					nuevoOrden++;
					if (nuevoOrden == ordenNovedades) {
						nuevoOrden++;
					}
					ao.setOrdenNovedades(nuevoOrden);
					administracionOfertasDAO.update(ao, request);
				}
			}
		}
		administracionOfertas.setOrdenNovedades(ordenNovedades);
		administracionOfertasDAO.update(administracionOfertas, request);
	}

	@Override
	public void fillFavoritos(List<AdministracionOfertas> administracionOfertasList, int idUsr) {
		if (administracionOfertasList != null && !administracionOfertasList.isEmpty()) {
			Favorito favorito;
			for (AdministracionOfertas ao : administracionOfertasList) {
				if (ao.getProducto() != null) {
					favorito = favoritoService.findById(idUsr, ao.getProducto().getIdPro());
					if (favorito != null && favorito.getIdPro() != 0) {
						ao.getProducto().setFavorito(true);
					}
				}
			}
		}
	}

	private int updateOrDelete(AdministracionOfertas administracionOfertas, HttpServletRequest request) {
		int realizado = 0;
		if (!administracionOfertas.isBooleanOferta() && !administracionOfertas.isBooleanPopular()
				&& !administracionOfertas.isBooleanNovedades() && administracionOfertas.getIdCam() == 0) {
			realizado = administracionOfertasDAO.delete(administracionOfertas.getIdPro(), request);
		} else {
			realizado = administracionOfertasDAO.update(administracionOfertas, request);
		}
		return realizado;
	}

	private List<AdministracionOfertas> fillProducto(List<AdministracionOfertas> administracionOfertasList) {
		for (AdministracionOfertas ao : administracionOfertasList) {
			ao.setProducto(productoService.findByIdModel(ao.getIdPro()));
		}
		return administracionOfertasList;

	}

}
