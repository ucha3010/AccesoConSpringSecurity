package com.damian.pojo.front;

public class FrontProductoStock {

	//Producto
	private int idPro;
	private String descripcion;
	private int unidades;
	
	//ProductoFactura
	private int cantidad;
	private double iva;
	private double precioFinal;
	
	//Factura
	private boolean compra;
	private String observaciones;
	
	public FrontProductoStock() {
		
	}

	/**
	 * @param idPro
	 * @param descripcion
	 * @param unidades
	 * @param cantidad
	 * @param iva
	 * @param precioFinal
	 * @param compra
	 * @param observaciones
	 */
	public FrontProductoStock(int idPro, String descripcion, int unidades, int cantidad, double iva, double precioFinal,
			boolean compra, String observaciones) {
		this.idPro = idPro;
		this.descripcion = descripcion;
		this.unidades = unidades;
		this.cantidad = cantidad;
		this.iva = iva;
		this.precioFinal = precioFinal;
		this.compra = compra;
		this.observaciones = observaciones;
	}

	/**
	 * @return the idPro
	 */
	public int getIdPro() {
		return idPro;
	}

	/**
	 * @param idPro the idPro to set
	 */
	public void setIdPro(int idPro) {
		this.idPro = idPro;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the unidades
	 */
	public int getUnidades() {
		return unidades;
	}

	/**
	 * @param unidades the unidades to set
	 */
	public void setUnidades(int unidades) {
		this.unidades = unidades;
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
	 * @return the iva
	 */
	public double getIva() {
		return iva;
	}

	/**
	 * @param iva the iva to set
	 */
	public void setIva(double iva) {
		this.iva = iva;
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
	 * @return the compra
	 */
	public boolean isCompra() {
		return compra;
	}

	/**
	 * @param compra the compra to set
	 */
	public void setCompra(boolean compra) {
		this.compra = compra;
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
		return "FrontProductoStock [idPro=" + idPro + ", descripcion=" + descripcion + ", unidades=" + unidades
				+ ", cantidad=" + cantidad + ", iva=" + iva + ", precioFinal=" + precioFinal + ", compra=" + compra
				+ ", observaciones=" + observaciones + "]";
	}
}
