package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.FacturaDAO;
import com.damian.dao.ProductoDAO;
import com.damian.dao.model.ModelProductoFactura;
import com.damian.pojo.Factura;
import com.damian.pojo.Producto;
import com.damian.pojo.ProductoFactura;

@Component
public class ConverterProductoFactura {

	@Autowired
	private ProductoDAO productoDAO;

	@Autowired
	private FacturaDAO facturaDAO;

	public ProductoFactura convertAll(ModelProductoFactura mpf) {

		ProductoFactura pf = convert(mpf);
		pf.setProducto(productoDAO.findByIdModel(mpf.getIdPro()));
		pf.setFactura(facturaDAO.findByIdModel(mpf.getIdFac()));

		return pf;

	}

	public ProductoFactura convert(ModelProductoFactura mpf) {

		ProductoFactura pf = new ProductoFactura();
		pf.setCantidad(mpf.getCantidad());
		pf.setIvaProducto(mpf.getIvaProducto());
		pf.setIvaImporteTotal(mpf.getIvaImporteTotal());
		pf.setDescuentoPor(mpf.getDescuentoPor());
		pf.setDescuentoImporteTotal(mpf.getDescuentoImporteTotal());
		pf.setPrecioUnitSinIva(mpf.getPrecioUnitSinIva());
		pf.setPrecioUnitSinIvaConDesc(mpf.getPrecioUnitSinIvaConDesc());
		pf.setPrecioUnitario(mpf.getPrecioUnitario());
		pf.setPrecioFinalSinIva(mpf.getPrecioFinalSinIva());
		pf.setPrecioFinalRecibidoPagado(mpf.getPrecioFinalRecibidoPagado());
		pf.setObservaciones(mpf.getObservaciones());
		Producto producto = new Producto();
		producto.setIdPro(mpf.getIdPro());
		pf.setProducto(producto);
		Factura factura = new Factura();
		factura.setIdFac(mpf.getIdFac());
		pf.setFactura(factura);
		pf.setModificadoPor(mpf.getModificadoPor());
		pf.setFechaModificacion(mpf.getFechaModificacion());

		return pf;

	}

	public ModelProductoFactura convert(ProductoFactura pf) {

		ModelProductoFactura mpf = new ModelProductoFactura();
		if (pf.getProducto() != null) {
			mpf.setIdPro(pf.getProducto().getIdPro());
		}
		if (pf.getFactura() != null) {
			mpf.setIdFac(pf.getFactura().getIdFac());
		}
		mpf.setCantidad(pf.getCantidad());
		mpf.setIvaProducto(pf.getIvaProducto());
		mpf.setIvaImporteTotal(pf.getIvaImporteTotal());
		mpf.setDescuentoPor(pf.getDescuentoPor());
		mpf.setDescuentoImporteTotal(pf.getDescuentoImporteTotal());
		mpf.setPrecioUnitSinIva(pf.getPrecioUnitSinIva());
		mpf.setPrecioUnitSinIvaConDesc(pf.getPrecioUnitSinIvaConDesc());
		mpf.setPrecioUnitario(pf.getPrecioUnitario());
		mpf.setPrecioFinalSinIva(pf.getPrecioFinalSinIva());
		mpf.setPrecioFinalRecibidoPagado(pf.getPrecioFinalRecibidoPagado());
		mpf.setObservaciones(pf.getObservaciones());
		mpf.setModificadoPor(pf.getModificadoPor());
		mpf.setFechaModificacion(pf.getFechaModificacion());

		return mpf;

	}

}
