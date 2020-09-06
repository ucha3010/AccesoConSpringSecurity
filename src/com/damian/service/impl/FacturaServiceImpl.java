package com.damian.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.FacturaDAO;
import com.damian.dao.model.ModelCuotaDetalle;
import com.damian.dao.model.ModelFactura;
import com.damian.exceptions.NegativeStockException;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Cuota;
import com.damian.pojo.DireccionEmpresaPropia;
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
import com.damian.service.LanguageService;
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
	private LanguageService languageService;

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
	public ImpresionFactura findImpresionFacturaById(int idFac, HttpServletRequest request) {
		ModelFactura factura = facturaDAO.findModelById(idFac);
		FormaPago formaPago = formaPagoService.findByIdModel(factura.getIdFor());
		List<FacturaEnviarFacturar> facturaEnviarFacturarList = facturaEnviarFacturarService.findByIdFac(idFac);
		Cuota cuota = null;
		if (factura.getIdCuo() != 0) {
			cuota = cuotaService.findByIdModel(factura.getIdCuo());
		}
		List<EmpresaPropia> empresaPropiaList = empresaPropiaService.findAll();
		EmpresaPropia empresaPropia = new EmpresaPropia();
		for (EmpresaPropia ep : empresaPropiaList) {
			if (ep.isFacturacion()) {
				empresaPropia = ep;
			}
		}
		List<ProductoFactura> productoFacturaList = productoFacturaService.findByIdFacModel(idFac);

		return rellenarImpresionFactura(factura, formaPago, facturaEnviarFacturarList, cuota, empresaPropia,
				productoFacturaList, request);
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
			List<ProductoFactura> productoFacturaList, HttpServletRequest request) {

		ImpresionFactura impresionFactura = new ImpresionFactura();
		BigDecimal cien = new BigDecimal(100);
		BigDecimal porcentajeDescuento = new BigDecimal(0);
		BigDecimal ivaProducto = new BigDecimal(0);
		BigDecimal descuentoTotal = new BigDecimal(factura.getDescuentoTotal(), MathContext.DECIMAL64);
		int limiteDireccion = 50;

		impresionFactura.setFechaCompra(factura.getFechaCompra());
		impresionFactura.setIdFac(factura.getIdFac());
		for (FacturaEnviarFacturar fef : facturaEnviarFacturarList) {
			if (fef.isEnviar()) {
				impresionFactura.setEntrega_nombre(fef.getNombre());
				impresionFactura.setEntrega_direccion1(Utils.cortaCadena(fef.getDireccion(), 0, limiteDireccion));
				impresionFactura.setEntrega_direccion2(
						Utils.cortaCadena(fef.getDireccion(), limiteDireccion, limiteDireccion * 2));
				impresionFactura.setEntrega_cp(fef.getCp());
				impresionFactura.setEntrega_ciudad(fef.getCiudad());
				impresionFactura.setEntrega_provincia(fef.getProvincia());
				impresionFactura.setEntrega_pais(fef.getPais()); // multiidioma
				impresionFactura.setEntrega_telefono(fef.getTelefono());

			}
			if (fef.isFacturar()) {
				impresionFactura.setFactura_nombre(fef.getNombre());
				impresionFactura.setFactura_direccion1(Utils.cortaCadena(fef.getDireccion(), 0, limiteDireccion));
				impresionFactura.setFactura_direccion2(
						Utils.cortaCadena(fef.getDireccion(), limiteDireccion, limiteDireccion * 2));
				impresionFactura.setFactura_cp(fef.getCp());
				impresionFactura.setFactura_ciudad(fef.getCiudad());
				impresionFactura.setFactura_provincia(fef.getProvincia());
				impresionFactura.setFactura_pais(fef.getPais()); // multiidioma
				impresionFactura.setFactura_telefono(fef.getTelefono());

			}
		}
		impresionFactura.setFormaPago_nombre(formaPago.getNombreES()); // multiidioma
		impresionFactura.setFactura_observaciones(factura.getObservaciones());

		List<ImpresionProducto> impresionProductoList = new ArrayList<>();
		ImpresionProducto ip;
		Producto producto;
		for (ProductoFactura pf : productoFacturaList) {
			porcentajeDescuento = new BigDecimal(pf.getDescuentoPor(), MathContext.DECIMAL64);
			ivaProducto = new BigDecimal(pf.getIvaProducto(), MathContext.DECIMAL64);
			ip = new ImpresionProducto();
			ip.setIdPro(pf.getProducto().getIdPro());
			producto = productoService.findById(pf.getProducto().getIdPro());
			ip.setProducto_nombre(producto.getNombreES()); // multiidioma
			ip.setPrecioUnitSinIva(pf.getPrecioUnitSinIva());
			// como el pattern de jasperrepot me multiplica este número por 100 tengo que
			// hacer esto
			ip.setPorcentajeDescuento(porcentajeDescuento.divide(cien, 4, RoundingMode.DOWN).doubleValue());
			ip.setPrecioUnitSinIvaConDesc(pf.getPrecioUnitSinIvaConDesc());
			ip.setIvaProducto(ivaProducto.divide(cien, 4, RoundingMode.DOWN).doubleValue());
			ip.setCantidad(pf.getCantidad());
			ip.setPrecioFinalRecibidoPagado(pf.getPrecioFinalRecibidoPagado());
			ip.setProducto_observaciones(pf.getObservaciones());
			impresionProductoList.add(ip);
		}
		impresionFactura.setImpresionProductoList(impresionProductoList);

		impresionFactura.setTotalSinIvaEnvDescfac(factura.getTotalSinIvaEnvDescfac());
		impresionFactura.setImporteEnvioSinIva(factura.getImporteEnvioSinIva());
		impresionFactura.setDescuentoTotal(descuentoTotal.divide(cien, 4, RoundingMode.DOWN).doubleValue());
		impresionFactura.setDescuentoImporteFactura(factura.getDescuentoImporteFactura());
		impresionFactura.setTotalSinIvaConDescfac(factura.getTotalSinIvaConDescfac());
		impresionFactura.setIvaImporteTotal(factura.getIvaImporteTotal());
		impresionFactura.setImporteTotal(factura.getImporteTotal());

		if (cuota == null) {
			impresionFactura.setHayCuotas(false);
			impresionFactura.setIdCuo(0);
		} else {
			BigDecimal comisionAperturaPor = new BigDecimal(cuota.getComisionAperturaPor(), MathContext.DECIMAL64);
			BigDecimal interesPor = new BigDecimal(cuota.getInteresPor(), MathContext.DECIMAL64);
			impresionFactura.setHayCuotas(true);
			impresionFactura.setIdCuo(cuota.getIdCuo());
			impresionFactura
					.setComisionAperturaPor(comisionAperturaPor.divide(cien, 4, RoundingMode.DOWN).doubleValue());
			impresionFactura.setInteresPor(interesPor.divide(cien, 4, RoundingMode.DOWN).doubleValue());
		}
		StringBuilder primerRenglon = new StringBuilder();
		StringBuilder segundoRenglon = new StringBuilder();
		StringBuilder tercerRenglon = new StringBuilder();
		if (empresaPropia.getDireccionEmpresaPropia() != null
				&& empresaPropia.getDireccionEmpresaPropia().getIdDirPropia() != 0) {
			DireccionEmpresaPropia dep = empresaPropia.getDireccionEmpresaPropia();
			String via = languageService.getMessage(dep.getTipoVia(), new Locale("es", "ES"), request);
			primerRenglon.append(Utils.entradaOVacio(via));
			primerRenglon.append(Utils.siHayDatoAgregoEspacio(dep.getTipoVia()));
			primerRenglon.append(Utils.entradaOVacio(dep.getNombreVia()));
			primerRenglon.append(Utils.siHayDatoAgregoEspacio(dep.getNombreVia()));
			primerRenglon.append(Utils.entradaOVacio(dep.getNumero()));
			primerRenglon.append(Utils.siHayDatoAgregoEspacio(dep.getNumero()));
			primerRenglon.append(Utils.entradaOVacio(dep.getResto()));
			segundoRenglon.append(Utils.entradaOVacio(dep.getCp()));
			segundoRenglon.append(Utils.siHayDatoAgregoEspacio(dep.getCp()));
			segundoRenglon.append(Utils.entradaOVacio(dep.getCiudad()));
			segundoRenglon.append(Utils.siHayDatoAgregoEspacio(dep.getCiudad()));
			segundoRenglon.append(Utils.entradaOVacio(dep.getProvincia()));
			segundoRenglon.append(Utils.siHayDatoAgregoEspacio(dep.getProvincia()));
			if (dep.getPais() != null) {
				segundoRenglon.append(dep.getPais().getNombreES()); // multiidioma
			}
		}
		tercerRenglon.append("CIF: " + Utils.entradaOVacio(empresaPropia.getCif()));
		String espacioCif = Utils.siHayDatoAgregoEspacio(empresaPropia.getCif());
		tercerRenglon.append(espacioCif + espacioCif + espacioCif + espacioCif);
		tercerRenglon.append(Utils.entradaOVacio(empresaPropia.getTelefono()));
		impresionFactura.setPrimerRenglon(primerRenglon.toString());
		impresionFactura.setSegundoRenglon(segundoRenglon.toString());
		impresionFactura.setTercerRenglon(tercerRenglon.toString());

		return impresionFactura;
	}

	@Override
	public void defineJrxml(ImpresionFactura factura, List<ModelCuotaDetalle> cuotaDetalleList) {

		boolean envio = factura.getImporteEnvioSinIva() != 0.0;
		boolean descuento = factura.getDescuentoImporteFactura() != 0.0;
		int cantidadCuotas = cuotaDetalleList.size();

		if (cantidadCuotas > 0) {
			if (cantidadCuotas <= 3) {
				factura.setJrxml("facturaReportCuotas03");
			} else if (cantidadCuotas <= 6) {
				factura.setJrxml("facturaReportCuotas06");
			} else if (cantidadCuotas <= 9) {
				factura.setJrxml("facturaReportCuotas09");
			} else if (cantidadCuotas <= 12) {
				factura.setJrxml("facturaReportCuotas12");
			}
		} else {
			if (envio && descuento) {
				factura.setJrxml("facturaReportSiEySiD");
			} else if (envio && !descuento) {
				factura.setJrxml("facturaReportSiEyNoD");
			} else if (!envio && descuento) {
				factura.setJrxml("facturaReportNoEySiD");
			} else if (!envio && !descuento) {
				factura.setJrxml("facturaReportNoEyNoD");
			}
		}
	}

}
