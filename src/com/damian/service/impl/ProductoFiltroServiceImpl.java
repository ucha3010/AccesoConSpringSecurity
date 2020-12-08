package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.FiltroTituloDAO;
import com.damian.dao.ProductoFiltroDAO;
import com.damian.pojo.FiltroNombre;
import com.damian.pojo.FiltroTitulo;
import com.damian.pojo.Producto;
import com.damian.pojo.ProductoFiltro;
import com.damian.service.ProductoFiltroService;
import com.damian.utils.Utils;

@Service
public class ProductoFiltroServiceImpl implements ProductoFiltroService {

	@Autowired
	private FiltroTituloDAO filtroTituloDAO;

	@Autowired
	private ProductoFiltroDAO productoFiltroDAO;

	@Override
	public List<ProductoFiltro> findAll() {
		List<ProductoFiltro> productoFiltros = productoFiltroDAO.findAll();
		return rellenoFiltroTituloList(productoFiltros);
	}

	@Override
	public void save(int idPro, int idNombre, HttpServletRequest request) {

		Producto producto = new Producto();
		producto.setIdPro(idPro);
		FiltroNombre filtroNombre = new FiltroNombre();
		filtroNombre.setIdNombre(idNombre);
		ProductoFiltro productoFiltro = new ProductoFiltro();
		productoFiltro.setProducto(producto);
		productoFiltro.setFiltroNombre(filtroNombre);
		productoFiltro.setCreadoPor(Utils.getLoggedUser(request));
		productoFiltro.setFechaCreacion(new Timestamp((new Date()).getTime()));
		productoFiltroDAO.save(productoFiltro, request);
	}

	@Override
	public void update(ProductoFiltro productoFiltro, HttpServletRequest request) {
		productoFiltroDAO.update(productoFiltro, request);
	}

	@Override
	public void delete(int idPro, int idNombre, HttpServletRequest request) {
		productoFiltroDAO.delete(idPro, idNombre, request);
	}

	@Override
	public List<ProductoFiltro> findByIdPro(int idPro) {
		List<ProductoFiltro> productoFiltros = productoFiltroDAO.findByIdPro(idPro);
		return rellenoFiltroTituloList(productoFiltros);
	}

	@Override
	public List<ProductoFiltro> findByIdNombre(int idNombre) {
		List<ProductoFiltro> productoFiltros = productoFiltroDAO.findByIdNombre(idNombre);
		return rellenoFiltroTituloList(productoFiltros);
	}

	@Override
	public List<ProductoFiltro> findByIdProModel(int idPro) {
		return productoFiltroDAO.findByIdProModel(idPro);
	}

	@Override
	public List<ProductoFiltro> findByIdNombreModel(int idNombre) {
		return productoFiltroDAO.findByIdNombreModel(idNombre);
	}

	@Override
	public ProductoFiltro findByIdProAndIdNombre(int idPro, int idNombre) {
		ProductoFiltro pf = productoFiltroDAO.findByIdProAndIdNombre(idPro, idNombre);
		rellenoFiltroTitulo(pf);
		return pf;
	}

	private List<ProductoFiltro> rellenoFiltroTituloList(List<ProductoFiltro> productoFiltros) {
		for (ProductoFiltro pf : productoFiltros) {
			rellenoFiltroTitulo(pf);
		}
		return productoFiltros;
	}

	private void rellenoFiltroTitulo(ProductoFiltro pf) {
		if (pf != null && pf.getFiltroNombre() != null && pf.getFiltroNombre().getFiltroTitulo() != null) {
			FiltroTitulo ft = filtroTituloDAO.findByIdModel(pf.getFiltroNombre().getFiltroTitulo().getIdTitulo());
			pf.getFiltroNombre().setFiltroTitulo(ft);
		}

	}

}
