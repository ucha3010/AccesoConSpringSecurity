package com.damian.pojo;

import java.util.Date;
import java.util.List;

public class Factura {

	private int idFac;
	private boolean compra;
	private double ivaTotal;
	private double descuentoTotal;
	private double importeTotal;
	private Date fechaCompra;
	private Date fechaEntrega;
	private String direccionEntrega;
	private String observaciones;
	private String creadoPor;
	private List<ProductoFactura> productoFacturaList;
	private Estado estado;
	private FormaPago formaPago;

	public Factura() {

	}

	/**
	 * @param idFac
	 * @param compra
	 * @param ivaTotal
	 * @param descuentoTotal
	 * @param importeTotal
	 * @param fechaCompra
	 * @param fechaEntrega
	 * @param direccionEntrega
	 * @param observaciones
	 * @param creadoPor
	 */
	public Factura(int idFac, boolean compra, double ivaTotal, double descuentoTotal, double importeTotal,
			Date fechaCompra, Date fechaEntrega, String direccionEntrega, String observaciones, String creadoPor) {
		this.idFac = idFac;
		this.compra = compra;
		this.ivaTotal = ivaTotal;
		this.descuentoTotal = descuentoTotal;
		this.importeTotal = importeTotal;
		this.fechaCompra = fechaCompra;
		this.fechaEntrega = fechaEntrega;
		this.direccionEntrega = direccionEntrega;
		this.observaciones = observaciones;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Factura [idFac=" + idFac + ", compra=" + compra + ", ivaTotal=" + ivaTotal + ", descuentoTotal="
				+ descuentoTotal + ", importeTotal=" + importeTotal + ", fechaCompra=" + fechaCompra + ", fechaEntrega="
				+ fechaEntrega + ", direccionEntrega=" + direccionEntrega + ", observaciones=" + observaciones
				+ ", creadoPor=" + creadoPor + ", estado=" + estado + ", formaPago=" + formaPago + "]";
	}

}
