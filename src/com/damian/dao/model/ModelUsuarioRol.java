package com.damian.dao.model;

import java.util.Date;

public class ModelUsuarioRol implements java.io.Serializable {

	/**
	 * Clase UsuarioRol
	 */
	private static final long serialVersionUID = 130820142307L;

	private int idUsr;
	private int idRol;
	private Date fechaCreacion;
	private String creadoPor;

	public ModelUsuarioRol() {

	}

	/**
	 * @param idUsr
	 * @param idRol
	 * @param fechaCreacion
	 * @param creadoPor
	 */
	public ModelUsuarioRol(int idUsr, int idRol, Date fechaCreacion, String creadoPor) {
		this.idUsr = idUsr;
		this.idRol = idRol;
		this.fechaCreacion = fechaCreacion;
		this.creadoPor = creadoPor;
	}

	/**
	 * @return the idUsr
	 */
	public int getIdUsr() {
		return idUsr;
	}

	/**
	 * @param idUsr
	 *            the idUsr to set
	 */
	public void setIdUsr(int idUsr) {
		this.idUsr = idUsr;
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
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion
	 *            the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the creadoPor
	 */
	public String getCreadoPor() {
		return creadoPor;
	}

	/**
	 * @param creadoPor
	 *            the creadoPor to set
	 */
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModelUsuarioRol [idUsr=" + idUsr + ", idRol=" + idRol + ", fechaCreacion=" + fechaCreacion
				+ ", creadoPor=" + creadoPor + "]";
	}
}
