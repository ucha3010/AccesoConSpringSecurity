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
import com.damian.pojo.AdministracionOfertas;
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
import com.damian.service.AdministracionOfertasService;
import com.damian.service.CampaniaService;
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
import com.damian.utils.ConstantesLocales;
import com.damian.utils.Utils;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private AdministracionOfertasService administracionOfertasService;

	@Autowired
	private CampaniaService campaniaService;

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

	private BigDecimal cero = new BigDecimal(0, MathContext.DECIMAL64);
	private BigDecimal uno = new BigDecimal(1, MathContext.DECIMAL64);
	private BigDecimal cien = new BigDecimal(100, MathContext.DECIMAL64);
	private BigDecimal comaCeroUno = new BigDecimal(0.01, MathContext.DECIMAL64);

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

		BigDecimal ivaProducto = new BigDecimal(frontProductoStock.getIva(), MathContext.DECIMAL64);
		BigDecimal ivaImporteTotal;
		BigDecimal precioUnitSinIva;
		BigDecimal precioUnitario;
		BigDecimal precioFinalRecibidoPagado = new BigDecimal(frontProductoStock.getPrecioFinal(),
				MathContext.DECIMAL64);
		BigDecimal precioFinalSinIva = precioFinalRecibidoPagado.divide(((comaCeroUno.multiply(ivaProducto)).add(uno)),
				2, RoundingMode.HALF_UP);
		if (frontProductoStock.getCantidad() > 0) {
			ivaImporteTotal = precioFinalRecibidoPagado.subtract(precioFinalSinIva);
			precioUnitSinIva = precioFinalSinIva.divide(
					new BigDecimal(frontProductoStock.getCantidad(), MathContext.DECIMAL64), 2, RoundingMode.HALF_UP);
			precioUnitario = precioFinalRecibidoPagado.divide(
					new BigDecimal(frontProductoStock.getCantidad(), MathContext.DECIMAL64), 2, RoundingMode.HALF_UP);
		} else {
			ivaImporteTotal = cero;
			precioUnitSinIva = cero;
			precioUnitario = cero;
		}

		// Producto
		Producto producto = productoDAO.findById(frontProductoStock.getIdPro());
		if (frontProductoStock.isCompra()) {
			producto.setUnidades(producto.getUnidades() + frontProductoStock.getCantidad());
		} else {
			producto.setUnidades(producto.getUnidades() - frontProductoStock.getCantidad());
		}
		productoDAO.save(producto, request);

		// Factura
		Factura factura = new Factura();
		fillFactura(factura, frontProductoStock, precioFinalSinIva, ivaImporteTotal, request);
		int idFac = facturaService.save(factura, request);
		factura.setIdFac(idFac);

		// FacturaEnviarFacturar
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

		// ProductoFactura
		ProductoFactura productoFactura = new ProductoFactura();
		productoFactura.setProducto(producto);
		productoFactura.setFactura(factura);
		productoFactura.setCantidad(frontProductoStock.getCantidad());
		productoFactura.setIvaProducto(frontProductoStock.getIva());
		productoFactura.setIvaImporteTotal(ivaImporteTotal.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
		productoFactura.setDescuentoPor(0);
		productoFactura.setDescuentoImporteTotal(0);
		if (frontProductoStock.getIva() > 0 && frontProductoStock.getPrecioFinal() > 0) {
			productoFactura.setPrecioUnitSinIva(precioUnitSinIva.doubleValue());
		} else {
			productoFactura.setPrecioUnitSinIva(precioUnitario.doubleValue());
		}
		productoFactura.setPrecioUnitSinIvaConDesc(precioUnitSinIva.doubleValue());
		productoFactura.setPrecioUnitario(precioUnitario.doubleValue());
		productoFactura.setPrecioFinalSinIva(precioFinalSinIva.doubleValue());
		productoFactura.setPrecioFinalRecibidoPagado(frontProductoStock.getPrecioFinal());
		productoFactura.setObservaciones("");
		productoFacturaService.save(productoFactura, request);

		// Cuotas
		if (frontProductoStock.getCuotas() != null) {
			fillCuotas(frontProductoStock, precioFinalRecibidoPagado, factura, request);
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
	public List<Producto> findAllReducedData() {
		return productoDAO.findAllReducedData();
	}

	@Override
	public List<Producto> findProductosSinOferta(List<Producto> productos, List<AdministracionOfertas> ofertas,
			List<AdministracionOfertas> campanias) {

		return new ArrayList<>(reduceProductos(reduceProductos(productos, ofertas), campanias));
	}

	@Override
	public List<Producto> findProductosSinPopulares(List<Producto> productos,
			List<AdministracionOfertas> popularesList) {

		return new ArrayList<>(reduceProductos(productos, popularesList));

	}

	@Override
	public List<Producto> findProductosSinNovedades(List<Producto> productos,
			List<AdministracionOfertas> novedadesList) {

		return new ArrayList<>(reduceProductos(productos, novedadesList));
	}

	@Override
	public List<Producto> findProductosSinCampania(List<Producto> productos,
			List<AdministracionOfertas> productosCampaniaList) {

		List<Producto> productosSinCampania = new ArrayList<>(reduceProductos(productos, productosCampaniaList));
		for (Producto p : productosSinCampania) {
			p.setCampania(campaniaService.getCampaignName(p.getIdPro()));
		}
		List<AdministracionOfertas> productosConOfertaList = administracionOfertasService.findByOfertas(0);
		List<Integer> idProConOfertaList = new ArrayList<>();
		for(AdministracionOfertas ao: productosConOfertaList) {
			idProConOfertaList.add(ao.getIdPro());
		}
		List<Producto> productosSinCampaniaNiOferta = new ArrayList<>(productosSinCampania);
		for(Producto pr: productosSinCampania) {
			if(idProConOfertaList.contains(pr.getIdPro())) {
				productosSinCampaniaNiOferta.remove(pr);
			}
		}

		return productosSinCampaniaNiOferta;
	}

	@Override
	public List<Producto> findByIdSubModel(int idSub) {
		return productoDAO.findByIdSubModel(idSub);
	}

	@Override
	public int getMaxId() {
		return productoDAO.getMaxId();
	}

	private void fillFactura(Factura factura, FrontProductoStock frontProductoStock, BigDecimal precioFinalSinIva,
			BigDecimal ivaImporteTotal, HttpServletRequest request) {

		factura.setCompra(frontProductoStock.isCompra());
		factura.setTotalSinIvaEnvDescfac(precioFinalSinIva.doubleValue());
		factura.setDescuentoTotal(0);
		factura.setDescuentoImporteTotal(0);
		factura.setImporteEnvioSinIva(0);
		// totalSinIvaConDescfac = totalSinIvaEnvDescfac - descuentoImporteTotal +
		// importeEnvioSinIva
		factura.setTotalSinIvaConDescfac(precioFinalSinIva.doubleValue());
		factura.setIvaTotal(frontProductoStock.getIva());
		factura.setIvaImporteTotal(ivaImporteTotal.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
		factura.setImporteTotal(frontProductoStock.getPrecioFinal());
		factura.setFechaCompra(new Date());
		if (frontProductoStock.isCompra()) {
			factura.setEstado(new Estado(ConstantesLocales.AGREGAR_STOCK, null, null));
		} else {
			factura.setEstado(new Estado(ConstantesLocales.QUITAR_STOCK, null, null));
		}
		factura.setObservaciones(frontProductoStock.getObservaciones());
		factura.setFormaPago(new FormaPago(ConstantesLocales.MOVIMIENTO_STOCK, null, null));
		factura.setCreadoPor(Utils.getLoggedUser(request));

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

	private void fillCuotas(FrontProductoStock frontProductoStock, BigDecimal precioFinalRecibidoPagado,
			Factura factura, HttpServletRequest request) {

		BigDecimal comisionAperturaPor = new BigDecimal(frontProductoStock.getComisionAperturaPor(),
				MathContext.DECIMAL64);
		BigDecimal comisionAperturaImp = comisionAperturaPor.multiply(precioFinalRecibidoPagado).multiply(comaCeroUno);
		BigDecimal totalCompletoAPagar = new BigDecimal(0.0, MathContext.DECIMAL64);
		BigDecimal interesImp = new BigDecimal(0.0, MathContext.DECIMAL64);
		for (FrontCuota fc : frontProductoStock.getCuotas()) {
			totalCompletoAPagar = totalCompletoAPagar.add(new BigDecimal(fc.getImporteTotal(), MathContext.DECIMAL64));
		}
		if (frontProductoStock.getInteresPor() != 0) {
			interesImp = totalCompletoAPagar.subtract(comisionAperturaImp).subtract(precioFinalRecibidoPagado);
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

		BigDecimal cuotaSinInteres = precioFinalRecibidoPagado.divide(
				new BigDecimal(frontProductoStock.getCuotas().size(), MathContext.DECIMAL64), 5, RoundingMode.HALF_UP);
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

		// CuotaDetalle
		for (FrontCuota fc : frontProductoStock.getCuotas()) {

			BigDecimal importeCuotaTotal = new BigDecimal(fc.getImporteTotal(), MathContext.DECIMAL64);
			BigDecimal importeCuotaSinInteres = new BigDecimal(0, MathContext.DECIMAL64);
			BigDecimal interesCuotaImporte = new BigDecimal(0, MathContext.DECIMAL64);

			if (fc.getNumeroCuota() == 1) {
				capitalPendiente = precioFinalRecibidoPagado;
				interesCuotaImporte = importeCuotaTotal.subtract(comisionAperturaImp).subtract(cuotaSinInteres);
				importeCuotaSinInteres = importeCuotaTotal.subtract(interesCuotaImporte).subtract(comisionAperturaImp);
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
			cuotaDetalle
					.setImporteInteres(interesCuotaImporte.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
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

	private List<Producto> reduceProductos(List<Producto> productoList,
			List<AdministracionOfertas> administracionOfertasList) {

		List<Integer> idProList = new ArrayList<>();
		for (AdministracionOfertas ao : administracionOfertasList) {
			idProList.add(ao.getIdPro());
		}
		List<Producto> productoListOut = new ArrayList<>(productoList);
		if (!idProList.isEmpty()) {
			for (int i = 0; i < productoList.size(); i++) {
				if (idProList.contains(productoList.get(i).getIdPro())) {
					productoListOut.remove(productoList.get(i));
				}
			}
		}
		return new ArrayList<>(productoListOut);
	}

}
