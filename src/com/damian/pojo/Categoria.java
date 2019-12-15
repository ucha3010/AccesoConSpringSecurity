package com.damian.pojo;

import java.util.List;

public class Categoria {
	
	private int idCat;
	private String nombre;
	private List<Subcategoria> subcategorias;

	/**
	 * 
	 */
	public Categoria() {
	}
	
	/**
	 * @param idCat
	 * @param nombre
	 */
	public Categoria(int idCat, String nombre) {
		this.idCat = idCat;
		this.nombre = nombre;
	}

	/**
	 * @param idCat
	 * @param nombre
	 * @param subcategorias
	 */
	public Categoria(int idCat, String nombre, List<Subcategoria> subcategorias) {
		this.idCat = idCat;
		this.nombre = nombre;
		this.subcategorias = subcategorias;
	}

	/**
	 * @return the idCat
	 */
	public int getIdCat() {
		return idCat;
	}

	/**
	 * @param idCat the idCat to set
	 */
	public void setIdCat(int idCat) {
		this.idCat = idCat;
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
	 * @return the subcategorias
	 */
	public List<Subcategoria> getSubcategorias() {
		return subcategorias;
	}

	/**
	 * @param subcategorias the subcategorias to set
	 */
	public void setSubcategorias(List<Subcategoria> subcategorias) {
		this.subcategorias = subcategorias;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModelCategoria [idCat=" + idCat + ", nombre=" + nombre + "]";
	}

}
