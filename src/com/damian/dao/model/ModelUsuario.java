package com.damian.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ModelUsuario implements Serializable {

	/**
	 * Clase Usuario
	 */
	private static final long serialVersionUID = 130820142307L;

	private int idUsr;
	private String usuario;
	private String clave;
	private boolean habilitado;
	private Timestamp fechaCreacion;

	public ModelUsuario() {

	}

	/**
	 * @param idUsr
	 * @param usuario
	 * @param clave
	 * @param habilitado
	 * @param fechaCreacion
	 */
	public ModelUsuario(int idUsr, String usuario, String clave, boolean habilitado, Timestamp fechaCreacion) {
		this.idUsr = idUsr;
		this.usuario = usuario;
		this.clave = clave;
		this.habilitado = habilitado;
		this.fechaCreacion = fechaCreacion;
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
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	 * @return the habilitado
	 */
	public boolean isHabilitado() {
		return habilitado;
	}

	/**
	 * @param habilitado
	 *            the habilitado to set
	 */
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion
	 *            the fechaCreacion to set
	 */
	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Usuario [idUsr=" + idUsr + ", usuario=" + usuario + ", clave=" + clave + ", habilitado=" + habilitado
				+ ", fechaCreacion=" + fechaCreacion + "]";
	}

}
