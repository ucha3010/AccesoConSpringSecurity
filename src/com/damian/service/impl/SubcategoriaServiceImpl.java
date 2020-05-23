package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.SubcategoriaDAO;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Producto;
import com.damian.pojo.Subcategoria;
import com.damian.service.ProductoService;
import com.damian.service.SubcategoriaService;

@Service
public class SubcategoriaServiceImpl implements SubcategoriaService {

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

		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		subcategoria.setModificadoPor(context.getAuthentication().getName());
		subcategoria.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		return subcategoriaDAO.save(subcategoria);
	}

	@Override
	public int update(Subcategoria subcategoria, HttpServletRequest request) {

		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		subcategoria.setModificadoPor(context.getAuthentication().getName());
		subcategoria.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		return subcategoriaDAO.update(subcategoria);
	}

	@Override
	public int delete(int id) throws NotEmptyException {
		List<Producto> productoList = productoService.findByIdSubModel(id);
		if (productoList != null && !productoList.isEmpty()) {
			throw new NotEmptyException("Tiene asociado productos");
		}
		return subcategoriaDAO.delete(id);
	}

	@Override
	public List<Subcategoria> findByIdCatModel(int idCat) {
		return subcategoriaDAO.findByIdCatModel(idCat);
	}

}
