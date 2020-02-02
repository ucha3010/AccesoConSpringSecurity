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

		BigDecimal iva = new BigDecimal(frontProductoStock.getIva(), MathContext.DECIMAL64);
		BigDecimal precioFinal = new BigDecimal(frontProductoStock.getPrecioFinal(), MathContext.DECIMAL64);
		BigDecimal precioUnitConIva;
		if (frontProductoStock.getCantidad() > 0) {
			precioUnitConIva = precioFinal.divide(
					new BigDecimal(frontProductoStock.getCantidad(), MathContext.DECIMAL64), 5, RoundingMode.HALF_UP);
		} else {
			precioUnitConIva = new BigDecimal(frontProductoStock.getPrecioFinal(), MathContext.DECIMAL64);
		}
		BigDecimal precioUnitSinIva = precioUnitConIva
				.divide(((new BigDecimal(0.01, MathContext.DECIMAL64).multiply(iva))
						.add(new BigDecimal(1, MathContext.DECIMAL64))), 5, RoundingMode.HALF_UP);

		Producto producto = productoDAO.findById(frontProductoStock.getIdPro());
		if (frontProductoStock.isCompra()) {
			producto.setUnidades(producto.getUnidades() + frontProductoStock.getCantidad());
		} else {
			producto.setUnidades(producto.getUnidades() - frontProductoStock.getCantidad());
		}
		productoDAO.save(producto);

		Factura factura = new Factura();

		if (frontProductoStock.getCuotas() == null) {
			factura.setCompra(frontProductoStock.isCompra());
			factura.setIvaTotal(frontProductoStock.getIva());
			if (frontProductoStock.getIva() > 0 && frontProductoStock.getPrecioFinal() > 0) {
				factura.setIvaImporteTotal(((precioUnitConIva.subtract(precioUnitSinIva))
						.multiply(new BigDecimal(frontProductoStock.getCantidad(), MathContext.DECIMAL64)))
								.doubleValue());
			}
			factura.setImporteTotal(frontProductoStock.getPrecioFinal());
			factura.setFechaCompra(new Date());
			factura.setObservaciones(frontProductoStock.getObservaciones());
			org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
					.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
			factura.setFormaPago(new FormaPago(Constantes.MOVIMIENTO_STOCK, null, null));
			factura.setCreadoPor(context.getAuthentication().getName());
			if (frontProductoStock.isCompra()) {
				factura.setEstado(new Estado(Constantes.AGREGAR_STOCK, null, null));
			} else {
				factura.setEstado(new Estado(Constantes.QUITAR_STOCK, null, null));
			}
			int idFac = facturaService.save(factura, request);
			factura.setIdFac(idFac);
			ProductoFactura productoFactura = new ProductoFactura();
			productoFactura.setProducto(producto);
			productoFactura.setFactura(factura);
			productoFactura.setCantidad(frontProductoStock.getCantidad());
			productoFactura.setIvaProducto(frontProductoStock.getIva());
			productoFactura.setPrecioUnitConIva(precioUnitConIva.doubleValue());
			if (frontProductoStock.getIva() > 0 && frontProductoStock.getPrecioFinal() > 0) {
				productoFactura.setPrecioUnitSinIva(precioUnitSinIva.doubleValue());
			} else {
				productoFactura.setPrecioUnitSinIva(precioUnitConIva.doubleValue());

			}
			productoFactura.setPrecioFinal(frontProductoStock.getPrecioFinal());
			productoFacturaDAO.save(productoFactura);
		} else {
			BigDecimal comisionAperturaPor = new BigDecimal(frontProductoStock.getComisionAperturaPor(),
					MathContext.DECIMAL64);
			Cuota cuota = new Cuota();
			cuota.setCantidadCuotas(frontProductoStock.getCantidadCuotas());
			cuota.setComisionAperturaPor(frontProductoStock.getComisionAperturaPor());
			cuota.setInteresPor(frontProductoStock.getInteresPor());
			BigDecimal comisionAperturaImp = comisionAperturaPor.multiply(precioFinal)
					.multiply(new BigDecimal(0.01, MathContext.DECIMAL64));
			cuota.setComisionAperturaImp(comisionAperturaImp.doubleValue());
			BigDecimal totalCompletoAPagar = new BigDecimal(0.0, MathContext.DECIMAL64);
			for (FrontCuota fc : frontProductoStock.getCuotas()) {
				totalCompletoAPagar = totalCompletoAPagar
						.add(new BigDecimal(fc.getImporteTotal(), MathContext.DECIMAL64));
			}
			BigDecimal interesImp = totalCompletoAPagar.subtract(comisionAperturaImp)
					.subtract(new BigDecimal(frontProductoStock.getPrecioFinal(), MathContext.DECIMAL64));
			cuota.setInteresImp(interesImp.doubleValue());
			int idCuo = cuotaService.save(cuota);
			// TODO DAMIAN continuar acá
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
