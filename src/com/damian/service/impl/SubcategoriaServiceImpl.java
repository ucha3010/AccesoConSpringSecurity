package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.SubcategoriaDAO;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.FiltroTitulo;
import com.damian.pojo.Producto;
import com.damian.pojo.Subcategoria;
import com.damian.service.FiltroTituloService;
import com.damian.service.ProductoService;
import com.damian.service.SubcategoriaService;
import com.damian.utils.Utils;

@Service
public class SubcategoriaServiceImpl implements SubcategoriaService {

	@Autowired
	private FiltroTituloService filtroTituloService;

	@Autowired
	private ProductoService productoService;

	@Autowired
	private SubcategoriaDAO subcategoriaDAO;

	@Override
	public List<Subcategoria> findAll() {
		return subcategoriaDAO.findAll();
	}

	@Override
	public Subcategoria findById(int id) {
		return subcategoriaDAO.findById(id);
	}

	@Override
	public Subcategoria findByIdModel(int id) {
		return subcategoriaDAO.findByIdModel(id);
	}

	@Override
	public int save(Subcategoria subcategoria, HttpServletRequest request) {

		subcategoria.setModificadoPor(Utils.getLoggedUser(request));
		subcategoria.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		return subcategoriaDAO.save(subcategoria, request);
	}

	@Override
	public int update(Subcategoria subcategoria, HttpServletRequest request) {

		subcategoria.setModificadoPor(Utils.getLoggedUser(request));
		subcategoria.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		return subcategoriaDAO.update(subcategoria, request);
	}

	@Override
	public int delete(int id, HttpServletRequest request) throws NotEmptyException {
		List<Producto> productoList = productoService.findByIdSubModel(id);
		if (productoList != null && !productoList.isEmpty()) {
			throw new NotEmptyException("Tiene asociado productos");
		}
		List<FiltroTitulo> filtroTituloList = filtroTituloService.findByIdSubModel(id);
		if (filtroTituloList != null && !filtroTituloList.isEmpty()) {
			throw new NotEmptyException("Tiene asociado filtros");
		}
		return subcategoriaDAO.delete(id, request);
	}

	@Override
	public List<Subcategoria> findByIdCatModel(int idCat) {
		return subcategoriaDAO.findByIdCatModel(idCat);
	}

}
