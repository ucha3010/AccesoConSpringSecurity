package com.damian.pojo;

import java.sql.Timestamp;

public class Campania {

	private int idCam;
	private String nombre;
	private Timestamp fechaInicio;
	private Timestamp fechaFin;
	private double descuentoPor;
	private String descripcion;

	public Campania() {

	}

	/**
	 * @return the idCam
	 */
	public int getIdCam() {
		return idCam;
	}

	/**
	 * @param idCam
	 *            the idCam to set
	 */
	public void setIdCam(int idCam) {
		this.idCam = idCam;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the fechaInicio
	 */
	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio
	 *            the fechaInicio to set
	 */
	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Timestamp getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin
	 *            the fechaFin to set
	 */
	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the descuentoPor
	 */
	public double getDescuentoPor() {
		return descuentoPor;
	}

	/**
	 * @param descuentoPor
	 *            the descuentoPor to set
	 */
	public void setDescuentoPor(double descuentoPor) {
		this.descuentoPor = descuentoPor;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Campania [idCam=" + idCam + ", nombre=" + nombre + ", fechaInicio=" + fechaInicio + ", fechaFin="
				+ fechaFin + ", descuentoPor=" + descuentoPor + ", descripcion=" + descripcion + "]";
	}

}
