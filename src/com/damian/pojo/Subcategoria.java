package com.damian.pojo;

import java.util.List;

public class Subcategoria {
	
	private int idSub;
	private String nombre;
	private Categoria categoria;
	private List<Producto> productos;
	
	public Subcategoria() {
		
	}

	/**
	 * @param idSub
	 * @param nombre
	 * @param categoria
	 * @param productos
	 */
	public Subcategoria(int idSub, String nombre, Categoria categoria, List<Producto> productos) {
		this.idSub = idSub;
		this.nombre = nombre;
		this.categoria = categoria;
		this.productos = productos;
	}

	/**
	 * @return the idSub
	 */
	public int getIdSub() {
		return idSub;
	}

	/**
	 * @param idSub the idSub to set
	 */
	public void setIdSub(int idSub) {
		this.idSub = idSub;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the productos
	 */
	public List<Producto> getProductos() {
		return productos;
	}

	/**
	 * @param productos the productos to set
	 */
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Subcategoria [idSub=" + idSub + ", nombre=" + nombre + "]";
	}

}