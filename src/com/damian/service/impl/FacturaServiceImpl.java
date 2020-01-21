package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.EstadoDAO;
import com.damian.dao.FacturaDAO;
import com.damian.dao.FacturaEstadoDAO;
import com.damian.dao.FormaPagoDAO;
import com.damian.dao.ProductoFacturaDAO;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Estado;
import com.damian.pojo.Factura;
import com.damian.pojo.FacturaEstado;
import com.damian.pojo.FormaPago;
import com.damian.pojo.ProductoFactura;
import com.damian.service.FacturaService;

@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	private FacturaDAO facturaDAO;

	@Autowired
	private EstadoDAO estadoDAO;

	@Autowired
	private FacturaEstadoDAO facturaEstadoDAO;

	@Autowired
	private FormaPagoDAO formaPagoDAO;

	@Autowired
	private ProductoFacturaDAO productoFacturaDAO;

	@Override
	public List<Factura> findAll() {
		List<Factura> facturas = facturaDAO.findAll();
		for (Factura factura : facturas) {
			Estado estado = estadoDAO.findById(factura.getEstado().getIdEst());
			FormaPago formaPago = formaPagoDAO.findById(factura.getFormaPago().getIdFor());
			factura.setEstado(estado);
			factura.setFormaPago(formaPago);
		}
		return facturas;
	}

	@Override
	public Factura findById(int id) {
		return facturaDAO.findById(id);
	}

	@Override
	public Factura findByIdModel(int id) {
		return facturaDAO.findByIdModel(id);
	}

	@Override
	public int save(Factura factura, HttpServletRequest request) {
		facturaDAO.save(factura);
		factura.setIdFac(facturaDAO.getMaxId());
		saveFacturaEstado(factura, request);
		return factura.getIdFac();

	}

	@Override
	public int update(Factura factura, HttpServletRequest request) {
		facturaDAO.update(factura);
		saveFacturaEstado(factura, request);
		return factura.getIdFac();
	}

	@Override
	public int delete(int id) throws NotEmptyException {
		List<ProductoFactura> productoFacturaList = productoFacturaDAO.findByIdFacModel(id);
		if (productoFacturaList != null) {
			for (ProductoFactura pf : productoFacturaList) {
				productoFacturaDAO.delete(pf.getProducto().getIdPro(), id);
			}
		}
		List<FacturaEstado> facturaEstadoList = facturaEstadoDAO.findByIdFacModel(id);
		for (FacturaEstado fe : facturaEstadoList) {
			facturaEstadoDAO.delete(fe.getId());
		}
		return facturaDAO.delete(id);
	}

	@Override
	public List<Factura> findByIdEstModel(int idEst) {
		return facturaDAO.findByIdEstModel(idEst);
	}

	@Override
	public List<Factura> findByIdForModel(int idFor) {
		return facturaDAO.findByIdForModel(idFor);
	}

	@Override
	public List<Factura> findByIdList(int id) {
		return facturaDAO.findByIdList(id);
	}

	private void saveFacturaEstado(Factura factura, HttpServletRequest request) {

		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		String creador;
		if (context != null && context.getAuthentication() != null && context.getAuthentication().getName() != null
				&& !context.getAuthentication().getName().isEmpty()) {
			creador = context.getAuthentication().getName();
		} else {
			creador = "OWN USER";
		}
		FacturaEstado facturaEstado = new FacturaEstado();
		facturaEstado.setFactura(factura);
		facturaEstado.setEstado(factura.getEstado());
		Date utilDate = new Date();
		long lnMilisegundos = utilDate.getTime();
		facturaEstado.setFecha(new Timestamp(lnMilisegundos));
		facturaEstado.setCreadoPor(creador);
		facturaEstadoDAO.save(facturaEstado);

	}

}
