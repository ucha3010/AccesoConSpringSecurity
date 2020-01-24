package com.damian.dao.model;

public class ModelFormaPago {

	private int idFor;
	private String nombreES;
	private String nombreEN;

	public ModelFormaPago() {

	}

	/**
	 * @param idFor
	 * @param nombreES
	 * @param nombreEN
	 */
	public ModelFormaPago(int idFor, String nombreES, String nombreEN) {
		this.idFor = idFor;
		this.nombreES = nombreES;
		this.nombreEN = nombreEN;
	}

	/**
	 * @return the idFor
	 */
	public int getIdFor() {
		return idFor;
	}

	/**
	 * @param idFor
	 *            the idFor to set
	 */
	public void setIdFor(int idFor) {
		this.idFor = idFor;
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
		return "ModelFormaPago [idFor=" + idFor + ", nombreES=" + nombreES + ", nombreEN=" + nombreEN + "]";
	}
}
