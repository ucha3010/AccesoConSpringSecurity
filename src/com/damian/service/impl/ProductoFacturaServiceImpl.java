package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.ProductoFacturaDAO;
import com.damian.pojo.ProductoFactura;
import com.damian.service.ProductoFacturaService;

@Service
public class ProductoFacturaServiceImpl implements ProductoFacturaService {

	@Autowired
	private ProductoFacturaDAO productoFacturaDAO;

	@Override
	public List<ProductoFactura> findAll() {
		return productoFacturaDAO.findAll();
	}

	@Override
	public void save(ProductoFactura productoFactura, HttpServletRequest request) {

		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		productoFactura.setModificadoPor(context.getAuthentication().getPrincipal().toString());
		productoFactura.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
		
		productoFacturaDAO.save(productoFactura);
	}

	@Override
	public void update(ProductoFactura productoFactura, HttpServletRequest request) {

		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		productoFactura.setModificadoPor(context.getAuthentication().getPrincipal().toString());
		productoFactura.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
		
		productoFacturaDAO.update(productoFactura);
	}

	@Override
	public void delete(int idPro, int idFac) {
		productoFacturaDAO.delete(idPro, idFac);
	}

	@Override
	public List<ProductoFactura> findByIdPro(int idPro) {
		return productoFacturaDAO.findByIdPro(idPro);
	}

	@Override
	public List<ProductoFactura> findByIdFac(int idFac) {
		return productoFacturaDAO.findByIdFac(idFac);
	}

	@Override
	public List<ProductoFactura> findByIdProModel(int idPro) {
		return productoFacturaDAO.findByIdProModel(idPro);
	}

	@Override
	public List<ProductoFactura> findByIdFacModel(int idFac) {
		return productoFacturaDAO.findByIdFacModel(idFac);
	}

	@Override
	public ProductoFactura findByIdProAndIdFac(int idPro, int idFac) {
		return productoFacturaDAO.findByIdProAndIdFac(idPro, idFac);
	}

}
