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
import com.damian.pojo.front.FrontProductoFactura;
import com.damian.service.ProductoFacturaService;
import com.damian.utils.Utils;

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

		productoFactura.setModificadoPor(Utils.getLoggedUser(request));
		productoFactura.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

		productoFacturaDAO.save(productoFactura, request);
	}

	@Override
	public void update(ProductoFactura productoFactura, HttpServletRequest request) {

		productoFactura.setModificadoPor(Utils.getLoggedUser(request));
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
	public List<FrontProductoFactura> findByIdFacFront(int idFac) {
		List<ProductoFactura> productoFacturas = findByIdFac(idFac);
		List<FrontProductoFactura> pfFront = new ArrayList<>();
		FrontProductoFactura fp;
		BigDecimal comaCeroUno = new BigDecimal(0.01, MathContext.DECIMAL64);
		BigDecimal descuentoUnitarioImp = new BigDecimal(0);
		BigDecimal ivaProductoPor = new BigDecimal(0);
		BigDecimal ivaProductoUnitarioImp = new BigDecimal(0);
		BigDecimal precioUnitSinIva = new BigDecimal(0);
		BigDecimal precioUnitSinIvaConDescuento = new BigDecimal(0);
		for (ProductoFactura pf : productoFacturas) {
			precioUnitSinIva = new BigDecimal(pf.getPrecioUnitSinIva(), MathContext.DECIMAL64);
			precioUnitSinIvaConDescuento = new BigDecimal(pf.getPrecioUnitSinIvaConDesc(), MathContext.DECIMAL64);
			descuentoUnitarioImp = precioUnitSinIvaConDescuento.subtract(precioUnitSinIva);
			ivaProductoPor = new BigDecimal(pf.getIvaProducto(), MathContext.DECIMAL64);
			ivaProductoUnitarioImp = precioUnitSinIva.multiply(ivaProductoPor).multiply(comaCeroUno);

			fp = new FrontProductoFactura();
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
			fp.setPrecioUnit(pf.getPrecioUnitSinIva());
			fp.setDescuentoPor(pf.getDescuentoPor());
			fp.setDescuentoImp(descuentoUnitarioImp.divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP).doubleValue());
			fp.setDescuentoCantidadImp(pf.getDescuentoImporteTotal());
			fp.setPrecioUnitConDescuento(pf.getPrecioUnitSinIvaConDesc());
			fp.setIvaProductoPor(pf.getIvaProducto());
			fp.setIvaProductoImp(ivaProductoUnitarioImp.divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP).doubleValue());
			fp.setIvaProductosCantidadImp(pf.getIvaImporteTotal());
			fp.setPrecioUnitFinal(pf.getPrecioUnitario());
			fp.setCantidad(pf.getCantidad());
			fp.setPrecioFinal(pf.getPrecioFinalRecibidoPagado());

			pfFront.add(fp);
		}
		return pfFront;
	}

}
