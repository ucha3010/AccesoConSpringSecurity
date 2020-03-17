package com.damian.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.CategoriaDAO;
import com.damian.dao.ProductoDAO;
import com.damian.dao.ProductoEmpresaDAO;
import com.damian.dao.ProductoFacturaDAO;
import com.damian.dao.SubcategoriaDAO;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Categoria;
import com.damian.pojo.Cuota;
import com.damian.pojo.Estado;
import com.damian.pojo.Factura;
import com.damian.pojo.FormaPago;
import com.damian.pojo.Producto;
import com.damian.pojo.ProductoEmpresa;
import com.damian.pojo.ProductoFactura;
import com.damian.pojo.Subcategoria;
import com.damian.pojo.front.FrontCuota;
import com.damian.pojo.front.FrontProductoStock;
import com.damian.service.CuotaService;
import com.damian.service.FacturaService;
import com.damian.service.ProductoService;
import com.damian.utils.Constantes;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private CategoriaDAO categoriaDAO;

	@Autowired
	private CuotaService cuotaService;

	@Autowired
	private FacturaService facturaService;

	@Autowired
	private ProductoDAO productoDAO;

	@Autowired
	private ProductoFacturaDAO productoFacturaDAO;

	@Autowired
	private ProductoEmpresaDAO productoEmpresaDAO;

	@Autowired
	private SubcategoriaDAO subcategoriaDAO;

	@Override
	public List<Producto> findAll() {
		List<Producto> salida = productoDAO.findAll();
		for (Producto p : salida) {
			Subcategoria s = subcategoriaDAO.findByIdModel(p.getSubcategoria().getIdSub());
			Categoria c = categoriaDAO.findByIdModel(s.getCategoria().getIdCat());
			s.setCategoria(c);
			p.setSubcategoria(s);
		}
		return salida;
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
	public int save(Producto producto) {
		productoDAO.save(producto);
		return productoDAO.getMaxId();
	}

	@Override
	public void update(Producto producto) {
		productoDAO.update(producto);
	}

	@Override
	public int delete(int id) throws NotEmptyException {
		List<ProductoFactura> productoFacturaList = productoFacturaDAO.findByIdPro(id);
		if (productoFacturaList != null && !productoFacturaList.isEmpty()) {
			throw new NotEmptyException("Tiene asociado facturas");
		}
		List<ProductoEmpresa> productoEmpresaList = productoEmpresaDAO.findByIdPro(id);
		if (productoEmpresaList != null) {
			for (ProductoEmpresa p : productoEmpresaList) {
				productoEmpresaDAO.delete(id, p.getEmpresa().getIdEmp());
			}
		}
		return productoDAO.delete(id);
	}

	@Override
	public List<Producto> findByIdList(int id) {
		return productoDAO.findByIdList(id);
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
		productoDAO.save(producto);

		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		Factura factura = null;

		if (frontProductoStock.getCuotas() == null) {

			factura = new Factura();
			rellenaFacturaComun(factura, frontProductoStock, precioUnitConIva, precioUnitSinIva, context);
			factura.setImporteTotal(frontProductoStock.getPrecioFinal());
			factura.setImporteFront(frontProductoStock.getPrecioFinal());
			if (frontProductoStock.getIva() > 0 && frontProductoStock.getPrecioFinal() > 0) {
				factura.setIvaImporteTotal(((precioUnitConIva.subtract(precioUnitSinIva))
						.multiply(new BigDecimal(frontProductoStock.getCantidad(), MathContext.DECIMAL64)))
								.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
			}
			factura.setFechaCompra(new Date());

			int idFac = facturaService.save(factura, request);
			factura.setIdFac(idFac);
			ProductoFactura productoFactura = new ProductoFactura();
			productoFactura.setProducto(producto);
			productoFactura.setFactura(factura);
			productoFactura.setCantidad(frontProductoStock.getCantidad());
			productoFactura.setIvaProducto(frontProductoStock.getIva());
			productoFactura
					.setPrecioUnitConIva(precioUnitConIva.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
			if (frontProductoStock.getIva() > 0 && frontProductoStock.getPrecioFinal() > 0) {
				productoFactura.setPrecioUnitSinIva(
						precioUnitSinIva.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
			} else {
				productoFactura.setPrecioUnitSinIva(
						precioUnitConIva.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());

			}
			productoFactura.setPrecioFinal(frontProductoStock.getPrecioFinal());
			productoFacturaDAO.save(productoFactura);
		} else {
			BigDecimal comisionAperturaPor = new BigDecimal(frontProductoStock.getComisionAperturaPor(),
					MathContext.DECIMAL64);
			Cuota cuota = new Cuota();
			ProductoFactura productoFactura = new ProductoFactura();
			cuota.setCantidadCuotas(frontProductoStock.getCantidadCuotas());
			cuota.setComisionAperturaPor(frontProductoStock.getComisionAperturaPor());
			cuota.setInteresPor(frontProductoStock.getInteresPor());
			BigDecimal comisionAperturaImp = comisionAperturaPor.multiply(precioFinal).multiply(comaCeroUno);
			cuota.setComisionAperturaImp(Math.round(comisionAperturaImp.doubleValue() * 100.0) / 100.0);
			BigDecimal totalCompletoAPagar = new BigDecimal(0.0, MathContext.DECIMAL64);
			for (FrontCuota fc : frontProductoStock.getCuotas()) {
				totalCompletoAPagar = totalCompletoAPagar
						.add(new BigDecimal(fc.getImporteTotal(), MathContext.DECIMAL64));
			}
			if (frontProductoStock.getInteresPor() != 0) {
				BigDecimal interesImp = totalCompletoAPagar.subtract(comisionAperturaImp)
						.subtract(new BigDecimal(frontProductoStock.getPrecioFinal(), MathContext.DECIMAL64));
				cuota.setInteresImp(interesImp.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
			}
			int idCuo = cuotaService.save(cuota);
			cuota.setIdCuo(idCuo);

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

			for (FrontCuota fc : frontProductoStock.getCuotas()) {

				productoFactura = new ProductoFactura();
				factura = new Factura();
				rellenaFacturaComun(factura, frontProductoStock, precioUnitConIva, precioUnitSinIva, context);
				factura.setFechaCompra(fc.getFechaCompra());

				factura.setCuota(cuota);
				factura.setImporteCuotaTotal(fc.getImporteTotal());
				factura.setImporteFront(fc.getImporteTotal());
				factura.setNumeroCuota(fc.getNumeroCuota());

				BigDecimal cuotaSinIva = new BigDecimal(0, MathContext.DECIMAL64);
				BigDecimal cuotaConIva = new BigDecimal(0, MathContext.DECIMAL64);
				BigDecimal importeCuotaTotal = new BigDecimal(fc.getImporteTotal(), MathContext.DECIMAL64);
				BigDecimal interesCuotaImporte = new BigDecimal(0, MathContext.DECIMAL64);

				if (fc.getNumeroCuota() == 1) {
					factura.setImporteTotal(frontProductoStock.getPrecioFinal());
					if (frontProductoStock.getIva() > 0 && frontProductoStock.getPrecioFinal() > 0) {
						factura.setIvaImporteTotal(((precioUnitConIva.subtract(precioUnitSinIva))
								.multiply(new BigDecimal(frontProductoStock.getCantidad(), MathContext.DECIMAL64)))
										.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
					}
					interesCuotaImporte = importeCuotaTotal.subtract(comisionAperturaImp).subtract(cuotaSinInteres);
					productoFactura.setCantidad(frontProductoStock.getCantidad());
					productoFactura.setPrecioFinal(frontProductoStock.getPrecioFinal());
				} else if (fc.getNumeroCuota() == frontProductoStock.getCuotas().size()) {
					interesCuotaImporte = importeCuotaTotal.subtract(cuotaSinInteres.add(sumarUltimaCuota));
					productoFactura.setCantidad(0);
					productoFactura.setPrecioFinal(0);
				} else {
					interesCuotaImporte = importeCuotaTotal.subtract(cuotaSinInteres);
					productoFactura.setCantidad(0);
					productoFactura.setPrecioFinal(0);
				}
				if (frontProductoStock.getInteresPor() != 0) {
					factura.setInteresCuotaImporte(
							interesCuotaImporte.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
				}

				cuotaConIva = cuotaSinInteres;
				cuotaSinIva = cuotaSinInteres.divide(((comaCeroUno.multiply(iva)).add(uno)), 5, RoundingMode.HALF_UP);
				if (fc.getNumeroCuota() == frontProductoStock.getCuotas().size()) {
					cuotaConIva = cuotaConIva.add(sumarUltimaCuota);
					cuotaSinIva = cuotaSinIva.add(sumarUltimaCuota);
				}
				factura.setCuotaConIva(cuotaConIva.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
				factura.setCuotaSinIva(cuotaSinIva.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());

				int idFac = facturaService.save(factura, request);
				factura.setIdFac(idFac);

				productoFactura.setProducto(producto);
				productoFactura.setFactura(factura);
				productoFactura.setIvaProducto(frontProductoStock.getIva());
				productoFactura.setPrecioUnitConIva(precioUnitConIva.doubleValue());
				if (frontProductoStock.getIva() > 0 && frontProductoStock.getPrecioFinal() > 0) {
					productoFactura.setPrecioUnitSinIva(
							precioUnitSinIva.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
				} else {
					productoFactura.setPrecioUnitSinIva(
							precioUnitConIva.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());

				}
				productoFacturaDAO.save(productoFactura);
			}
		}

	}

	private void rellenaFacturaComun(Factura factura, FrontProductoStock frontProductoStock,
			BigDecimal precioUnitConIva, BigDecimal precioUnitSinIva,
			org.springframework.security.core.context.SecurityContextImpl context) {

		factura.setCompra(frontProductoStock.isCompra());
		factura.setIvaTotal(frontProductoStock.getIva());
		factura.setObservaciones(frontProductoStock.getObservaciones());
		factura.setFormaPago(new FormaPago(Constantes.MOVIMIENTO_STOCK, null, null));
		factura.setCreadoPor(context.getAuthentication().getName());
		if (frontProductoStock.isCompra()) {
			factura.setEstado(new Estado(Constantes.AGREGAR_STOCK, null, null));
		} else {
			factura.setEstado(new Estado(Constantes.QUITAR_STOCK, null, null));
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

}
