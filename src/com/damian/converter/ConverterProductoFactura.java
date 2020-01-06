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
		pf.setPorcentajeDescuento(mpf.getPorcentajeDescuento());
		pf.setPrecioUnitSinIva(mpf.getPrecioUnitSinIva());
		pf.setPrecioFinal(mpf.getPrecioFinal());
		pf.setObservaciones(mpf.getObservaciones());
		Producto producto = new Producto();
		producto.setIdPro(mpf.getIdPro());
		pf.setProducto(producto);
		Factura factura = new Factura();
		factura.setIdFac(mpf.getIdFac());
		pf.setFactura(factura);

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
		mpf.setPorcentajeDescuento(pf.getPorcentajeDescuento());
		mpf.setPrecioUnitSinIva(pf.getPrecioUnitSinIva());
		mpf.setPrecioFinal(pf.getPrecioFinal());
		mpf.setObservaciones(pf.getObservaciones());

		return mpf;

	}

}
