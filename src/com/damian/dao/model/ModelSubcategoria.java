package com.damian.dao.model;

public class ModelSubcategoria {

	private int idSub;
	private String nombreES;
	private String nombreEN;
	private int idCat;

	public ModelSubcategoria() {

	}

	/**
	 * @param idSub
	 * @param nombreES
	 * @param nombreEN
	 * @param idCat
	 */
	public ModelSubcategoria(int idSub, String nombreES, String nombreEN, int idCat) {
		this.idSub = idSub;
		this.nombreES = nombreES;
		this.nombreEN = nombreEN;
		this.idCat = idCat;
	}

	/**
	 * @return the idSub
	 */
	public int getIdSub() {
		return idSub;
	}

	/**
	 * @param idSub
	 *            the idSub to set
	 */
	public void setIdSub(int idSub) {
		this.idSub = idSub;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModelSubcategoria [idSub=" + idSub + ", nombreES=" + nombreES + ", nombreEN=" + nombreEN + ", idCat="
				+ idCat + "]";
	}

}
