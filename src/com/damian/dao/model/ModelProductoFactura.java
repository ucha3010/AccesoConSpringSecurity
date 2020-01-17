package com.damian.dao.model;

public class ModelProductoFactura {

	private int idPro;
	private int idFac;
	private int cantidad;
	private double ivaProducto;
	private double porcentajeDescuento;
	private double precioUnitSinIva;
	private double precioUnitConIva;
	private double precioFinal;
	private String observaciones;

	public ModelProductoFactura() {

	}

	/**
	 * @param idPro
	 * @param idFac
	 * @param cantidad
	 * @param ivaProducto
	 * @param porcentajeDescuento
	 * @param precioUnitSinIva
	 * @param precioUnitConIva
	 * @param precioFinal
	 * @param observaciones
	 */
	public ModelProductoFactura(int idPro, int idFac, int cantidad, double ivaProducto, double porcentajeDescuento,
			double precioUnitSinIva, double precioUnitConIva, double precioFinal, String observaciones) {
		this.idPro = idPro;
		this.idFac = idFac;
		this.cantidad = cantidad;
		this.ivaProducto = ivaProducto;
		this.porcentajeDescuento = porcentajeDescuento;
		this.precioUnitSinIva = precioUnitSinIva;
		this.precioUnitConIva = precioUnitConIva;
		this.precioFinal = precioFinal;
		this.observaciones = observaciones;
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
	 * @return the precioUnitConIva
	 */
	public double getPrecioUnitConIva() {
		return precioUnitConIva;
	}

	/**
	 * @param precioUnitConIva
	 *            the precioUnitConIva to set
	 */
	public void setPrecioUnitConIva(double precioUnitConIva) {
		this.precioUnitConIva = precioUnitConIva;
	}

	/**
	 * @return the precioFinal
	 */
	public double getPrecioFinal() {
		return precioFinal;
	}

	/**
	 * @param precioFinal
	 *            the precioFinal to set
	 */
	public void setPrecioFinal(double precioFinal) {
		this.precioFinal = precioFinal;
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
		return "ModelProductoFactura [idPro=" + idPro + ", idFac=" + idFac + ", cantidad=" + cantidad + ", ivaProducto="
				+ ivaProducto + ", porcentajeDescuento=" + porcentajeDescuento + ", precioUnitSinIva="
				+ precioUnitSinIva + ", precioUnitConIva=" + precioUnitConIva + ", precioFinal=" + precioFinal
				+ ", observaciones=" + observaciones + "]";
	}

}
