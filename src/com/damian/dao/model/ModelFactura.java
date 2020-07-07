package com.damian.dao.model;

import java.sql.Timestamp;
import java.util.Date;

public class ModelFactura {

	private int idFac;
	private boolean compra;
	private double ivaTotal;
	private double ivaImporteTotal;
	private double descuentoTotal;
	private double descuentoImporteTotal;
	private double importeEnvioSinIva;
	private double importeTotal;
	private Date fechaCompra;
	private Date fechaEntrega;
	private int idEst;
	private String observaciones;
	private int idFor;
	private String creadoPor;
	private int idCuo;
	private String modificadoPor;
	private Timestamp fechaModificacion;

	public ModelFactura() {

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
	 * @return the descuentoImporteTotal
	 */
	public double getDescuentoImporteTotal() {
		return descuentoImporteTotal;
	}

	/**
	 * @param descuentoImporteTotal
	 *            the descuentoImporteTotal to set
	 */
	public void setDescuentoImporteTotal(double descuentoImporteTotal) {
		this.descuentoImporteTotal = descuentoImporteTotal;
	}

	/**
	 * @return the importeEnvioSinIva
	 */
	public double getImporteEnvioSinIva() {
		return importeEnvioSinIva;
	}

	/**
	 * @param importeEnvioSinIva
	 *            the importeEnvioSinIva to set
	 */
	public void setImporteEnvioSinIva(double importeEnvioSinIva) {
		this.importeEnvioSinIva = importeEnvioSinIva;
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

	/**
	 * @return the idCuo
	 */
	public int getIdCuo() {
		return idCuo;
	}

	/**
	 * @param idCuo
	 *            the idCuo to set
	 */
	public void setIdCuo(int idCuo) {
		this.idCuo = idCuo;
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
		return "ModelFactura [idFac=" + idFac + ", compra=" + compra + ", ivaTotal=" + ivaTotal + ", ivaImporteTotal="
				+ ivaImporteTotal + ", descuentoTotal=" + descuentoTotal + ", descuentoImporteTotal="
				+ descuentoImporteTotal + ", importeEnvioSinIva=" + importeEnvioSinIva + ", importeTotal="
				+ importeTotal + ", fechaCompra=" + fechaCompra + ", fechaEntrega=" + fechaEntrega + ", idEst=" + idEst
				+ ", observaciones=" + observaciones + ", idFor=" + idFor + ", creadoPor=" + creadoPor + ", idCuo="
				+ idCuo + ", modificadoPor=" + modificadoPor + ", fechaModificacion=" + fechaModificacion + "]";
	}

}
