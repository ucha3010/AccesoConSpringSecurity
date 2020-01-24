package com.damian.dao.model;

public class ModelCategoria {

	private int idCat;
	private String nombreES;
	private String nombreEN;

	/**
	 * 
	 */
	public ModelCategoria() {
	}

	/**
	 * @param idCat
	 * @param nombreES
	 * @param nombreEN
	 */
	public ModelCategoria(int idCat, String nombreES, String nombreEN) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModelCategoria [idCat=" + idCat + ", nombreES=" + nombreES + ", nombreEN=" + nombreEN + "]";
	}

}
