package com.damian.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
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
import com.damian.pojo.Estado;
import com.damian.pojo.Factura;
import com.damian.pojo.FormaPago;
import com.damian.pojo.Producto;
import com.damian.pojo.ProductoEmpresa;
import com.damian.pojo.ProductoFactura;
import com.damian.pojo.Subcategoria;
import com.damian.pojo.front.FrontProductoStock;
import com.damian.service.FacturaService;
import com.damian.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private CategoriaDAO categoriaDAO;

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
			precioUnitConIva = precioFinal
					.divide(new BigDecimal(frontProductoStock.getCantidad(), MathContext.DECIMAL64));
		} else {
			precioUnitConIva = new BigDecimal(frontProductoStock.getPrecioFinal(), MathContext.DECIMAL64);
		}
		BigDecimal precioUnitSinIva = precioUnitConIva
				.divide(((new BigDecimal(0.01, MathContext.DECIMAL64).multiply(iva))
						.add(new BigDecimal(1, MathContext.DECIMAL64))));

		Producto producto = productoDAO.findById(frontProductoStock.getIdPro());
		Factura factura = new Factura();
		factura.setCompra(frontProductoStock.isCompra());
		factura.setIvaTotal(frontProductoStock.getIva());
		if (frontProductoStock.getIva() > 0 && frontProductoStock.getPrecioFinal() > 0) {
			factura.setIvaImporteTotal(((precioUnitConIva.subtract(precioUnitSinIva))
					.multiply(new BigDecimal(frontProductoStock.getCantidad(), MathContext.DECIMAL64))).doubleValue());
		}
		factura.setImporteTotal(frontProductoStock.getPrecioFinal());
		factura.setFechaCompra(new Date());
		factura.setObservaciones(frontProductoStock.getObservaciones());
		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		factura.setFormaPago(new FormaPago(4, null));
		factura.setCreadoPor(context.getAuthentication().getName());
		if (frontProductoStock.isCompra()) {
			producto.setUnidades(producto.getUnidades() + frontProductoStock.getCantidad());
			factura.setEstado(new Estado(6, null, null));
		} else {
			producto.setUnidades(producto.getUnidades() - frontProductoStock.getCantidad());
			factura.setEstado(new Estado(7, null, null));

		}
		productoDAO.save(producto);
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

	}

}
