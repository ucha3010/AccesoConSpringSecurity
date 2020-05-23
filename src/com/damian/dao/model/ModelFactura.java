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
	private double importeTotal;
	private Date fechaCompra;
	private Date fechaEntrega;
	private int idEst;
	private String direccionEntrega;
	private String observaciones;
	private int idFor;
	private String creadoPor;
	private int idCuo;
	private int numeroCuota;
	private double interesCuotaImporte;
	private double importeCuotaTotal;
	private double cuotaConIva;
	private double cuotaSinIva;
	private double importeFront;
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
	 * @return the numeroCuota
	 */
	public int getNumeroCuota() {
		return numeroCuota;
	}

	/**
	 * @param numeroCuota
	 *            the numeroCuota to set
	 */
	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	/**
	 * @return the interesCuotaImporte
	 */
	public double getInteresCuotaImporte() {
		return interesCuotaImporte;
	}

	/**
	 * @param interesCuotaImporte
	 *            the interesCuotaImporte to set
	 */
	public void setInteresCuotaImporte(double interesCuotaImporte) {
		this.interesCuotaImporte = interesCuotaImporte;
	}

	/**
	 * @return the importeCuotaTotal
	 */
	public double getImporteCuotaTotal() {
		return importeCuotaTotal;
	}

	/**
	 * @param importeCuotaTotal
	 *            the importeCuotaTotal to set
	 */
	public void setImporteCuotaTotal(double importeCuotaTotal) {
		this.importeCuotaTotal = importeCuotaTotal;
	}

	/**
	 * @return the cuotaConIva
	 */
	public double getCuotaConIva() {
		return cuotaConIva;
	}

	/**
	 * @param cuotaConIva
	 *            the cuotaConIva to set
	 */
	public void setCuotaConIva(double cuotaConIva) {
		this.cuotaConIva = cuotaConIva;
	}

	/**
	 * @return the cuotaSinIva
	 */
	public double getCuotaSinIva() {
		return cuotaSinIva;
	}

	/**
	 * @param cuotaSinIva
	 *            the cuotaSinIva to set
	 */
	public void setCuotaSinIva(double cuotaSinIva) {
		this.cuotaSinIva = cuotaSinIva;
	}

	/**
	 * @return the importeFront
	 */
	public double getImporteFront() {
		return importeFront;
	}

	/**
	 * @param importeFront
	 *            the importeFront to set
	 */
	public void setImporteFront(double importeFront) {
		this.importeFront = importeFront;
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
				+ descuentoImporteTotal + ", importeTotal=" + importeTotal + ", fechaCompra=" + fechaCompra
				+ ", fechaEntrega=" + fechaEntrega + ", idEst=" + idEst + ", direccionEntrega=" + direccionEntrega
				+ ", observaciones=" + observaciones + ", idFor=" + idFor + ", creadoPor=" + creadoPor + ", idCuo="
				+ idCuo + ", numeroCuota=" + numeroCuota + ", interesCuotaImporte=" + interesCuotaImporte
				+ ", importeCuotaTotal=" + importeCuotaTotal + ", cuotaConIva=" + cuotaConIva + ", cuotaSinIva="
				+ cuotaSinIva + "importeFront=" + importeFront + "]";
	}

}
