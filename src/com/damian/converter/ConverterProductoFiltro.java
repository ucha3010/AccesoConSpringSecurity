package com.damian.converter;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.FiltroNombreDAO;
import com.damian.dao.ProductoDAO;
import com.damian.dao.model.ModelProductoFiltro;
import com.damian.pojo.FiltroNombre;
import com.damian.pojo.Producto;
import com.damian.pojo.ProductoFiltro;

@Component
public class ConverterProductoFiltro {

	@Autowired
	private ProductoDAO productoDAO;

	@Autowired
	private FiltroNombreDAO filtroNombreDAO;

	public ProductoFiltro convertAll(ModelProductoFiltro mpf) {

		ProductoFiltro pf = convert(mpf);
		pf.setProducto(productoDAO.findByIdModel(mpf.getIdPro()));
		pf.setFiltroNombre(filtroNombreDAO.findByIdModel(mpf.getIdNombre()));

		return pf;

	}

	public ProductoFiltro convert(ModelProductoFiltro mpf) {

		ProductoFiltro pf = new ProductoFiltro();
		pf.setFechaCreacion(new Timestamp(mpf.getFechaCreacion().getTime()));
		pf.setCreadoPor(mpf.getCreadoPor());
		Producto producto = new Producto();
		producto.setIdPro(mpf.getIdPro());
		pf.setProducto(producto);
		FiltroNombre filtroNombre = new FiltroNombre();
		filtroNombre.setIdNombre(mpf.getIdNombre());
		pf.setFiltroNombre(filtroNombre);

		return pf;

	}

	public ModelProductoFiltro convert(ProductoFiltro pe) {

		ModelProductoFiltro mpe = new ModelProductoFiltro();
		if (pe.getProducto() != null) {
			mpe.setIdPro(pe.getProducto().getIdPro());
		}
		if (pe.getFiltroNombre() != null) {
			mpe.setIdNombre(pe.getFiltroNombre().getIdNombre());
		}
		mpe.setFechaCreacion(pe.getFechaCreacion());
		mpe.setCreadoPor(pe.getCreadoPor());

		return mpe;

	}

}
