package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.FacturaDAO;
import com.damian.exceptions.NegativeStockException;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Estado;
import com.damian.pojo.Factura;
import com.damian.pojo.FacturaEstado;
import com.damian.pojo.FormaPago;
import com.damian.pojo.Producto;
import com.damian.pojo.ProductoFactura;
import com.damian.service.CuotaService;
import com.damian.service.EstadoService;
import com.damian.service.FacturaEstadoService;
import com.damian.service.FacturaService;
import com.damian.service.FormaPagoService;
import com.damian.service.ProductoFacturaService;
import com.damian.service.ProductoService;

@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	private FacturaDAO facturaDAO;

	@Autowired
	private CuotaService cuotaService;

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private FacturaEstadoService facturaEstadoService;

	@Autowired
	private FormaPagoService formaPagoService;

	@Autowired
	private ProductoService productoService;

	@Autowired
	private ProductoFacturaService productoFacturaService;

	@Override
	public List<Factura> findAll(String column, int paginaInicio, int totalPaginas, HttpServletRequest request) {
		List<Factura> facturas = facturaDAO.findAll(column, paginaInicio, totalPaginas, request);
		for (Factura factura : facturas) {
			Estado estado = estadoService.findByIdModel(factura.getEstado().getIdEst());
			FormaPago formaPago = formaPagoService.findById(factura.getFormaPago().getIdFor());
			factura.setEstado(estado);
			factura.setFormaPago(formaPago);
			if (factura.getCuota() != null && factura.getCuota().getIdCuo() != 0) {
				factura.setCuota(cuotaService.findById(factura.getCuota().getIdCuo()));
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

		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		factura.setModificadoPor(context.getAuthentication().getName());
		factura.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
		facturaDAO.save(factura);
		factura.setIdFac(facturaDAO.getMaxId());
		saveFacturaEstado(factura, request);
		return factura.getIdFac();

	}

	@Override
	public int update(Factura factura, HttpServletRequest request) {

		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		factura.setModificadoPor(context.getAuthentication().getName());
		factura.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
		facturaDAO.update(factura);
		saveFacturaEstado(factura, request);
		return factura.getIdFac();
	}

	@Override
	public int delete(int id, HttpServletRequest request) throws NotEmptyException {
		List<ProductoFactura> productoFacturaList = productoFacturaService.findByIdFacModel(id);
		Factura factura = findByIdModel(id);
		int borrada = 0;
		boolean continuo = true;

		try {
			if (factura.getCuota() != null && factura.getCuota().getIdCuo() != 0) {
				List<Factura> facturas = findByIdCuo(factura.getCuota().getIdCuo());
				if (facturas != null && facturas.size() == 1) {
					Producto producto = verificoStock(productoFacturaList.get(0), factura);
					actualizoStock(productoFacturaList.get(0), factura, producto, request);
					cuotaService.delete(factura.getCuota().getIdCuo());
				} else if (facturas != null && facturas.size() > 1 && productoFacturaList != null
						&& !productoFacturaList.isEmpty() && productoFacturaList.get(0).getCantidad() != 0) {
					for (Factura f : facturas) {
						ProductoFactura pf = productoFacturaService
								.findByIdProAndIdFac(productoFacturaList.get(0).getProducto().getIdPro(), f.getIdFac());
						if (pf.getCantidad() == 0) {
							pf.setCantidad(productoFacturaList.get(0).getCantidad());
							productoFacturaService.update(pf, request);
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
					actualizoStock(pf, factura, pf.getProducto(), request);
				}
			}
		} catch (NegativeStockException e) {
			System.out.println(e.getMessage());
			continuo = false;
		}

		if (continuo) {
			if (productoFacturaList != null) {
				for (ProductoFactura pf : productoFacturaList) {
					productoFacturaService.delete(pf.getProducto().getIdPro(), id);
				}
			}
			List<FacturaEstado> facturaEstadoList = facturaEstadoService.findByIdFacModel(id);
			for (FacturaEstado fe : facturaEstadoList) {
				facturaEstadoService.delete(fe.getId());
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
		Estado estado = estadoService.findById(idEst);
		for (Factura factura : facturas) {
			factura.setEstado(estado);
			factura.setFormaPago(formaPagoService.findById(factura.getFormaPago().getIdFor()));
		}
		return facturas;
	}

	@Override
	public List<Factura> findByIdCuo(int idCuo) {
		return facturaDAO.findByIdCuo(idCuo);
	}

	@Override
	public List<Factura> selectExpire(List<Factura> facturas) {
		Date hoy = new Date();
		List<Factura> facturasVencen = new ArrayList<>();
		long milisegDif = 0;
		for (Factura factura : facturas) {
			milisegDif = factura.getFechaCompra().getTime() - hoy.getTime();
			if (0 < milisegDif && milisegDif < (86400000 * 5)) { // 5 días
				facturasVencen.add(factura);
			}
		}
		return facturasVencen;
	}

	@Override
	public List<Factura> findSearchAll() {
		return facturaDAO.findSearchAll();
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
		facturaEstadoService.save(facturaEstado, request);

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

	private void actualizoStock(ProductoFactura pf, Factura factura, Producto producto, HttpServletRequest request)
			throws NegativeStockException {

		int stockFinal;
		if (factura.isCompra()) {
			stockFinal = producto.getUnidades() - pf.getCantidad();
		} else {
			stockFinal = producto.getUnidades() + pf.getCantidad();
		}
		producto.setUnidades(stockFinal);
		productoService.update(producto, request);

	}

}
