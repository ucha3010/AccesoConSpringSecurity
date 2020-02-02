package com.damian.pojo.front;

import java.util.List;

public class FrontProductoStock {

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
	private int unidades;

	// ProductoFactura
	private int cantidad;
	private double iva;
	private double precioFinal;

	// Factura
	private boolean compra;
	private String observaciones;
	private double comisionAperturaPor;
	private double interesPor;
	private int cantidadCuotas;
	private List<FrontCuota> cuotas;

	public FrontProductoStock() {

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
	 * @return the unidades
	 */
	public int getUnidades() {
		return unidades;
	}

	/**
	 * @param unidades
	 *            the unidades to set
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
	 * @param cantidad
	 *            the cantidad to set
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
	 * @param iva
	 *            the iva to set
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
	 * @param precioFinal
	 *            the precioFinal to set
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
	 * @param compra
	 *            the compra to set
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
	 * @param observaciones
	 *            the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the comisionAperturaPor
	 */
	public double getComisionAperturaPor() {
		return comisionAperturaPor;
	}

	/**
	 * @param comisionAperturaPor
	 *            the comisionAperturaPor to set
	 */
	public void setComisionAperturaPor(double comisionAperturaPor) {
		this.comisionAperturaPor = comisionAperturaPor;
	}

	/**
	 * @return the interesPor
	 */
	public double getInteresPor() {
		return interesPor;
	}

	/**
	 * @param interesPor
	 *            the interesPor to set
	 */
	public void setInteresPor(double interesPor) {
		this.interesPor = interesPor;
	}

	/**
	 * @return the cantidadCuotas
	 */
	public int getCantidadCuotas() {
		return cantidadCuotas;
	}

	/**
	 * @param cantidadCuotas
	 *            the cantidadCuotas to set
	 */
	public void setCantidadCuotas(int cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}

	/**
	 * @return the cuotas
	 */
	public List<FrontCuota> getCuotas() {
		return cuotas;
	}

	/**
	 * @param cuotas
	 *            the cuotas to set
	 */
	public void setCuotas(List<FrontCuota> cuotas) {
		this.cuotas = cuotas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FrontProductoStock [idPro=" + idPro + ", nombreES=" + nombreES + ", nombreEN=" + nombreEN
				+ ", nombrePT=" + nombrePT + ", nombreFR=" + nombreFR + ", nombreIT=" + nombreIT + ", nombreGE="
				+ nombreGE + ", nombreCA=" + nombreCA + ", nombreEU=" + nombreEU + ", unidades=" + unidades
				+ ", cantidad=" + cantidad + ", iva=" + iva + ", precioFinal=" + precioFinal + ", compra=" + compra
				+ ", observaciones=" + observaciones + ", comisionAperturaPor=" + comisionAperturaPor + ", interesPor="
				+ interesPor + ", cantidadCuotas=" + cantidadCuotas + ", cuotas=" + cuotas + "]";
	}
}
