package com.damian.pojo;

import java.util.List;

public class Subcategoria {

	private int idSub;
	private String nombreES;
	private String nombreEN;
	private Categoria categoria;
	private List<Producto> productos;

	public Subcategoria() {

	}

	/**
	 * @param idSub
	 * @param nombreES
	 * @param nombreEN
	 */
	public Subcategoria(int idSub, String nombreES, String nombreEN) {
		this.idSub = idSub;
		this.nombreES = nombreES;
		this.nombreEN = nombreEN;
	}

	/**
	 * @param idSub
	 * @param nombreES
	 * @param nombreEN
	 * @param categoria
	 * @param productos
	 */
	public Subcategoria(int idSub, String nombreES, String nombreEN, Categoria categoria, List<Producto> productos) {
		this.idSub = idSub;
		this.nombreES = nombreES;
		this.nombreEN = nombreEN;
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
	 * @param idSub
	 *            the idSub to set
	 */
	public void setIdSub(int idSub) {
		this.idSub = idSub;
	}

	/**
	 * @return the nombreES
	 */
	public String getNombreES() {
		return nombreES;
	}

	/**
	 * @param nombreES
	 *            the nombreES to set
	 */
	public void setNombreES(String nombreES) {
		this.nombreES = nombreES;
	}

	/**
	 * @return the nombreEN
	 */
	public String getNombreEN() {
		return nombreEN;
	}

	/**
	 * @param nombreEN
	 *            the nombreEN to set
	 */
	public void setNombreEN(String nombreEN) {
		this.nombreEN = nombreEN;
	}

	/**
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria
	 *            the categoria to set
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
	 * @param productos
	 *            the productos to set
	 */
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Subcategoria [idSub=" + idSub + ", nombreES=" + nombreES + ", nombreEN=" + nombreEN + "]";
	}

}
