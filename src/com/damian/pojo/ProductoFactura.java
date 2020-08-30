package com.damian.pojo;

import java.sql.Timestamp;

public class ProductoFactura {

	private Producto producto;
	private Factura factura;
	private int cantidad;
	private double ivaProducto;
	private double ivaImporteTotal;
	private double porcentajeDescuento;
	private double precioUnitSinIva;
	private double precioUnitSinIvaConDesc;
	private double precioUnitario;
	private double precioFinalSinIva;
	private double precioFinalRecibidoPagado;
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
	 * @return the ivaImporteTotal
	 */
	public double getIvaImporteTotal() {
		return ivaImporteTotal;
	}

	/**
	 * @param ivaImporteTotal
	 *            the ivaImporteTotal to set
	 */
	public void setIvaImporteTotal(double ivaImporteTotal) {
		this.ivaImporteTotal = ivaImporteTotal;
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
	 * @return the precioUnitSinIvaConDesc
	 */
	public double getPrecioUnitSinIvaConDesc() {
		return precioUnitSinIvaConDesc;
	}

	/**
	 * @param precioUnitSinIvaConDesc
	 *            the precioUnitSinIvaConDesc to set
	 */
	public void setPrecioUnitSinIvaConDesc(double precioUnitSinIvaConDesc) {
		this.precioUnitSinIvaConDesc = precioUnitSinIvaConDesc;
	}

	/**
	 * @return the precioUnitario
	 */
	public double getPrecioUnitario() {
		return precioUnitario;
	}

	/**
	 * @param precioUnitario
	 *            the precioUnitario to set
	 */
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	/**
	 * @return the precioFinalSinIva
	 */
	public double getPrecioFinalSinIva() {
		return precioFinalSinIva;
	}

	/**
	 * @param precioFinalSinIva
	 *            the precioFinalSinIva to set
	 */
	public void setPrecioFinalSinIva(double precioFinalSinIva) {
		this.precioFinalSinIva = precioFinalSinIva;
	}

	/**
	 * @return the precioFinalRecibidoPagado
	 */
	public double getPrecioFinalRecibidoPagado() {
		return precioFinalRecibidoPagado;
	}

	/**
	 * @param precioFinalRecibidoPagado
	 *            the precioFinalRecibidoPagado to set
	 */
	public void setPrecioFinalRecibidoPagado(double precioFinalRecibidoPagado) {
		this.precioFinalRecibidoPagado = precioFinalRecibidoPagado;
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
		return "ProductoFactura [cantidad=" + cantidad + ", ivaProducto=" + ivaProducto + ", ivaImporteTotal="
				+ ivaImporteTotal + ", porcentajeDescuento=" + porcentajeDescuento + ", precioUnitSinIva="
				+ precioUnitSinIva + ", precioUnitSinIvaConDesc=" + precioUnitSinIvaConDesc + ", precioUnitario="
				+ precioUnitario + ", precioFinalSinIva=" + precioFinalSinIva + ", precioFinalRecibidoPagado="
				+ precioFinalRecibidoPagado + ", observaciones=" + observaciones + ", modificadoPor=" + modificadoPor
				+ ", fechaModificacion=" + fechaModificacion + "]";
	}

}
