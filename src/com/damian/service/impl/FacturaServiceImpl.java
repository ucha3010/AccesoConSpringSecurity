package com.damian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.EstadoDAO;
import com.damian.dao.FacturaDAO;
import com.damian.dao.FormaPagoDAO;
import com.damian.dao.ProductoFacturaDAO;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Estado;
import com.damian.pojo.Factura;
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
	private FormaPagoDAO formaPagoDAO;

	@Autowired
	private ProductoFacturaDAO productoFacturaDAO;

	@Override
	public List<Factura> findAll() {
		List<Factura> facturas = facturaDAO.findAll();
		for(Factura factura:facturas) {
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
	public int save(Factura factura) {
		return facturaDAO.save(factura);
	}

	@Override
	public int update(Factura factura) {
		return facturaDAO.update(factura);
	}

	@Override
	public int delete(int id) throws NotEmptyException {
		List<ProductoFactura> productoFacturaList = productoFacturaDAO.findByIdFacModel(id);
		if (productoFacturaList != null) {
			for (ProductoFactura pf : productoFacturaList) {
				productoFacturaDAO.delete(pf.getProducto().getIdPro(), id);
			}
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

}
