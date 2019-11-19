package com.damian.dao.model;

import java.io.Serializable;

public class ModelPais implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 130820142307L;

	private int idPais;
	private String nombreES;
	private String nombreEN;

	public ModelPais() {

	}

	/**
	 * @param idPais
	 * @param nombreES
	 * @param nombreEN
	 */
	public ModelPais(int idPais, String nombreES, String nombreEN) {
		this.idPais = idPais;
		this.nombreES = nombreES;
		this.nombreEN = nombreEN;
	}

	/**
	 * @return the idPais
	 */
	public int getIdPais() {
		return idPais;
	}

	/**
	 * @param idPais
	 *            the idPais to set
	 */
	public void setIdPais(int idPais) {
		this.idPais = idPais;
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
		return "Pais [idPais=" + idPais + ", nombreES=" + nombreES + ", nombreEN=" + nombreEN + "]";
	}

}
