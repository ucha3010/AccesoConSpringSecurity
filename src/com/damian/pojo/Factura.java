package com.damian.pojo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Factura {

	private int idFac;
	private boolean compra;
	private double totalSinIvaEnvDescfac;
	private double descuentoTotal;
	private double descuentoImporteTotal;
	private double importeEnvioSinIva;
	private double totalSinIvaConDescfac;
	private double ivaTotal;
	private double ivaImporteTotal;
	private double importeTotal;
	private Date fechaCompra;
	private Date fechaEntrega;
	private Estado estado;
	private String observaciones;
	private FormaPago formaPago;
	private String creadoPor;
	private Cuota cuota;
	private String modificadoPor;
	private Timestamp fechaModificacion;
	private List<ProductoFactura> productoFacturaList;

	public Factura() {

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
	 * @return the totalSinIvaEnvDescfac
	 */
	public double getTotalSinIvaEnvDescfac() {
		return totalSinIvaEnvDescfac;
	}

	/**
	 * @param totalSinIvaEnvDescfac
	 *            the totalSinIvaEnvDescfac to set
	 */
	public void setTotalSinIvaEnvDescfac(double totalSinIvaEnvDescfac) {
		this.totalSinIvaEnvDescfac = totalSinIvaEnvDescfac;
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
	 * @return the totalSinIvaConDescfac
	 */
	public double getTotalSinIvaConDescfac() {
		return totalSinIvaConDescfac;
	}

	/**
	 * @param totalSinIvaConDescfac
	 *            the totalSinIvaConDescfac to set
	 */
	public void setTotalSinIvaConDescfac(double totalSinIvaConDescfac) {
		this.totalSinIvaConDescfac = totalSinIvaConDescfac;
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
	 * @return the formaPago
	 */
	public FormaPago getFormaPago() {
		return formaPago;
	}

	/**
	 * @param formaPago
	 *            the formaPago to set
	 */
	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
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
	 * @return the cuota
	 */
	public Cuota getCuota() {
		return cuota;
	}

	/**
	 * @param cuota
	 *            the cuota to set
	 */
	public void setCuota(Cuota cuota) {
		this.cuota = cuota;
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

	/**
	 * @return the productoFacturaList
	 */
	public List<ProductoFactura> getProductoFacturaList() {
		return productoFacturaList;
	}

	/**
	 * @param productoFacturaList
	 *            the productoFacturaList to set
	 */
	public void setProductoFacturaList(List<ProductoFactura> productoFacturaList) {
		this.productoFacturaList = productoFacturaList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Factura [idFac=" + idFac + ", compra=" + compra + ", totalSinIvaEnvDescfac=" + totalSinIvaEnvDescfac
				+ ", descuentoTotal=" + descuentoTotal + ", descuentoImporteTotal=" + descuentoImporteTotal
				+ ", importeEnvioSinIva=" + importeEnvioSinIva + ", totalSinIvaConDescfac=" + totalSinIvaConDescfac
				+ ", ivaTotal=" + ivaTotal + ", ivaImporteTotal=" + ivaImporteTotal + ", importeTotal=" + importeTotal
				+ ", fechaCompra=" + fechaCompra + ", fechaEntrega=" + fechaEntrega + ", observaciones=" + observaciones
				+ ", creadoPor=" + creadoPor + ", modificadoPor=" + modificadoPor + ", fechaModificacion="
				+ fechaModificacion + "]";
	}

}
