package com.damian.dao.model;

import java.sql.Timestamp;

public class ModelProductoFactura {

	private int idPro;
	private int idFac;
	private int cantidad;
	private double ivaProducto;
	private double porcentajeDescuento;
	private double precioUnitSinIva;
	private double precioFinalRecibidoPagado;
	private String observaciones;
	private String modificadoPor;
	private Timestamp fechaModificacion;

	public ModelProductoFactura() {

	}

	/**
	 * @return the idPro
	 */
	public int getIdPro() {
		return idPro;
	}

	/**
	 * @param idPro
	 *            the idPro to set
	 */
	public void setIdPro(int idPro) {
		this.idPro = idPro;
	}

	/**
	 * @return the idFac
	 */
	public int getIdFac() {
		return idFac;
	}

	/**
	 * @param idFac
	 *            the idFac to set
	 */
	public void setIdFac(int idFac) {
		this.idFac = idFac;
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
		return "ModelProductoFactura [idPro=" + idPro + ", idFac=" + idFac + ", cantidad=" + cantidad + ", ivaProducto="
				+ ivaProducto + ", porcentajeDescuento=" + porcentajeDescuento + ", precioUnitSinIva="
				+ precioUnitSinIva + ", precioFinalRecibidoPagado=" + precioFinalRecibidoPagado + ", observaciones="
				+ observaciones + ", modificadoPor=" + modificadoPor + ", fechaModificacion=" + fechaModificacion + "]";
	}

}
