package com.damian.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.ProductoDAO;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Categoria;
import com.damian.pojo.Cuota;
import com.damian.pojo.CuotaDetalle;
import com.damian.pojo.DireccionEmpresaPropia;
import com.damian.pojo.EmpresaPropia;
import com.damian.pojo.Estado;
import com.damian.pojo.Factura;
import com.damian.pojo.FacturaEnviarFacturar;
import com.damian.pojo.FormaPago;
import com.damian.pojo.Foto;
import com.damian.pojo.Producto;
import com.damian.pojo.ProductoEmpresa;
import com.damian.pojo.ProductoFactura;
import com.damian.pojo.Subcategoria;
import com.damian.pojo.front.FrontCuota;
import com.damian.pojo.front.FrontProductoStock;
import com.damian.service.CategoriaService;
import com.damian.service.CuotaDetalleService;
import com.damian.service.CuotaService;
import com.damian.service.EmpresaPropiaService;
import com.damian.service.FacturaEnviarFacturarService;
import com.damian.service.FacturaService;
import com.damian.service.FotoService;
import com.damian.service.LanguageService;
import com.damian.service.ProductoEmpresaService;
import com.damian.service.ProductoFacturaService;
import com.damian.service.ProductoService;
import com.damian.service.SubcategoriaService;
import com.damian.utils.Constantes;
import com.damian.utils.Utils;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private CuotaService cuotaService;

	@Autowired
	private CuotaDetalleService cuotaDetalleService;

	@Autowired
	private EmpresaPropiaService empresaPropiaService;

	@Autowired
	private FacturaEnviarFacturarService facturaEnviarFacturarService;

	@Autowired
	private FacturaService facturaService;

	@Autowired
	private FotoService fotoService;

	@Autowired
	private LanguageService languageService;

	@Autowired
	private ProductoDAO productoDAO;

	@Autowired
	private ProductoFacturaService productoFacturaService;

	@Autowired
	private ProductoEmpresaService productoEmpresaService;

	@Autowired
	private SubcategoriaService subcategoriaService;

	@Override
	public List<Producto> findAll(String column, int paginaInicio, int totalPaginas, HttpServletRequest request) {
		List<Producto> salida = productoDAO.findAll(column, paginaInicio, totalPaginas, request);
		return fillCatSubcatYFotoPrinc(salida);
	}

	@Override
	public Producto findById(int id) {
		return productoDAO.findById(id);
	}

	@Override
	public Producto findByIdModel(int id) {
		return productoDAO.findByIdModel(id);
	}

	@Override
	public int save(Producto producto, HttpServletRequest request) {

		producto.setModificadoPor(Utils.getLoggedUser(request));
		producto.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		productoDAO.save(producto, request);
		return productoDAO.getMaxId();
	}

	@Override
	public void update(Producto producto, HttpServletRequest request) {

		producto.setModificadoPor(Utils.getLoggedUser(request));
		producto.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		productoDAO.update(producto, request);
	}

	@Override
	public int delete(int id, HttpServletRequest request) throws NotEmptyException {
		List<ProductoFactura> productoFacturaList = productoFacturaService.findByIdPro(id);
		if (productoFacturaList != null && !productoFacturaList.isEmpty()) {
			throw new NotEmptyException("Tiene asociado facturas");
		}
		List<ProductoEmpresa> productoEmpresaList = productoEmpresaService.findByIdPro(id);
		if (productoEmpresaList != null) {
			for (ProductoEmpresa p : productoEmpresaList) {
				productoEmpresaService.delete(id, p.getEmpresa().getIdEmp(), request);
			}
		}
		return productoDAO.delete(id, request);
	}

	@Override
	public List<Producto> findByIdList(int id) {
		List<Producto> salida = productoDAO.findByIdList(id);
		return fillCatSubcatYFotoPrinc(salida);
	}

	@Override
	public void saveProductoStock(FrontProductoStock frontProductoStock, HttpServletRequest request) {

		BigDecimal uno = new BigDecimal(1, MathContext.DECIMAL64);
		BigDecimal cien = new BigDecimal(100, MathContext.DECIMAL64);
		BigDecimal comaCeroUno = new BigDecimal(0.01, MathContext.DECIMAL64);
		BigDecimal iva = new BigDecimal(frontProductoStock.getIva(), MathContext.DECIMAL64);
		BigDecimal precioFinal = new BigDecimal(frontProductoStock.getPrecioFinal(), MathContext.DECIMAL64);
		BigDecimal precioUnitConIva;
		if (frontProductoStock.getCantidad() > 0) {
			precioUnitConIva = precioFinal.divide(
					new BigDecimal(frontProductoStock.getCantidad(), MathContext.DECIMAL64), 5, RoundingMode.HALF_UP);
		} else {
			precioUnitConIva = new BigDecimal(frontProductoStock.getPrecioFinal(), MathContext.DECIMAL64);
		}
		BigDecimal precioUnitSinIva = precioUnitConIva.divide(((comaCeroUno.multiply(iva)).add(uno)), 5,
				RoundingMode.HALF_UP);

		Producto producto = productoDAO.findById(frontProductoStock.getIdPro());
		if (frontProductoStock.isCompra()) {
			producto.setUnidades(producto.getUnidades() + frontProductoStock.getCantidad());
		} else {
			producto.setUnidades(producto.getUnidades() - frontProductoStock.getCantidad());
		}
		productoDAO.save(producto, request);

		Factura factura = new Factura();
		fillFactura(factura, frontProductoStock, precioUnitConIva, precioUnitSinIva, request);

		int idFac = facturaService.save(factura, request);
		factura.setIdFac(idFac);

		List<EmpresaPropia> empresaPropiaList = empresaPropiaService.findAll();
		EmpresaPropia empresaPropia = new EmpresaPropia();
		if (!empresaPropiaList.isEmpty()) {
			for (EmpresaPropia ep : empresaPropiaList) {
				if (ep.isFacturacion()) {
					empresaPropia = ep;
				}
			}
		}
		FacturaEnviarFacturar facturaEnviarFacturar = fillFacturaEnviarFacturar(empresaPropia, factura, request);
		if (facturaEnviarFacturar.getFactura() != null) {
			facturaEnviarFacturarService.save(facturaEnviarFacturar, request);
		}
		ProductoFactura productoFactura = new ProductoFactura();
		productoFactura.setProducto(producto);
		productoFactura.setFactura(factura);
		productoFactura.setCantidad(frontProductoStock.getCantidad());
		productoFactura.setIvaProducto(frontProductoStock.getIva());
		if (frontProductoStock.getIva() > 0 && frontProductoStock.getPrecioFinal() > 0) {
			productoFactura
					.setPrecioUnitSinIva(precioUnitSinIva.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
		} else {
			productoFactura
					.setPrecioUnitSinIva(precioUnitConIva.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
		}
		productoFactura.setPrecioFinalRecibidoPagado(frontProductoStock.getPrecioFinal());
		productoFacturaService.save(productoFactura, request);

		if (frontProductoStock.getCuotas() != null) {

			BigDecimal comisionAperturaPor = new BigDecimal(frontProductoStock.getComisionAperturaPor(),
					MathContext.DECIMAL64);
			BigDecimal comisionAperturaImp = comisionAperturaPor.multiply(precioFinal).multiply(comaCeroUno);
			BigDecimal totalCompletoAPagar = new BigDecimal(0.0, MathContext.DECIMAL64);
			BigDecimal interesImp = new BigDecimal(0.0, MathContext.DECIMAL64);
			for (FrontCuota fc : frontProductoStock.getCuotas()) {
				totalCompletoAPagar = totalCompletoAPagar
						.add(new BigDecimal(fc.getImporteTotal(), MathContext.DECIMAL64));
			}
			if (frontProductoStock.getInteresPor() != 0) {
				interesImp = totalCompletoAPagar.subtract(comisionAperturaImp).subtract(precioFinal);
			}

			Cuota cuota = new Cuota();
			cuota.setCantidadCuotas(frontProductoStock.getCantidadCuotas());
			cuota.setComisionAperturaPor(frontProductoStock.getComisionAperturaPor());
			cuota.setComisionAperturaImp(Math.round(comisionAperturaImp.doubleValue() * 100.0) / 100.0);
			cuota.setInteresPor(frontProductoStock.getInteresPor());
			cuota.setInteresImp(interesImp.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
			cuota.setTotalCompletoAPagar(totalCompletoAPagar.doubleValue());

			int idCuo = cuotaService.save(cuota, request);
			cuota.setIdCuo(idCuo);
			factura.setCuota(cuota);
			facturaService.update(factura, request);

			BigDecimal cuotaSinInteres = precioFinal.divide(
					new BigDecimal(frontProductoStock.getCuotas().size(), MathContext.DECIMAL64), 5,
					RoundingMode.HALF_UP);
			BigDecimal residuo = cuotaSinInteres.remainder(uno);
			residuo = residuo.multiply(cien);
			residuo = residuo.remainder(uno);
			BigDecimal residuoRestar = residuo.multiply(comaCeroUno);
			cuotaSinInteres = cuotaSinInteres.subtract(residuoRestar);
			BigDecimal residuoSumar = residuo
					.multiply(new BigDecimal(frontProductoStock.getCuotas().size(), MathContext.DECIMAL64));
			BigDecimal sumarUltimaCuota = new BigDecimal(Math.round(residuoSumar.doubleValue()), MathContext.DECIMAL64);
			sumarUltimaCuota = sumarUltimaCuota.multiply(comaCeroUno);
			BigDecimal capitalPendiente = new BigDecimal(0, MathContext.DECIMAL64);

			for (FrontCuota fc : frontProductoStock.getCuotas()) {

				BigDecimal importeCuotaTotal = new BigDecimal(fc.getImporteTotal(), MathContext.DECIMAL64);
				BigDecimal importeCuotaSinInteres = new BigDecimal(0, MathContext.DECIMAL64);
				BigDecimal interesCuotaImporte = new BigDecimal(0, MathContext.DECIMAL64);

				if (fc.getNumeroCuota() == 1) {
					capitalPendiente = precioFinal;
					interesCuotaImporte = importeCuotaTotal.subtract(comisionAperturaImp).subtract(cuotaSinInteres);
					importeCuotaSinInteres = importeCuotaTotal.subtract(interesCuotaImporte)
							.subtract(comisionAperturaImp);
				} else if (fc.getNumeroCuota() == frontProductoStock.getCuotas().size()) {
					interesCuotaImporte = importeCuotaTotal.subtract(cuotaSinInteres.add(sumarUltimaCuota));
					importeCuotaSinInteres = importeCuotaTotal.subtract(interesCuotaImporte);
				} else {
					interesCuotaImporte = importeCuotaTotal.subtract(cuotaSinInteres);
					importeCuotaSinInteres = importeCuotaTotal.subtract(interesCuotaImporte);
				}

				CuotaDetalle cuotaDetalle = new CuotaDetalle();
				cuotaDetalle.setImporteSinInteres(
						importeCuotaSinInteres.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
				cuotaDetalle.setImporteInteres(
						interesCuotaImporte.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
				cuotaDetalle.setImporteCuota(fc.getImporteTotal());
				cuotaDetalle.setFecha(fc.getFechaCobro());
				cuotaDetalle.setCapitalPendienteAntes(capitalPendiente.doubleValue());
				capitalPendiente = capitalPendiente.subtract(importeCuotaSinInteres);
				cuotaDetalle.setCapitalPendienteDespues(capitalPendiente.doubleValue());
				cuotaDetalle.setNumeroCuota(fc.getNumeroCuota());
				cuotaDetalle.setCuota(cuota);

				cuotaDetalleService.save(cuotaDetalle, request);
			}
		}

	}

	@Override
	public FrontProductoStock fillFrontProductoStock(Producto producto) {

		FrontProductoStock frontProductoStock = new FrontProductoStock();
		frontProductoStock.setIdPro(producto.getIdPro());
		frontProductoStock.setUnidades(producto.getUnidades());
		frontProductoStock.setNombreES(producto.getNombreES());
		frontProductoStock.setNombreEN(producto.getNombreEN());
		frontProductoStock.setNombrePT(producto.getNombrePT());
		frontProductoStock.setNombreFR(producto.getNombreFR());
		frontProductoStock.setNombreIT(producto.getNombreIT());
		frontProductoStock.setNombreGE(producto.getNombreGE());
		frontProductoStock.setNombreCA(producto.getNombreCA());
		frontProductoStock.setNombreEU(producto.getNombreEU());
		frontProductoStock.setCompra(true);
		frontProductoStock.setCuotas(new ArrayList<FrontCuota>());
		return frontProductoStock;
	}

	@Override
	public List<Producto> findSearchAll() {
		return productoDAO.findSearchAll();
	}

	@Override
	public List<Producto> findByIdSubModel(int idSub) {
		return productoDAO.findByIdSubModel(idSub);
	}

	@Override
	public int getMaxId() {
		return productoDAO.getMaxId();
	}

	private void fillFactura(Factura factura, FrontProductoStock frontProductoStock, BigDecimal precioUnitConIva,
			BigDecimal precioUnitSinIva, HttpServletRequest request) {

		factura.setCompra(frontProductoStock.isCompra());
		factura.setIvaTotal(frontProductoStock.getIva());
		factura.setObservaciones(frontProductoStock.getObservaciones());
		factura.setFormaPago(new FormaPago(Constantes.MOVIMIENTO_STOCK, null, null));
		factura.setCreadoPor(Utils.getLoggedUser(request));
		if (frontProductoStock.isCompra()) {
			factura.setEstado(new Estado(Constantes.AGREGAR_STOCK, null, null));
		} else {
			factura.setEstado(new Estado(Constantes.QUITAR_STOCK, null, null));
		}
		factura.setImporteTotal(frontProductoStock.getPrecioFinal());
		if (frontProductoStock.getIva() > 0 && frontProductoStock.getPrecioFinal() > 0) {
			factura.setIvaImporteTotal(((precioUnitConIva.subtract(precioUnitSinIva))
					.multiply(new BigDecimal(frontProductoStock.getCantidad(), MathContext.DECIMAL64)))
							.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
		}
		factura.setFechaCompra(new Date());

	}

	private List<Producto> fillCatSubcatYFotoPrinc(List<Producto> salida) {
		for (Producto p : salida) {
			Subcategoria s = subcategoriaService.findByIdModel(p.getSubcategoria().getIdSub());
			Categoria c = categoriaService.findByIdModel(s.getCategoria().getIdCat());
			s.setCategoria(c);
			p.setSubcategoria(s);
			p.setFotos(fillFotoPrincipal(p.getIdPro()));
		}
		return salida;
	}

	private List<Foto> fillFotoPrincipal(int idPro) {

		List<Foto> fotos = fotoService.findByIdPro(idPro);
		List<Foto> fotoPrincipal = null;
		for (Foto foto : fotos) {
			if (foto.isPrincipal()) {
				fotoPrincipal = new ArrayList<>();
				fotoPrincipal.add(foto);
				break;
			}
		}
		return fotoPrincipal;
	}

	private FacturaEnviarFacturar fillFacturaEnviarFacturar(EmpresaPropia empresaPropia, Factura factura,
			HttpServletRequest request) {

		FacturaEnviarFacturar facturaEnviarFacturar = new FacturaEnviarFacturar();
		if (empresaPropia.getIdPropia() != 0) {
			facturaEnviarFacturar.setNombre(empresaPropia.getRazonSocial());
			if (empresaPropia.getDireccionEmpresaPropia() != null) {
				DireccionEmpresaPropia dep = empresaPropia.getDireccionEmpresaPropia();

				String via = languageService.getMessage(dep.getTipoVia(), new Locale("es", "ES"), request);
				facturaEnviarFacturar.setDireccion(
						Utils.entradaOVacio(via).concat(" ").concat(Utils.entradaOVacio(dep.getNombreVia())).concat(" ")
								.concat(Utils.entradaOVacio(dep.getNumero())).concat(" ")
								.concat(Utils.entradaOVacio(dep.getResto())));
				facturaEnviarFacturar.setCp(dep.getCp());
				facturaEnviarFacturar.setCiudad(dep.getCiudad());
				facturaEnviarFacturar.setProvincia(dep.getProvincia());
				if (dep.getPais() != null) {
					facturaEnviarFacturar.setPais(dep.getPais().getNombreES());
				}
			}
			facturaEnviarFacturar.setTelefono(empresaPropia.getTelefono());
			facturaEnviarFacturar.setFacturar(true);
			facturaEnviarFacturar.setEnviar(true);
			facturaEnviarFacturar.setFactura(factura);
		}

		return facturaEnviarFacturar;
	}

}
