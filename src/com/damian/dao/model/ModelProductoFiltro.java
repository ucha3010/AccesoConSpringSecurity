package com.damian.dao.model;

import java.util.Date;

public class ModelProductoFiltro {

	private int idPro;
	private int idNombre;
	private String creadoPor;
	private Date fechaCreacion;

	public ModelProductoFiltro() {

	}

	/**
	 * @return the idPro
	 */
	public int getIdPro() {
		return idPro;
	}

	/**
	 * @param idPro
	 *            the idPro to set
	 */
	public void setIdPro(int idPro) {
		this.idPro = idPro;
	}

	/**
	 * @return the idNombre
	 */
	public int getIdNombre() {
		return idNombre;
	}

	/**
	 * @param idNombre
	 *            the idNombre to set
	 */
	public void setIdNombre(int idNombre) {
		this.idNombre = idNombre;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModelProductoFiltro [idPro=" + idPro + ", idNombre=" + idNombre + ", creadoPor=" + creadoPor
				+ ", fechaCreacion=" + fechaCreacion + "]";
	}

}
