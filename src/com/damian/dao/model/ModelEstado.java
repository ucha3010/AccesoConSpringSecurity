package com.damian.dao.model;

public class ModelEstado {

	private int idEst;
	private String nombreES;
	private String nombreEN;

	public ModelEstado() {

	}

	/**
	 * @param idEst
	 * @param nombreES
	 * @param nombreEN
	 */
	public ModelEstado(int idEst, String nombreES, String nombreEN) {
		this.idEst = idEst;
		this.nombreES = nombreES;
		this.nombreEN = nombreEN;
	}

	/**
	 * @return the idEst
	 */
	public int getIdEst() {
		return idEst;
	}

	/**
	 * @param idEst
	 *            the idEst to set
	 */
	public void setIdEst(int idEst) {
		this.idEst = idEst;
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
		return "ModelEstado [idEst=" + idEst + ", nombreES=" + nombreES + ", nombreEN=" + nombreEN + "]";
	}

}
