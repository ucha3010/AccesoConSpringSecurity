package com.damian.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Campania {

	private int idCam;
	private String nombre;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaInicio;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaFin;
	private int descuentoPor;
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
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio
	 *            the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin
	 *            the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the descuentoPor
	 */
	public int getDescuentoPor() {
		return descuentoPor;
	}

	/**
	 * @param descuentoPor
	 *            the descuentoPor to set
	 */
	public void setDescuentoPor(int descuentoPor) {
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
