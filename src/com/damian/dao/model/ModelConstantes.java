package com.damian.dao.model;

import java.sql.Timestamp;

public class ModelConstantes {

	private String clave;
	private double valorDouble;
	private String valorString100;
	private String valorText;
	private String modificadoPor;
	private Timestamp fechaModificacion;

	/**
	 * 
	 */
	public ModelConstantes() {
	}

	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param clave
	 *            the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * @return the valorDouble
	 */
	public double getValorDouble() {
		return valorDouble;
	}

	/**
	 * @param valorDouble
	 *            the valorDouble to set
	 */
	public void setValorDouble(double valorDouble) {
		this.valorDouble = valorDouble;
	}

	/**
	 * @return the valorString100
	 */
	public String getValorString100() {
		return valorString100;
	}

	/**
	 * @param valorString100
	 *            the valorString100 to set
	 */
	public void setValorString100(String valorString100) {
		this.valorString100 = valorString100;
	}

	/**
	 * @return the valorText
	 */
	public String getValorText() {
		return valorText;
	}

	/**
	 * @param valorText
	 *            the valorText to set
	 */
	public void setValorText(String valorText) {
		this.valorText = valorText;
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
		return "ModelConstantes [clave=" + clave + ", valorDouble=" + valorDouble + ", valorString100=" + valorString100
				+ ", valorText=" + valorText + ", modificadoPor=" + modificadoPor + ", fechaModificacion="
				+ fechaModificacion + "]";
	}

}
