package com.damian.pojo;

import java.sql.Timestamp;

public class AdministracionOfertas {

	private int idPro;
	private boolean booleanOferta;
	private int ordenOferta;
	private double precioSinOferta;
	private double precioConOferta;
	private Timestamp fecha;
	private boolean booleanPopular;
	private int ordenPopular;
	private boolean booleanNovedades;
	private int ordenNovedades;
	private int idCam;
	private Producto producto;

	public AdministracionOfertas() {

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
	 * @return the booleanOferta
	 */
	public boolean isBooleanOferta() {
		return booleanOferta;
	}

	/**
	 * @param booleanOferta
	 *            the booleanOferta to set
	 */
	public void setBooleanOferta(boolean booleanOferta) {
		this.booleanOferta = booleanOferta;
	}

	/**
	 * @return the ordenOferta
	 */
	public int getOrdenOferta() {
		return ordenOferta;
	}

	/**
	 * @param ordenOferta
	 *            the ordenOferta to set
	 */
	public void setOrdenOferta(int ordenOferta) {
		this.ordenOferta = ordenOferta;
	}

	/**
	 * @return the precioSinOferta
	 */
	public double getPrecioSinOferta() {
		return precioSinOferta;
	}

	/**
	 * @param precioSinOferta
	 *            the precioSinOferta to set
	 */
	public void setPrecioSinOferta(double precioSinOferta) {
		this.precioSinOferta = precioSinOferta;
	}

	/**
	 * @return the precioConOferta
	 */
	public double getPrecioConOferta() {
		return precioConOferta;
	}

	/**
	 * @param precioConOferta
	 *            the precioConOferta to set
	 */
	public void setPrecioConOferta(double precioConOferta) {
		this.precioConOferta = precioConOferta;
	}

	/**
	 * @return the fecha
	 */
	public Timestamp getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the booleanPopular
	 */
	public boolean isBooleanPopular() {
		return booleanPopular;
	}

	/**
	 * @param booleanPopular
	 *            the booleanPopular to set
	 */
	public void setBooleanPopular(boolean booleanPopular) {
		this.booleanPopular = booleanPopular;
	}

	/**
	 * @return the ordenPopular
	 */
	public int getOrdenPopular() {
		return ordenPopular;
	}

	/**
	 * @param ordenPopular
	 *            the ordenPopular to set
	 */
	public void setOrdenPopular(int ordenPopular) {
		this.ordenPopular = ordenPopular;
	}

	/**
	 * @return the booleanNovedades
	 */
	public boolean isBooleanNovedades() {
		return booleanNovedades;
	}

	/**
	 * @param booleanNovedades
	 *            the booleanNovedades to set
	 */
	public void setBooleanNovedades(boolean booleanNovedades) {
		this.booleanNovedades = booleanNovedades;
	}

	/**
	 * @return the ordenNovedades
	 */
	public int getOrdenNovedades() {
		return ordenNovedades;
	}

	/**
	 * @param ordenNovedades
	 *            the ordenNovedades to set
	 */
	public void setOrdenNovedades(int ordenNovedades) {
		this.ordenNovedades = ordenNovedades;
	}

	/**
	 * @return the idCam
	 */
	public int getIdCam() {
		return idCam;
	}

	/**
	 * @param idCam
	 *            the idCam to set
	 */
	public void setIdCam(int idCam) {
		this.idCam = idCam;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AdministracionOfertas [idPro=" + idPro + ", booleanOferta=" + booleanOferta + ", ordenOferta="
				+ ordenOferta + ", precioSinOferta=" + precioSinOferta + ", precioConOferta=" + precioConOferta
				+ ", fecha=" + fecha + ", booleanPopular=" + booleanPopular + ", ordenPopular=" + ordenPopular
				+ ", booleanNovedades=" + booleanNovedades + ", ordenNovedades=" + ordenNovedades + ", idCam=" + idCam
				+ "]";
	}

}
