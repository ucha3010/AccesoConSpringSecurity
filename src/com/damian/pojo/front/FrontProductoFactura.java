package com.damian.pojo.front;

public class FrontProductoFactura {

	// Producto
	private int idPro;
	private String nombreES;
	private String nombreEN;
	private String nombrePT;
	private String nombreFR;
	private String nombreIT;
	private String nombreGE;
	private String nombreCA;
	private String nombreEU;

	// ProductoFactura
	private int cantidad;
	private double ivaProductoPor;
	private double ivaProductoImp;
	private double ivaProductosCantidadImp;
	private double descuentoPor;
	private double descuentoImp;
	private double descuentoCantidadImp;
	private double precioUnit;
	private double precioUnitConDescuento;
	private double precioUnitFinal;
	private double precioFinal;
	private String observaciones;

	public FrontProductoFactura() {

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
	 * @return the nombreES
	 */
	public String getNombreES() {
		return nombreES;
	}

	/**
	 * @param nombreES
	 *            the nombreES to set
	 */
	public void setNombreES(String nombreES) {
		this.nombreES = nombreES;
	}

	/**
	 * @return the nombreEN
	 */
	public String getNombreEN() {
		return nombreEN;
	}

	/**
	 * @param nombreEN
	 *            the nombreEN to set
	 */
	public void setNombreEN(String nombreEN) {
		this.nombreEN = nombreEN;
	}

	/**
	 * @return the nombrePT
	 */
	public String getNombrePT() {
		return nombrePT;
	}

	/**
	 * @param nombrePT
	 *            the nombrePT to set
	 */
	public void setNombrePT(String nombrePT) {
		this.nombrePT = nombrePT;
	}

	/**
	 * @return the nombreFR
	 */
	public String getNombreFR() {
		return nombreFR;
	}

	/**
	 * @param nombreFR
	 *            the nombreFR to set
	 */
	public void setNombreFR(String nombreFR) {
		this.nombreFR = nombreFR;
	}

	/**
	 * @return the nombreIT
	 */
	public String getNombreIT() {
		return nombreIT;
	}

	/**
	 * @param nombreIT
	 *            the nombreIT to set
	 */
	public void setNombreIT(String nombreIT) {
		this.nombreIT = nombreIT;
	}

	/**
	 * @return the nombreGE
	 */
	public String getNombreGE() {
		return nombreGE;
	}

	/**
	 * @param nombreGE
	 *            the nombreGE to set
	 */
	public void setNombreGE(String nombreGE) {
		this.nombreGE = nombreGE;
	}

	/**
	 * @return the nombreCA
	 */
	public String getNombreCA() {
		return nombreCA;
	}

	/**
	 * @param nombreCA
	 *            the nombreCA to set
	 */
	public void setNombreCA(String nombreCA) {
		this.nombreCA = nombreCA;
	}

	/**
	 * @return the nombreEU
	 */
	public String getNombreEU() {
		return nombreEU;
	}

	/**
	 * @param nombreEU
	 *            the nombreEU to set
	 */
	public void setNombreEU(String nombreEU) {
		this.nombreEU = nombreEU;
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
	 * @return the ivaProductoPor
	 */
	public double getIvaProductoPor() {
		return ivaProductoPor;
	}

	/**
	 * @param ivaProductoPor
	 *            the ivaProductoPor to set
	 */
	public void setIvaProductoPor(double ivaProductoPor) {
		this.ivaProductoPor = ivaProductoPor;
	}

	/**
	 * @return the ivaProductoImp
	 */
	public double getIvaProductoImp() {
		return ivaProductoImp;
	}

	/**
	 * @param ivaProductoImp
	 *            the ivaProductoImp to set
	 */
	public void setIvaProductoImp(double ivaProductoImp) {
		this.ivaProductoImp = ivaProductoImp;
	}

	/**
	 * @return the ivaProductosCantidadImp
	 */
	public double getIvaProductosCantidadImp() {
		return ivaProductosCantidadImp;
	}

	/**
	 * @param ivaProductosCantidadImp
	 *            the ivaProductosCantidadImp to set
	 */
	public void setIvaProductosCantidadImp(double ivaProductosCantidadImp) {
		this.ivaProductosCantidadImp = ivaProductosCantidadImp;
	}

	/**
	 * @return the descuentoPor
	 */
	public double getDescuentoPor() {
		return descuentoPor;
	}

	/**
	 * @param descuentoPor
	 *            the descuentoPor to set
	 */
	public void setDescuentoPor(double descuentoPor) {
		this.descuentoPor = descuentoPor;
	}

	/**
	 * @return the descuentoImp
	 */
	public double getDescuentoImp() {
		return descuentoImp;
	}

	/**
	 * @param descuentoImp
	 *            the descuentoImp to set
	 */
	public void setDescuentoImp(double descuentoImp) {
		this.descuentoImp = descuentoImp;
	}

	/**
	 * @return the descuentoCantidadImp
	 */
	public double getDescuentoCantidadImp() {
		return descuentoCantidadImp;
	}

	/**
	 * @param descuentoCantidadImp the descuentoCantidadImp to set
	 */
	public void setDescuentoCantidadImp(double descuentoCantidadImp) {
		this.descuentoCantidadImp = descuentoCantidadImp;
	}

	/**
	 * @return the precioUnit
	 */
	public double getPrecioUnit() {
		return precioUnit;
	}

	/**
	 * @param precioUnit
	 *            the precioUnit to set
	 */
	public void setPrecioUnit(double precioUnit) {
		this.precioUnit = precioUnit;
	}

	/**
	 * @return the precioUnitConDescuento
	 */
	public double getPrecioUnitConDescuento() {
		return precioUnitConDescuento;
	}

	/**
	 * @param precioUnitConDescuento
	 *            the precioUnitConDescuento to set
	 */
	public void setPrecioUnitConDescuento(double precioUnitConDescuento) {
		this.precioUnitConDescuento = precioUnitConDescuento;
	}

	/**
	 * @return the precioUnitFinal
	 */
	public double getPrecioUnitFinal() {
		return precioUnitFinal;
	}

	/**
	 * @param precioUnitFinal
	 *            the precioUnitFinal to set
	 */
	public void setPrecioUnitFinal(double precioUnitFinal) {
		this.precioUnitFinal = precioUnitFinal;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FrontProductoFactura [idPro=" + idPro + ", nombreES=" + nombreES + ", nombreEN=" + nombreEN
				+ ", nombrePT=" + nombrePT + ", nombreFR=" + nombreFR + ", nombreIT=" + nombreIT + ", nombreGE="
				+ nombreGE + ", nombreCA=" + nombreCA + ", nombreEU=" + nombreEU + ", cantidad=" + cantidad
				+ ", ivaProductoPor=" + ivaProductoPor + ", ivaProductoImp=" + ivaProductoImp
				+ ", ivaProductosCantidadImp=" + ivaProductosCantidadImp + ", descuentoPor=" + descuentoPor
				+ ", descuentoImp=" + descuentoImp + ", descuentoCantidadImp=" + descuentoCantidadImp + ", precioUnit="
				+ precioUnit + ", precioUnitConDescuento=" + precioUnitConDescuento + ", precioUnitFinal="
				+ precioUnitFinal + ", precioFinal=" + precioFinal + ", observaciones=" + observaciones + "]";
	}

}
