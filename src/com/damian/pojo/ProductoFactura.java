package com.damian.pojo;

public class ProductoFactura {
	
	private Producto producto;
	private Factura factura;
	private int cantidad;
	private double ivaProducto;
	private double porcentajeDescuento;
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
	 * @param precioFinal
	 * @param observaciones
	 */
	public ProductoFactura(Producto producto, Factura factura, int cantidad, double ivaProducto,
			double porcentajeDescuento, double precioFinal, String observaciones) {
		this.producto = producto;
		this.factura = factura;
		this.cantidad = cantidad;
		this.ivaProducto = ivaProducto;
		this.porcentajeDescuento = porcentajeDescuento;
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
	 * @param producto the producto to set
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
	 * @param factura the factura to set
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
	 * @param cantidad the cantidad to set
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
	 * @param ivaProducto the ivaProducto to set
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
	 * @param porcentajeDescuento the porcentajeDescuento to set
	 */
	public void setPorcentajeDescuento(double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	/**
	 * @return the precioFinal
	 */
	public double getPrecioFinal() {
		return precioFinal;
	}

	/**
	 * @param precioFinal the precioFinal to set
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
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductoFactura [cantidad=" + cantidad + ", ivaProducto=" + ivaProducto + ", porcentajeDescuento="
				+ porcentajeDescuento + ", precioFinal=" + precioFinal + ", observaciones=" + observaciones + "]";
	}

}
