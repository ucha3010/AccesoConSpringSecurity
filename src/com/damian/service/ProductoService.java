package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.AdministracionOfertas;
import com.damian.pojo.Producto;
import com.damian.pojo.front.FrontProductoStock;

public interface ProductoService {

	public List<Producto> findAll(String column, int paginaInicio, int totalPaginas, HttpServletRequest request);

	public Producto findById(int id);
	
	public Producto findByIdConFotos(int id);

	public Producto findByIdModel(int id);

	public int save(Producto producto, HttpServletRequest request);

	public void update(Producto producto, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request) throws NotEmptyException;

	public List<Producto> findByIdList(int id);

	public void saveProductoStock(FrontProductoStock frontProductoStock, HttpServletRequest request);

	public FrontProductoStock fillFrontProductoStock(Producto producto);

	public List<Producto> findSearchAll();
	
	public List<Producto> findAllReducedData();

	public List<Producto> findProductosSinOferta(List<Producto> productos, List<AdministracionOfertas> ofertas,
			List<AdministracionOfertas> campanias);

	public List<Producto> findProductosSinPopulares(List<Producto> productos,
			List<AdministracionOfertas> popularesList);

	public List<Producto> findProductosSinNovedades(List<Producto> productos,
			List<AdministracionOfertas> novedadesList);

	public List<Producto> findProductosSinCampania(List<Producto> productos,
			List<AdministracionOfertas> productosCampaniaList);

	public List<Producto> findByIdSubModel(int idSub);

	public List<Producto> findByMarcaExacta(String nombre);
	
	public int getMaxId();


}
