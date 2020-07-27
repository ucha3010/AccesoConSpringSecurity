package com.damian.dao.model;

import java.util.Date;

public class ModelProductoEmpresa implements java.io.Serializable {

	/**
	 * Clase ProductoEmpresa
	 */
	private static final long serialVersionUID = 130820142307L;

	private int idPro;
	private int idEmp;
	private Date fechaCreacion;
	private String creadoPor;

	public ModelProductoEmpresa() {

	}

	/**
	 * @param idPro
	 * @param idEmp
	 * @param fechaCreacion
	 * @param creadoPor
	 */
	public ModelProductoEmpresa(int idPro, int idEmp, Date fechaCreacion, String creadoPor) {
		this.idPro = idPro;
		this.idEmp = idEmp;
		this.fechaCreacion = fechaCreacion;
		this.creadoPor = creadoPor;
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
		return "ModelProductoEmpresa [idPro=" + idPro + ", idEmp=" + idEmp + ", fechaCreacion=" + fechaCreacion
				+ ", creadoPor=" + creadoPor + "]";
	}
}
