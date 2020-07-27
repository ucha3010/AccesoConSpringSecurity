package com.damian.dao.model;

import java.sql.Timestamp;

public class ModelRol {

	private int idRol;
	private String rol;
	private String modificadoPor;
	private Timestamp fechaModificacion;

	public ModelRol() {

	}

	/**
	 * @param idRol
	 * @param rol
	 */
	public ModelRol(int idRol, String rol) {
		this.idRol = idRol;
		this.rol = rol;
	}

	/**
	 * @return the idRol
	 */
	public int getIdRol() {
		return idRol;
	}

	/**
	 * @param idRol
	 *            the idRol to set
	 */
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	/**
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * @param rol
	 *            the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
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
		return "ModelRol [idRol=" + idRol + ", rol=" + rol + ", modificadoPor=" + modificadoPor + ", fechaModificacion="
				+ fechaModificacion + "]";
	}
}
