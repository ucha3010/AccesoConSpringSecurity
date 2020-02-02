package com.damian.dao.model;

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

	public ModelFactura() {

	}

	/**
	 * @param idFac
	 * @param compra
	 * @param ivaTotal
	 * @param ivaImporteTotal
	 * @param descuentoTotal
	 * @param descuentoImporteTotal
	 * @param importeTotal
	 * @param fechaCompra
	 * @param fechaEntrega
	 * @param idEst
	 * @param direccionEntrega
	 * @param observaciones
	 * @param idFor
	 * @param creadoPor
	 * @param idCuo
	 * @param numeroCuota
	 * @param interesCuotaImporte
	 * @param importeCuotaTotal
	 */
	public ModelFactura(int idFac, boolean compra, double ivaTotal, double ivaImporteTotal, double descuentoTotal,
			double descuentoImporteTotal, double importeTotal, Date fechaCompra, Date fechaEntrega, int idEst,
			String direccionEntrega, String observaciones, int idFor, String creadoPor, int idCuo, int numeroCuota,
			double interesCuotaImporte, double importeCuotaTotal) {
		this.idFac = idFac;
		this.compra = compra;
		this.ivaTotal = ivaTotal;
		this.ivaImporteTotal = ivaImporteTotal;
		this.descuentoTotal = descuentoTotal;
		this.descuentoImporteTotal = descuentoImporteTotal;
		this.importeTotal = importeTotal;
		this.fechaCompra = fechaCompra;
		this.fechaEntrega = fechaEntrega;
		this.idEst = idEst;
		this.direccionEntrega = direccionEntrega;
		this.observaciones = observaciones;
		this.idFor = idFor;
		this.creadoPor = creadoPor;
		this.idCuo = idCuo;
		this.numeroCuota = numeroCuota;
		this.interesCuotaImporte = interesCuotaImporte;
		this.importeCuotaTotal = importeCuotaTotal;
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
				+ ", importeCuotaTotal=" + importeCuotaTotal + "]";
	}

}
