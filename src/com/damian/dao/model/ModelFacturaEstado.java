package com.damian.dao.model;

import java.util.Date;

public class ModelFacturaEstado {

	private int id;
	private int idFac;
	private int idEst;
	private Date fecha;
	private String creadoPor;
	private String observaciones;

	public ModelFacturaEstado() {

	}

	/**
	 * @param id
	 * @param idFac
	 * @param idEst
	 * @param fecha
	 * @param creadoPor
	 * @param observaciones
	 */
	public ModelFacturaEstado(int id, int idFac, int idEst, Date fecha, String creadoPor, String observaciones) {
		this.id = id;
		this.idFac = idFac;
		this.idEst = idEst;
		this.fecha = fecha;
		this.creadoPor = creadoPor;
		this.observaciones = observaciones;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the idFac
	 */
	public int getIdFac() {
		return idFac;
	}

	/**
	 * @param idFac
	 *            the idFac to set
	 */
	public void setIdFac(int idFac) {
		this.idFac = idFac;
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
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones
	 *            the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModelFacturaEstado [id=" + id + ", idFac=" + idFac + ", idEst=" + idEst + ", fecha=" + fecha
				+ ", creadoPor=" + creadoPor + ", observaciones=" + observaciones + "]";
	}

}
