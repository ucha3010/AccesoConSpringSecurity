package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.FacturaDAO;
import com.damian.exceptions.NegativeStockException;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Estado;
import com.damian.pojo.Factura;
import com.damian.pojo.FacturaEnviarFacturar;
import com.damian.pojo.FacturaEstado;
import com.damian.pojo.FormaPago;
import com.damian.pojo.Producto;
import com.damian.pojo.ProductoFactura;
import com.damian.service.CuotaService;
import com.damian.service.EstadoService;
import com.damian.service.FacturaEnviarFacturarService;
import com.damian.service.FacturaEstadoService;
import com.damian.service.FacturaService;
import com.damian.service.FormaPagoService;
import com.damian.service.ProductoFacturaService;
import com.damian.service.ProductoService;
import com.damian.utils.Utils;

@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	private FacturaDAO facturaDAO;

	@Autowired
	private CuotaService cuotaService;

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private FacturaEnviarFacturarService facturaEnviarFacturarService;

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

		factura.setModificadoPor(Utils.getLoggedUser(request));
		factura.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
		facturaDAO.save(factura, request);
		factura.setIdFac(facturaDAO.getMaxId());
		saveFacturaEstado(factura, request);
		return factura.getIdFac();

	}

	@Override
	public int update(Factura factura, HttpServletRequest request) {

		factura.setModificadoPor(Utils.getLoggedUser(request));
		factura.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
		facturaDAO.update(factura, request);
		saveFacturaEstado(factura, request);
		return factura.getIdFac();
	}

	@Override
	public int delete(int id, HttpServletRequest request) throws NotEmptyException {

		int borrada = 0;
		try {
			List<ProductoFactura> productoFacturaList = productoFacturaService.findByIdFacModel(id);
			Factura factura = findByIdModel(id);
			for (ProductoFactura pf : productoFacturaList) {
				Producto producto = verificoStock(pf, factura);
				pf.setProducto(producto);
			}
			for (ProductoFactura pf : productoFacturaList) {
				actualizoStock(pf, factura, pf.getProducto(), request);
			}
			if (factura.getCuota() != null && factura.getCuota().getIdCuo() != 0) {
				cuotaService.delete(factura.getCuota().getIdCuo(), request);
			}
			if (productoFacturaList != null) {
				for (ProductoFactura pf : productoFacturaList) {
					productoFacturaService.delete(pf.getProducto().getIdPro(), id, request);
				}
			}
			List<FacturaEstado> facturaEstadoList = facturaEstadoService.findByIdFacModel(id);
			for (FacturaEstado fe : facturaEstadoList) {
				facturaEstadoService.delete(fe.getId(), request);
			}
			List<FacturaEnviarFacturar> facturaEnviarFacturarList = facturaEnviarFacturarService.findByIdFac(id);
			for (FacturaEnviarFacturar fef : facturaEnviarFacturarList) {
				facturaEnviarFacturarService.delete(fef.getIdEnFa(), request);
			}
			borrada = facturaDAO.delete(id, request);
		} catch (NegativeStockException e) {
			System.out.println(e.getMessage());
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
			if (factura.getCuota().getIdCuo() != 0) {
				factura.setCuota(cuotaService.findById(factura.getCuota().getIdCuo()));
			}
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
			if (0 < milisegDif && milisegDif < (86400000 * 5)) { // 5 d�as
				facturasVencen.add(factura);
			}
		}
		return facturasVencen;
	}

	@Override
	public List<Factura> findSearchAll() {
		return facturaDAO.findSearchAll();
	}

	@Override
	public List<Map<String, Object>> facturaMap(int idFac) {
		return facturaDAO.facturaMap(idFac);
	}

	private void saveFacturaEstado(Factura factura, HttpServletRequest request) {

		FacturaEstado facturaEstado = new FacturaEstado();
		facturaEstado.setFactura(factura);
		facturaEstado.setEstado(factura.getEstado());
		Date utilDate = new Date();
		long lnMilisegundos = utilDate.getTime();
		facturaEstado.setFecha(new Timestamp(lnMilisegundos));
		facturaEstado.setCreadoPor(Utils.getLoggedUser(request));
		facturaEstadoService.save(facturaEstado, request);

	}

	private Producto verificoStock(ProductoFactura pf, Factura factura) throws NegativeStockException {
		Producto producto = productoService.findByIdModel(pf.getProducto().getIdPro());
		int stockFinal = calculoStockFinal(factura, producto, pf);
		if (stockFinal < 0) {
			throw new NegativeStockException(
					"FacturaService.actualizoStock - La cantidad de stock final ser�a: " + stockFinal);
		}
		return producto;
	}

	private void actualizoStock(ProductoFactura pf, Factura factura, Producto producto, HttpServletRequest request)
			throws NegativeStockException {

		producto.setUnidades(calculoStockFinal(factura, producto, pf));
		productoService.update(producto, request);

	}

	private int calculoStockFinal(Factura factura, Producto producto, ProductoFactura pf) {
		if (factura.isCompra()) {
			return producto.getUnidades() - pf.getCantidad();
		} else {
			return producto.getUnidades() + pf.getCantidad();
		}
	}

}
