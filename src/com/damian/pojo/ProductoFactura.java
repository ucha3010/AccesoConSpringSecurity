package com.damian.pojo;

public class ProductoFactura {

	private Producto producto;
	private Factura factura;
	private int cantidad;
	private double ivaProducto;
	private double porcentajeDescuento;
	private double precioUnitSinIva;
	private double precioUnitConIva;
	private double precioFinal;
	private String observaciones;

	public ProductoFactura() {

	}

	/**
	 * @param producto
	 * @param factura
	 * @param cantidad
	 * @param ivaProducto
	 * @param porcentajeDescuento
	 * @param precioUnitSinIva
	 * @param precioUnitConIva
	 * @param precioFinal
	 * @param observaciones
	 */
	public ProductoFactura(Producto producto, Factura factura, int cantidad, double ivaProducto,
			double porcentajeDescuento, double precioUnitSinIva, double precioUnitConIva, double precioFinal,
			String observaciones) {
		this.producto = producto;
		this.factura = factura;
		this.cantidad = cantidad;
		this.ivaProducto = ivaProducto;
		this.porcentajeDescuento = porcentajeDescuento;
		this.precioUnitSinIva = precioUnitSinIva;
		this.precioUnitConIva = precioUnitConIva;
		this.precioFinal = precioFinal;
		this.observaciones = observaciones;
	}

	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto
	 *            the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
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
		return "ProductoFactura [cantidad=" + cantidad + ", ivaProducto=" + ivaProducto + ", porcentajeDescuento="
				+ porcentajeDescuento + ", precioUnitSinIva=" + precioUnitSinIva + ", precioUnitConIva="
				+ precioUnitConIva + ", precioFinal=" + precioFinal + ", observaciones=" + observaciones + "]";
	}

}
