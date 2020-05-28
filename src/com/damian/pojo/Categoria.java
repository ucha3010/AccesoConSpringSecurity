package com.damian.pojo;

import java.sql.Timestamp;
import java.util.List;

public class Categoria {

	private int idCat;
	private String nombreES;
	private String nombreEN;
	private String nombrePT;
	private String nombreFR;
	private String nombreIT;
	private String nombreGE;
	private String nombreCA;
	private String nombreEU;
	private List<Subcategoria> subcategorias;
	private Foto foto;
	private String modificadoPor;
	private Timestamp fechaModificacion;

	/**
	 * 
	 */
	public Categoria() {
	}

	/**
	 * @param idCat
	 */
	public Categoria(int idCat) {
		this.idCat = idCat;
	}

	/**
	 * @param idCat
	 * @param nombreES
	 * @param nombreEN
	 */
	public Categoria(int idCat, String nombreES, String nombreEN) {
		this.idCat = idCat;
		this.nombreES = nombreES;
		this.nombreEN = nombreEN;
	}

	/**
	 * @return the idCat
	 */
	public int getIdCat() {
		return idCat;
	}

	/**
	 * @param idCat
	 *            the idCat to set
	 */
	public void setIdCat(int idCat) {
		this.idCat = idCat;
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
	 * @return the subcategorias
	 */
	public List<Subcategoria> getSubcategorias() {
		return subcategorias;
	}

	/**
	 * @param subcategorias
	 *            the subcategorias to set
	 */
	public void setSubcategorias(List<Subcategoria> subcategorias) {
		this.subcategorias = subcategorias;
	}

	/**
	 * @return the foto
	 */
	public Foto getFoto() {
		return foto;
	}

	/**
	 * @param foto
	 *            the foto to set
	 */
	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	/**
	 * @return the modificadoPor
	 */
	public String getModificadoPor() {
		return modificadoPor;
	}

	/**
	 * @param modificadoPor
	 *            the modificadoPor to set
	 */
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	/**
	 * @return the fechaModificacion
	 */
	public Timestamp getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @param fechaModificacion
	 *            the fechaModificacion to set
	 */
	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Categoria [idCat=" + idCat + ", nombreES=" + nombreES + ", nombreEN=" + nombreEN + ", nombrePT="
				+ nombrePT + ", nombreFR=" + nombreFR + ", nombreIT=" + nombreIT + ", nombreGE=" + nombreGE
				+ ", nombreCA=" + nombreCA + ", nombreEU=" + nombreEU + ", subcategorias=" + subcategorias + ", foto="
				+ foto + ", modificadoPor=" + modificadoPor + ", fechaModificacion=" + fechaModificacion + "]";
	}

}
