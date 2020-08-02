package com.damian.pojo.front;

public class ImpresionProducto {

	private int idPro;
	private String producto_nombre;
	private double porcentajeDescuento;
	private double precioUnitSinIva;
	private double ivaProducto;
	private int cantidad;
	private double precioFinalRecibidoPagado;
	private String producto_observaciones;

	public ImpresionProducto() {
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
	 * @return the producto_nombre
	 */
	public String getProducto_nombre() {
		return producto_nombre;
	}

	/**
	 * @param producto_nombre
	 *            the producto_nombre to set
	 */
	public void setProducto_nombre(String producto_nombre) {
		this.producto_nombre = producto_nombre;
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
	 * @return the producto_observaciones
	 */
	public String getProducto_observaciones() {
		return producto_observaciones;
	}

	/**
	 * @param producto_observaciones
	 *            the producto_observaciones to set
	 */
	public void setProducto_observaciones(String producto_observaciones) {
		this.producto_observaciones = producto_observaciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ImpresionProducto [idPro=" + idPro + ", producto_nombre=" + producto_nombre + ", porcentajeDescuento="
				+ porcentajeDescuento + ", precioUnitSinIva=" + precioUnitSinIva + ", ivaProducto=" + ivaProducto
				+ ", cantidad=" + cantidad + ", precioFinalRecibidoPagado=" + precioFinalRecibidoPagado
				+ ", producto_observaciones=" + producto_observaciones + "]";
	}

}
