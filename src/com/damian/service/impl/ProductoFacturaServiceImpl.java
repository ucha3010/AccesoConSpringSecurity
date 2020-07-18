package com.damian.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.ProductoFacturaDAO;
import com.damian.pojo.ProductoFactura;
import com.damian.pojo.front.FrontProducto;
import com.damian.service.ProductoFacturaService;

@Service
public class ProductoFacturaServiceImpl implements ProductoFacturaService {

	@Autowired
	private ProductoFacturaDAO productoFacturaDAO;

	@Override
	public List<ProductoFactura> findAll() {
		return productoFacturaDAO.findAll();
	}

	@Override
	public void save(ProductoFactura productoFactura, HttpServletRequest request) {

		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		productoFactura.setModificadoPor(context.getAuthentication().getPrincipal().toString());
		productoFactura.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		productoFacturaDAO.save(productoFactura, request);
	}

	@Override
	public void update(ProductoFactura productoFactura, HttpServletRequest request) {

		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		productoFactura.setModificadoPor(context.getAuthentication().getPrincipal().toString());
		productoFactura.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		productoFacturaDAO.update(productoFactura, request);
	}

	@Override
	public void delete(int idPro, int idFac, HttpServletRequest request) {
		productoFacturaDAO.delete(idPro, idFac, request);
	}

	@Override
	public List<ProductoFactura> findByIdPro(int idPro) {
		return productoFacturaDAO.findByIdPro(idPro);
	}

	@Override
	public List<ProductoFactura> findByIdFac(int idFac) {
		return productoFacturaDAO.findByIdFac(idFac);
	}

	@Override
	public List<ProductoFactura> findByIdProModel(int idPro) {
		return productoFacturaDAO.findByIdProModel(idPro);
	}

	@Override
	public List<ProductoFactura> findByIdFacModel(int idFac) {
		return productoFacturaDAO.findByIdFacModel(idFac);
	}

	@Override
	public ProductoFactura findByIdProAndIdFac(int idPro, int idFac) {
		return productoFacturaDAO.findByIdProAndIdFac(idPro, idFac);
	}

	@Override
	public List<FrontProducto> findByIdFacFront(int idFac) {
		List<ProductoFactura> productoFacturas = findByIdFac(idFac);
		List<FrontProducto> pfFront = new ArrayList<>();
		FrontProducto fp;
		BigDecimal comaCeroUno = new BigDecimal(0.01, MathContext.DECIMAL64);
		BigDecimal descuentoImp = new BigDecimal(0);
		BigDecimal ivaProductoPor = new BigDecimal(0);
		BigDecimal ivaProductoImp = new BigDecimal(0);
		BigDecimal precioUnitSinIva = new BigDecimal(0);
		BigDecimal precioUnitFinal = new BigDecimal(0);
		BigDecimal cantidad = new BigDecimal(0);
		BigDecimal precioFinal = new BigDecimal(0);
		for(ProductoFactura pf: productoFacturas) {
			fp = new FrontProducto();
			fp.setObservaciones(pf.getObservaciones());
			fp.setIdPro(pf.getProducto().getIdPro());
			fp.setNombreES(pf.getProducto().getNombreES());
			fp.setNombreCA(pf.getProducto().getNombreCA());
			fp.setNombreEN(pf.getProducto().getNombreEN());
			fp.setNombreEU(pf.getProducto().getNombreEU());
			fp.setNombreFR(pf.getProducto().getNombreFR());
			fp.setNombreGE(pf.getProducto().getNombreGE());
			fp.setNombreIT(pf.getProducto().getNombreIT());
			fp.setNombrePT(pf.getProducto().getNombrePT());
			precioUnitSinIva = new BigDecimal(pf.getPrecioUnitSinIva(), MathContext.DECIMAL64);
			fp.setPrecioUnit(precioUnitSinIva.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
			fp.setDescuentoPor(pf.getPorcentajeDescuento());
			if(pf.getPorcentajeDescuento() != 0.0) {
				BigDecimal descuentoPor = new BigDecimal(pf.getPorcentajeDescuento(), MathContext.DECIMAL64);
				descuentoImp = precioUnitSinIva.multiply(descuentoPor).multiply(comaCeroUno);				
				fp.setDescuentoImp(descuentoImp.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
				precioUnitSinIva = precioUnitSinIva.subtract(descuentoImp);
				fp.setPrecioUnitConDescuento(precioUnitSinIva.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
			} else {
				fp.setDescuentoImp(0.0);
				fp.setPrecioUnitConDescuento(precioUnitSinIva.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
			}
			fp.setIvaProductoPor(pf.getIvaProducto());
			ivaProductoPor = new BigDecimal(pf.getIvaProducto(), MathContext.DECIMAL64);
			ivaProductoImp = precioUnitSinIva.multiply(ivaProductoPor).multiply(comaCeroUno);
			fp.setIvaProductoImp(ivaProductoImp.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
			cantidad = new BigDecimal(pf.getCantidad());
			fp.setPrecioUnitFinal(precioUnitSinIva.multiply(cantidad).divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
			fp.setCantidad(pf.getCantidad());
			precioUnitFinal =precioUnitSinIva.add(ivaProductoImp);
			precioFinal = precioUnitFinal.multiply(cantidad);
			fp.setPrecioFinal(precioFinal.divide(BigDecimal.ONE, 2, RoundingMode.DOWN).doubleValue());
			
			pfFront.add(fp);
		}
		return pfFront;
	}

}
