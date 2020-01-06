package com.damian.pojo;

import java.util.Date;

public class FacturaEstado {

	private int id;
	private Factura factura;
	private Estado estado;
	private Date fecha;
	private String creadoPor;
	private String observaciones;

	public FacturaEstado() {

	}

	/**
	 * @param id
	 * @param fecha
	 * @param creadoPor
	 * @param observaciones
	 */
	public FacturaEstado(int id, Date fecha, String creadoPor, String observaciones) {
		this.id = id;
		this.fecha = fecha;
		this.creadoPor = creadoPor;
		this.observaciones = observaciones;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FacturaEstado [id=" + id + ", factura=" + factura + ", estado=" + estado + ", fecha=" + fecha
				+ ", creadoPor=" + creadoPor + ", observaciones=" + observaciones + "]";
	}

}
