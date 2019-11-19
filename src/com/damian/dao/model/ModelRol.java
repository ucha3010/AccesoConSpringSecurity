package com.damian.dao.model;

import java.io.Serializable;

public class ModelRol implements Serializable {

	/**
	 * Clase Roles
	 */
	private static final long serialVersionUID = 130820142307L;

	private int idRol;
	private String rol;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Rol [idRol=" + idRol + ", rol=" + rol + "]";
	}
}
