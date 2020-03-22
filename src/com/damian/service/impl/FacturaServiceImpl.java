package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.CuotaDAO;
import com.damian.dao.EstadoDAO;
import com.damian.dao.FacturaDAO;
import com.damian.dao.FacturaEstadoDAO;
import com.damian.dao.FormaPagoDAO;
import com.damian.dao.ProductoFacturaDAO;
import com.damian.exceptions.NegativeStockException;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Estado;
import com.damian.pojo.Factura;
import com.damian.pojo.FacturaEstado;
import com.damian.pojo.FormaPago;
import com.damian.pojo.Producto;
import com.damian.pojo.ProductoFactura;
import com.damian.service.FacturaService;
import com.damian.service.ProductoService;

@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	private FacturaDAO facturaDAO;

	@Autowired
	private CuotaDAO cuotaDAO;

	@Autowired
	private EstadoDAO estadoDAO;

	@Autowired
	private FacturaEstadoDAO facturaEstadoDAO;

	@Autowired
	private FormaPagoDAO formaPagoDAO;

	@Autowired
	private ProductoService productoService;

	@Autowired
	private ProductoFacturaDAO productoFacturaDAO;

	@Override
	public List<Factura> findAll(String column, HttpServletRequest request) {
		List<Factura> facturas = facturaDAO.findAll(column, request);
		for (Factura factura : facturas) {
			Estado estado = estadoDAO.findByIdModel(factura.getEstado().getIdEst());
			FormaPago formaPago = formaPagoDAO.findById(factura.getFormaPago().getIdFor());
			factura.setEstado(estado);
			factura.setFormaPago(formaPago);
			if (factura.getCuota() != null && factura.getCuota().getIdCuo() != 0) {
				factura.setCuota(cuotaDAO.findById(factura.getCuota().getIdCuo()));
			}
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
		Factura factura = findByIdModel(id);
		int borrada = 0;
		boolean continuo = true;

		try {
			if (factura.getCuota() != null && factura.getCuota().getIdCuo() != 0) {
				List<Factura> facturas = findByIdCuo(factura.getCuota().getIdCuo());
				if (facturas != null && facturas.size() == 1) {
					Producto producto = verificoStock(productoFacturaList.get(0), factura);
					actualizoStock(productoFacturaList.get(0), factura, producto);
					cuotaDAO.delete(factura.getCuota().getIdCuo());
				} else if (facturas != null && facturas.size() > 1 && productoFacturaList != null
						&& !productoFacturaList.isEmpty() && productoFacturaList.get(0).getCantidad() != 0) {
					for (Factura f : facturas) {
						ProductoFactura pf = productoFacturaDAO
								.findByIdProAndIdFac(productoFacturaList.get(0).getProducto().getIdPro(), f.getIdFac());
						if (pf.getCantidad() == 0) {
							pf.setCantidad(productoFacturaList.get(0).getCantidad());
							productoFacturaDAO.update(pf);
							asignarDatosFactura(f, factura);
							break;
						}
					}
				}
			} else {
				for (ProductoFactura pf : productoFacturaList) {
					Producto producto = verificoStock(pf, factura);
					pf.setProducto(producto);
				}
				for (ProductoFactura pf : productoFacturaList) {
					actualizoStock(pf, factura, pf.getProducto());
				}
			}
		} catch (NegativeStockException e) {
			System.out.println(e.getMessage());
			continuo = false;
		}

		if (continuo) {
			if (productoFacturaList != null) {
				for (ProductoFactura pf : productoFacturaList) {
					productoFacturaDAO.delete(pf.getProducto().getIdPro(), id);
				}
			}
			List<FacturaEstado> facturaEstadoList = facturaEstadoDAO.findByIdFacModel(id);
			for (FacturaEstado fe : facturaEstadoList) {
				facturaEstadoDAO.delete(fe.getId());
			}
			borrada = facturaDAO.delete(id);
		}
		return borrada;
	}

	@Override
	public List<Factura> findByIdEstModel(int idEst, String column, HttpServletRequest request) {
		return facturaDAO.findByIdEstModel(idEst, column, request);
	}

	@Override
	public List<Factura> findByIdForModel(int idFor) {
		return facturaDAO.findByIdForModel(idFor);
	}

	@Override
	public List<Factura> findByIdList(int id) {
		return facturaDAO.findByIdList(id);
	}

	@Override
	public List<Factura> findByIdEstList(int idEst, String column, HttpServletRequest request) {
		List<Factura> facturas = facturaDAO.findByIdEstModel(idEst, column, request);
		Estado estado = estadoDAO.findById(idEst);
		for (Factura factura : facturas) {
			factura.setEstado(estado);
			factura.setFormaPago(formaPagoDAO.findById(factura.getFormaPago().getIdFor()));
		}
		return facturas;
	}

	@Override
	public List<Factura> findByIdCuo(int idCuo) {
		return facturaDAO.findByIdCuo(idCuo);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public List<Factura> selectExpire(List<Factura> facturas) {
		Date hoy = new Date();
		List<Factura> facturasVencen = new ArrayList<>();
		int milisegDif = 0;
		for(Factura factura: facturas) {
			milisegDif = factura.getFechaCompra().getDate() - hoy.getDate();
			if(0 < milisegDif && milisegDif < (86400000 * 5)) { //5 días
				facturasVencen.add(factura);
			}
		}
		return facturasVencen;
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

	private void asignarDatosFactura(Factura f, Factura factura) {
		f.setIvaTotal(factura.getIvaTotal());
		f.setIvaImporteTotal(factura.getIvaImporteTotal());
		f.setDescuentoTotal(factura.getDescuentoTotal());
		f.setDescuentoImporteTotal(factura.getDescuentoImporteTotal());
		f.setImporteTotal(factura.getImporteTotal());
		f.setObservaciones(factura.getObservaciones());
		f.setNumeroCuota(factura.getNumeroCuota());
		f.setImporteFront(factura.getImporteFront());
		facturaDAO.update(f);
	}

	private Producto verificoStock(ProductoFactura pf, Factura factura) throws NegativeStockException {
		Producto producto = productoService.findByIdModel(pf.getProducto().getIdPro());
		int stockFinal;
		if (factura.isCompra()) {
			stockFinal = producto.getUnidades() - pf.getCantidad();
		} else {
			stockFinal = producto.getUnidades() + pf.getCantidad();
		}
		if (stockFinal < 0) {
			throw new NegativeStockException("La cantidad de stock final sería: " + stockFinal);
		}
		return producto;
	}

	private void actualizoStock(ProductoFactura pf, Factura factura, Producto producto) throws NegativeStockException {

		int stockFinal;
		if (factura.isCompra()) {
			stockFinal = producto.getUnidades() - pf.getCantidad();
		} else {
			stockFinal = producto.getUnidades() + pf.getCantidad();
		}
		producto.setUnidades(stockFinal);
		productoService.update(producto);

	}

}
