package com.damian.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.CategoriaDAO;
import com.damian.dao.ProductoEmpresaDAO;
import com.damian.dao.SubcategoriaDAO;
import com.damian.pojo.Categoria;
import com.damian.pojo.Empresa;
import com.damian.pojo.Producto;
import com.damian.pojo.ProductoEmpresa;
import com.damian.pojo.Subcategoria;
import com.damian.service.ProductoEmpresaService;

@Service
public class ProductoEmpresaServiceImpl implements ProductoEmpresaService {

	@Autowired
	private CategoriaDAO categoriaDAO;

	@Autowired
	private ProductoEmpresaDAO productoEmpresaDAO;

	@Autowired
	private SubcategoriaDAO subcategoriaDAO;

	@Override
	public List<ProductoEmpresa> findAll() {
		return productoEmpresaDAO.findAll();
	}

	@Override
	public void save(int idPro, int idEmp, HttpServletRequest request) {

		Producto producto = new Producto();
		producto.setIdPro(idPro);
		Empresa empresa = new Empresa();
		empresa.setIdEmp(idEmp);
		ProductoEmpresa productoEmpresa = new ProductoEmpresa();
		productoEmpresa.setProducto(producto);
		productoEmpresa.setEmpresa(empresa);
		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		productoEmpresa.setCreadoPor(context.getAuthentication().getName());
		productoEmpresa.setFechaCreacion(new Date());
		productoEmpresaDAO.save(productoEmpresa, request);
	}

	@Override
	public void update(ProductoEmpresa productoEmpresa, HttpServletRequest request) {
		productoEmpresaDAO.update(productoEmpresa, request);
	}

	@Override
	public void delete(int idPro, int idEmp, HttpServletRequest request) {
		productoEmpresaDAO.delete(idPro, idEmp, request);
	}

	@Override
	public List<ProductoEmpresa> findByIdPro(int idPro) {
		return productoEmpresaDAO.findByIdPro(idPro);
	}

	@Override
	public List<ProductoEmpresa> findByIdEmp(int idEmp) {
		List<ProductoEmpresa> productoEmpresas = productoEmpresaDAO.findByIdEmp(idEmp);
		for (ProductoEmpresa pe : productoEmpresas) {
			Subcategoria s = subcategoriaDAO.findByIdModel(pe.getProducto().getSubcategoria().getIdSub());
			Categoria c = categoriaDAO.findByIdModel(s.getCategoria().getIdCat());
			s.setCategoria(c);
			pe.getProducto().setSubcategoria(s);
		}
		return productoEmpresas;
	}

	@Override
	public List<ProductoEmpresa> findByIdProModel(int idPro) {
		return productoEmpresaDAO.findByIdProModel(idPro);
	}

	@Override
	public List<ProductoEmpresa> findByIdEmpModel(int idEmp) {
		return productoEmpresaDAO.findByIdEmpModel(idEmp);
	}

	@Override
	public ProductoEmpresa findByIdProAndIdEmp(int idPro, int idEmp) {
		return productoEmpresaDAO.findByIdProAndIdEmp(idPro, idEmp);
	}

}
