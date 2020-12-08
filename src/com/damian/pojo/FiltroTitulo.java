package com.damian.pojo;

import java.util.List;

public class FiltroTitulo {

	private int idTitulo;
	private Subcategoria subcategoria;
	private String nombreES;
	private String nombreEN;
	private String nombrePT;
	private String nombreFR;
	private String nombreIT;
	private String nombreGE;
	private String nombreCA;
	private String nombreEU;
	private List<FiltroNombre> filtroNombres;

	public FiltroTitulo() {

	}

	/**
	 * @return the idTitulo
	 */
	public int getIdTitulo() {
		return idTitulo;
	}

	/**
	 * @param idTitulo
	 *            the idTitulo to set
	 */
	public void setIdTitulo(int idTitulo) {
		this.idTitulo = idTitulo;
	}

	/**
	 * @return the subcategoria
	 */
	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	/**
	 * @param subcategoria
	 *            the subcategoria to set
	 */
	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
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
	 * @return the filtroNombres
	 */
	public List<FiltroNombre> getFiltroNombres() {
		return filtroNombres;
	}

	/**
	 * @param filtroNombres
	 *            the filtroNombres to set
	 */
	public void setFiltroNombres(List<FiltroNombre> filtroNombres) {
		this.filtroNombres = filtroNombres;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FiltroTitulo [idTitulo=" + idTitulo + ", subcategoria=" + subcategoria + ", nombreES=" + nombreES
				+ ", nombreEN=" + nombreEN + ", nombrePT=" + nombrePT + ", nombreFR=" + nombreFR + ", nombreIT="
				+ nombreIT + ", nombreGE=" + nombreGE + ", nombreCA=" + nombreCA + ", nombreEU=" + nombreEU + "]";
	}

}
