package com.damian.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.MarcaDAO;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Marca;
import com.damian.pojo.Producto;
import com.damian.service.MarcaService;
import com.damian.service.ProductoService;

@Service
public class MarcaServiceImpl implements MarcaService {

	@Autowired
	private MarcaDAO marcaDAO;

	@Autowired
	private ProductoService productoService;

	public List<Marca> findAll() {
		return marcaDAO.findAll();
	}

	public Marca findById(int id) {
		return marcaDAO.findById(id);
	}

	public void save(Marca marca, HttpServletRequest request) {
		marcaDAO.save(marca, request);
	}

	public void delete(int idMarca, HttpServletRequest request) throws NotEmptyException {
		Marca marca = findById(idMarca);
		List<Producto> productos = productoService.findAllReducedData();
		for(Producto producto: productos) {
			if(marca.getNombre().equalsIgnoreCase(producto.getMarca())) {
				throw new NotEmptyException("Producto con id " + producto.getIdPro() + " tiene asociada la marca");
			}
		}
		marcaDAO.delete(idMarca, request);
	}

	@Override
	public List<Marca> findByMarcaNombre(String nombre) {
		return marcaDAO.findByMarcaNombre(nombre);
	}

	@Override
	public int getMaxId() {
		return marcaDAO.getMaxId();
	}

}
