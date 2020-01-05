package com.damian.dao.model;

import java.util.Date;

public class ModelFactura {

	private int idFac;
	private boolean compra;
	private double ivaTotal;
	private double descuentoTotal;
	private double importeTotal;
	private Date fechaCompra;
	private Date fechaEntrega;
	private int idEst;
	private String direccionEntrega;
	private String observaciones;
	private int idFor;
	private String creadoPor;

	public ModelFactura() {

	}

	/**
	 * @param idFac
	 * @param compra
	 * @param ivaTotal
	 * @param descuentoTotal
	 * @param importeTotal
	 * @param fechaCompra
	 * @param fechaEntrega
	 * @param idEst
	 * @param direccionEntrega
	 * @param observaciones
	 * @param idFor
	 * @param creadoPor
	 */
	public ModelFactura(int idFac, boolean compra, double ivaTotal, double descuentoTotal, double importeTotal,
			Date fechaCompra, Date fechaEntrega, int idEst, String direccionEntrega, String observaciones, int idFor,
			String creadoPor) {
		this.idFac = idFac;
		this.compra = compra;
		this.ivaTotal = ivaTotal;
		this.descuentoTotal = descuentoTotal;
		this.importeTotal = importeTotal;
		this.fechaCompra = fechaCompra;
		this.fechaEntrega = fechaEntrega;
		this.idEst = idEst;
		this.direccionEntrega = direccionEntrega;
		this.observaciones = observaciones;
		this.idFor = idFor;
		this.creadoPor = creadoPor;
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
	 * @return the compra
	 */
	public boolean isCompra() {
		return compra;
	}

	/**
	 * @param compra
	 *            the compra to set
	 */
	public void setCompra(boolean compra) {
		this.compra = compra;
	}

	/**
	 * @return the ivaTotal
	 */
	public double getIvaTotal() {
		return ivaTotal;
	}

	/**
	 * @param ivaTotal
	 *            the ivaTotal to set
	 */
	public void setIvaTotal(double ivaTotal) {
		this.ivaTotal = ivaTotal;
	}

	/**
	 * @return the descuentoTotal
	 */
	public double getDescuentoTotal() {
		return descuentoTotal;
	}

	/**
	 * @param descuentoTotal
	 *            the descuentoTotal to set
	 */
	public void setDescuentoTotal(double descuentoTotal) {
		this.descuentoTotal = descuentoTotal;
	}

	/**
	 * @return the importeTotal
	 */
	public double getImporteTotal() {
		return importeTotal;
	}

	/**
	 * @param importeTotal
	 *            the importeTotal to set
	 */
	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}

	/**
	 * @return the fechaCompra
	 */
	public Date getFechaCompra() {
		return fechaCompra;
	}

	/**
	 * @param fechaCompra
	 *            the fechaCompra to set
	 */
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	/**
	 * @return the fechaEntrega
	 */
	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * @param fechaEntrega
	 *            the fechaEntrega to set
	 */
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * @return the idEst
	 */
	public int getIdEst() {
		return idEst;
	}

	/**
	 * @param idEst
	 *            the idEst to set
	 */
	public void setIdEst(int idEst) {
		this.idEst = idEst;
	}

	/**
	 * @return the direccionEntrega
	 */
	public String getDireccionEntrega() {
		return direccionEntrega;
	}

	/**
	 * @param direccionEntrega
	 *            the direccionEntrega to set
	 */
	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
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
	 * @return the idFor
	 */
	public int getIdFor() {
		return idFor;
	}

	/**
	 * @param idFor
	 *            the idFor to set
	 */
	public void setIdFor(int idFor) {
		this.idFor = idFor;
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
		return "ModelFactura [idFac=" + idFac + ", compra=" + compra + ", ivaTotal=" + ivaTotal + ", descuentoTotal="
				+ descuentoTotal + ", importeTotal=" + importeTotal + ", fechaCompra=" + fechaCompra + ", fechaEntrega="
				+ fechaEntrega + ", idEst=" + idEst + ", direccionEntrega=" + direccionEntrega + ", observaciones="
				+ observaciones + ", idFor=" + idFor + ", creadoPor=" + creadoPor + "]";
	}

}
