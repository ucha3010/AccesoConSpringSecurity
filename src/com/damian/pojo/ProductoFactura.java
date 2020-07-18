package com.damian.pojo;

import java.sql.Timestamp;

public class ProductoFactura {

	private Producto producto;
	private Factura factura;
	private int cantidad;
	private double ivaProducto;
	private double porcentajeDescuento;
	private double precioUnitSinIva;
	private String observaciones;
	private String modificadoPor;
	private Timestamp fechaModificacion;

	public ProductoFactura() {

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
	 * @return the factura
	 */
	public Factura getFactura() {
		return factura;
	}

	/**
	 * @param factura
	 *            the factura to set
	 */
	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad
	 *            the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the ivaProducto
	 */
	public double getIvaProducto() {
		return ivaProducto;
	}

	/**
	 * @param ivaProducto
	 *            the ivaProducto to set
	 */
	public void setIvaProducto(double ivaProducto) {
		this.ivaProducto = ivaProducto;
	}

	/**
	 * @return the porcentajeDescuento
	 */
	public double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	/**
	 * @param porcentajeDescuento
	 *            the porcentajeDescuento to set
	 */
	public void setPorcentajeDescuento(double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	/**
	 * @return the precioUnitSinIva
	 */
	public double getPrecioUnitSinIva() {
		return precioUnitSinIva;
	}

	/**
	 * @param precioUnitSinIva
	 *            the precioUnitSinIva to set
	 */
	public void setPrecioUnitSinIva(double precioUnitSinIva) {
		this.precioUnitSinIva = precioUnitSinIva;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones
	 *            the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the modificadoPor
	 */
	public String getModificadoPor() {
		return modificadoPor;
	}

	/**
	 * @param modificadoPor
	 *            the modificadoPor to set
	 */
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	/**
	 * @return the fechaModificacion
	 */
	public Timestamp getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @param fechaModificacion
	 *            the fechaModificacion to set
	 */
	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductoFactura [producto=" + producto + ", factura=" + factura + ", cantidad=" + cantidad
				+ ", ivaProducto=" + ivaProducto + ", porcentajeDescuento=" + porcentajeDescuento
				+ ", precioUnitSinIva=" + precioUnitSinIva + ", observaciones=" + observaciones + ", modificadoPor="
				+ modificadoPor + ", fechaModificacion=" + fechaModificacion + "]";
	}

}
