package com.damian.pojo;

import java.io.Serializable;
import java.util.Date;

public class ProductoEmpresa implements Serializable {

	/**
	 * Clase UsuarioEmpresa
	 */
	private static final long serialVersionUID = 130820142307L;

	private Producto producto;
	private Empresa empresa;
	private Date fechaCreacion;
	private String creadoPor;

	public ProductoEmpresa() {
	}

	/**
	 * @param producto
	 * @param empresa
	 * @param fechaCreacion
	 * @param creadoPor
	 */
	public ProductoEmpresa(Producto producto, Empresa empresa, Date fechaCreacion, String creadoPor) {
		this.producto = producto;
		this.empresa = empresa;
		this.fechaCreacion = fechaCreacion;
		this.creadoPor = creadoPor;
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
	 * @return the empresa
	 */
	public Empresa getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa
	 *            the empresa to set
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion
	 *            the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductoEmpresa [producto=" + producto + ", empresa=" + empresa + ", fechaCreacion=" + fechaCreacion
				+ ", creadoPor=" + creadoPor + "]";
	}

}
