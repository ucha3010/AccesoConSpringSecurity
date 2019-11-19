package com.damian.dao.model;

import java.util.Date;

public class ModelUsuarioEmpresa implements java.io.Serializable {

	/**
	 * Clase UsuarioEmpresa
	 */
	private static final long serialVersionUID = 130820142307L;

	private int idUsr;
	private int idEmp;
	private Date fechaCreacion;
	private String creadoPor;

	public ModelUsuarioEmpresa() {

	}

	/**
	 * @param idUsr
	 * @param idEmp
	 * @param fechaCreacion
	 * @param creadoPor
	 */
	public ModelUsuarioEmpresa(int idUsr, int idEmp, Date fechaCreacion, String creadoPor) {
		this.idUsr = idUsr;
		this.idEmp = idEmp;
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
	 * @return the idEmp
	 */
	public int getIdEmp() {
		return idEmp;
	}

	/**
	 * @param idEmp
	 *            the idEmp to set
	 */
	public void setIdEmp(int idEmp) {
		this.idEmp = idEmp;
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
		return "ModelUsuarioEmpresa [idUsr=" + idUsr + ", idEmp=" + idEmp + ", fechaCreacion=" + fechaCreacion
				+ ", creadoPor=" + creadoPor + "]";
	}
}
