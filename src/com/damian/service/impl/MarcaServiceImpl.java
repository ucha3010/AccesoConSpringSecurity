package com.damian.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.MarcaDAO;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Foto;
import com.damian.pojo.Marca;
import com.damian.pojo.Producto;
import com.damian.service.FotoService;
import com.damian.service.MarcaService;
import com.damian.service.ProductoService;

@Service
public class MarcaServiceImpl implements MarcaService {

	@Autowired
	private MarcaDAO marcaDAO;

	@Autowired
	private FotoService fotoService;

	@Autowired
	private ProductoService productoService;

	public List<Marca> findAll() {
		List<Marca> marcas = marcaDAO.findAll();
		agregarFotos(marcas);
		return marcas;
	}

	public Marca findById(int id) {
		return marcaDAO.findById(id);
	}

	public void save(Marca marca, HttpServletRequest request) {
		Marca marcaOriginal = null;
		if(marca.getIdMar() > 0) {
			marcaOriginal = findById(marca.getIdMar());
		}
		marcaDAO.save(marca, request);
		if(marca.getIdMar() > 0) {
			List<Producto> productos = productoService.findByMarcaExacta(marcaOriginal.getNombre());
			for(Producto producto: productos) {
				producto.setMarca(marca.getNombre());
				productoService.update(producto, request);
			}
		}
	}

	public void delete(int idMarca, HttpServletRequest request) throws NotEmptyException {
		Marca marca = findById(idMarca);
		List<Producto> productos = productoService.findAllReducedData();
		for (Producto producto : productos) {
			if (marca.getNombre().equalsIgnoreCase(producto.getMarca())) {
				throw new NotEmptyException("Producto con id " + producto.getIdPro() + " tiene asociada la marca");
			}
		}
		List<Foto> fotos = fotoService.findByIdMar(marca.getIdMar());
		if (fotos != null && !fotos.isEmpty()) {
			fotoService.delete(fotos.get(0).getIdFot(), request);
		}
		marcaDAO.delete(idMarca, request);
	}

	@Override
	public List<Marca> findByMarcaNombre(String nombre) {
		List<Marca> marcas = marcaDAO.findByMarcaNombre(nombre);
		agregarFotos(marcas);
		return marcas;
	}

	@Override
	public int getMaxId() {
		return marcaDAO.getMaxId();
	}

	private void agregarFotos(List<Marca> marcas) {
		List<Foto> fotos = null;
		for (Marca marca : marcas) {
			fotos = fotoService.findByIdMar(marca.getIdMar());
			if (fotos != null && !fotos.isEmpty()) {
				marca.setFoto(fotos.get(0));
			}
		}
	}

}
