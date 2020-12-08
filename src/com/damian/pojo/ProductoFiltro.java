package com.damian.pojo;

import java.sql.Timestamp;

public class ProductoFiltro {

	private Producto producto;
	private FiltroNombre filtroNombre;
	private String creadoPor;
	private Timestamp fechaCreacion;

	public ProductoFiltro() {

	}

	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto
	 *            the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * @return the filtroNombre
	 */
	public FiltroNombre getFiltroNombre() {
		return filtroNombre;
	}

	/**
	 * @param filtroNombre
	 *            the filtroNombre to set
	 */
	public void setFiltroNombre(FiltroNombre filtroNombre) {
		this.filtroNombre = filtroNombre;
	}

	/**
	 * @return the creadoPor
	 */
	public String getCreadoPor() {
		return creadoPor;
	}

	/**
	 * @param creadoPor
	 *            the creadoPor to set
	 */
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion
	 *            the fechaCreacion to set
	 */
	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductoFiltro [producto=" + producto + ", filtroNombre=" + filtroNombre + ", creadoPor=" + creadoPor
				+ ", fechaCreacion=" + fechaCreacion + "]";
	}

}
