package com.damian.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.FacturaDAO;
import com.damian.dao.model.ModelFactura;
import com.damian.exceptions.NegativeStockException;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Cuota;
import com.damian.pojo.DireccionEmpresa;
import com.damian.pojo.EmpresaPropia;
import com.damian.pojo.Estado;
import com.damian.pojo.Factura;
import com.damian.pojo.FacturaEnviarFacturar;
import com.damian.pojo.FacturaEstado;
import com.damian.pojo.FormaPago;
import com.damian.pojo.Producto;
import com.damian.pojo.ProductoFactura;
import com.damian.pojo.front.ImpresionFactura;
import com.damian.pojo.front.ImpresionProducto;
import com.damian.service.CuotaService;
import com.damian.service.EmpresaPropiaService;
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
	private EmpresaPropiaService empresaPropiaService;

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

	@Override
	public List<Map<String, Object>> facturaMap(int idFac) {
		return facturaDAO.facturaMap(idFac);
	}

	@Override
	public ImpresionFactura findImpresionFacturaById(int idFac) {
		ModelFactura factura = facturaDAO.findModelById(idFac);
		FormaPago formaPago = formaPagoService.findByIdModel(factura.getIdFor());
		List<FacturaEnviarFacturar> facturaEnviarFacturarList = facturaEnviarFacturarService.findByIdFac(idFac);
		Cuota cuota = null;
		if (factura.getIdCuo() != 0) {
			cuota = cuotaService.findByIdModel(factura.getIdCuo());
		}
		List<EmpresaPropia> empresaPropiaList = empresaPropiaService.findAll();
		EmpresaPropia empresaPropia = new EmpresaPropia();
		if(!empresaPropiaList.isEmpty()) {
			empresaPropia = empresaPropiaList.get(0);
		}
		List<ProductoFactura> productoFacturaList = productoFacturaService.findByIdFacModel(idFac);

		return rellenarImpresionFactura(factura, formaPago, facturaEnviarFacturarList, cuota, empresaPropia,
				productoFacturaList);
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
					"FacturaService.actualizoStock - La cantidad de stock final sería: " + stockFinal);
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

	private ImpresionFactura rellenarImpresionFactura(ModelFactura factura, FormaPago formaPago,
			List<FacturaEnviarFacturar> facturaEnviarFacturarList, Cuota cuota, EmpresaPropia empresaPropia,
			List<ProductoFactura> productoFacturaList) {

		ImpresionFactura impresionFactura = new ImpresionFactura();
		int limiteDirección = 50;
		impresionFactura.setFechaCompra(factura.getFechaCompra());
		impresionFactura.setIdFac(factura.getIdFac());
		for (FacturaEnviarFacturar fef : facturaEnviarFacturarList) {
			if (fef.isEnviar()) {
				impresionFactura.setEntrega_nombre(fef.getNombre());
				if (fef.getDireccion() != null) {
					if (fef.getDireccion().length() > limiteDirección) {
						impresionFactura.setEntrega_direccion1(fef.getDireccion().substring(0, limiteDirección));
						impresionFactura.setEntrega_direccion2(fef.getDireccion().substring(limiteDirección));
					} else {
						impresionFactura.setEntrega_direccion1(fef.getDireccion());
					}
				}
				impresionFactura.setEntrega_cp(fef.getCp());
				impresionFactura.setEntrega_ciudad(fef.getCiudad());
				impresionFactura.setEntrega_provincia(fef.getProvincia());
				impresionFactura.setEntrega_pais(fef.getPais()); // multiidioma
				impresionFactura.setEntrega_telefono(fef.getTelefono());

			}
			if (fef.isFacturar()) {
				impresionFactura.setFactura_nombre(fef.getNombre());
				if (fef.getDireccion() != null) {
					if (fef.getDireccion().length() > limiteDirección) {
						impresionFactura.setFactura_direccion1(fef.getDireccion().substring(0, limiteDirección));
						impresionFactura.setFactura_direccion2(fef.getDireccion().substring(limiteDirección));
					} else {
						impresionFactura.setFactura_direccion1(fef.getDireccion());
					}
				}
				impresionFactura.setFactura_cp(fef.getCp());
				impresionFactura.setFactura_ciudad(fef.getCiudad());
				impresionFactura.setFactura_provincia(fef.getProvincia());
				impresionFactura.setFactura_pais(fef.getPais()); // multiidioma
				impresionFactura.setFactura_telefono(fef.getTelefono());

			}
		}
		impresionFactura.setFormaPago_nombre(formaPago.getNombreES()); // multiidioma
		impresionFactura.setFactura_observaciones(factura.getObservaciones());
		BigDecimal totalProductos = new BigDecimal(0);
		BigDecimal cien = new BigDecimal(100);
		BigDecimal totalSinIva = new BigDecimal(0);
		BigDecimal precioUnitSinIva = new BigDecimal(0);
		BigDecimal cantidad = new BigDecimal(0);
		BigDecimal importeEnvioSinIva = new BigDecimal(factura.getImporteEnvioSinIva(), MathContext.DECIMAL64);
		BigDecimal porcentajeDescuento = new BigDecimal(0);
		BigDecimal ivaProducto = new BigDecimal(0);
		BigDecimal descuentoTotal = new BigDecimal(factura.getDescuentoTotal(), MathContext.DECIMAL64);
		BigDecimal descuentoImporteTotal = new BigDecimal(factura.getDescuentoImporteTotal(), MathContext.DECIMAL64);
		BigDecimal ivaImporteTotal = new BigDecimal(factura.getIvaImporteTotal(), MathContext.DECIMAL64);
		List<ImpresionProducto> impresionProductoList = new ArrayList<>();
		ImpresionProducto ip;
		Producto producto;
		for (ProductoFactura pf : productoFacturaList) {
			precioUnitSinIva = new BigDecimal(pf.getPrecioUnitSinIva(), MathContext.DECIMAL64);
			cantidad = new BigDecimal(pf.getCantidad(), MathContext.DECIMAL64);
			totalProductos = totalProductos.add(precioUnitSinIva.multiply(cantidad));
			porcentajeDescuento = new BigDecimal(pf.getPorcentajeDescuento(), MathContext.DECIMAL64);
			ivaProducto = new BigDecimal(pf.getIvaProducto(), MathContext.DECIMAL64);
			ip = new ImpresionProducto();
			ip.setIdPro(pf.getProducto().getIdPro());
			producto = productoService.findById(pf.getProducto().getIdPro());
			ip.setProducto_nombre(producto.getNombreES()); // multiidioma
			//como el pattern de jasperrepot me multiplica este número por 100 tengo que hacer esto
			ip.setPorcentajeDescuento(porcentajeDescuento.divide(cien, 4, RoundingMode.DOWN).doubleValue());
			ip.setPrecioUnitSinIva(pf.getPrecioUnitSinIva());
			ip.setIvaProducto(ivaProducto.divide(cien, 4, RoundingMode.DOWN).doubleValue());
			ip.setCantidad(pf.getCantidad());
			ip.setPrecioFinalRecibidoPagado(pf.getPrecioFinalRecibidoPagado());
			ip.setProducto_observaciones(pf.getObservaciones());
			impresionProductoList.add(ip);
		}
		impresionFactura.setImpresionProductoList(impresionProductoList);
		impresionFactura.setTotalProductos(totalProductos.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
		impresionFactura.setImporteEnvioSinIva(factura.getImporteEnvioSinIva());
		impresionFactura.setDescuentoTotal(descuentoTotal.divide(cien, 4, RoundingMode.DOWN).doubleValue());
		impresionFactura.setDescuentoImporteTotal(factura.getDescuentoImporteTotal());
		totalSinIva = totalProductos.add(importeEnvioSinIva).subtract(descuentoImporteTotal);
		impresionFactura.setTotalSinIva(totalSinIva.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
		impresionFactura.setIvaImporteTotal(factura.getIvaImporteTotal());
		impresionFactura.setImporteTotal(
				(totalSinIva.add(ivaImporteTotal)).divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
		if (cuota == null) {
			impresionFactura.setHayCuotas(false);
			impresionFactura.setIdCuo(0);
		} else {
			impresionFactura.setHayCuotas(true);
			impresionFactura.setIdCuo(cuota.getIdCuo());
			impresionFactura.setComisionAperturaPor(cuota.getComisionAperturaPor());
			impresionFactura.setInteresPor(cuota.getInteresPor());
		}
		if (empresaPropia.getDireccionEmpresa() != null && empresaPropia.getDireccionEmpresa().getIdDirEmp() != 0) {
			DireccionEmpresa de = empresaPropia.getDireccionEmpresa();
			impresionFactura.setTipoVia(de.getTipoVia());
			impresionFactura.setNombreVia(de.getNombreVia());
			impresionFactura.setNumero(de.getNumero());
			impresionFactura.setResto(de.getResto());
			impresionFactura.setCp(de.getCp());
			impresionFactura.setCiudad(de.getCiudad());
			impresionFactura.setProvincia(de.getProvincia());
			if (de.getPais() != null) {
				impresionFactura.setPais(de.getPais().getNombreES()); // multiidioma
			}
		}
		impresionFactura.setCif(empresaPropia.getCif());
		impresionFactura.setTelefono(empresaPropia.getTelefono());

		return impresionFactura;
	}

}
