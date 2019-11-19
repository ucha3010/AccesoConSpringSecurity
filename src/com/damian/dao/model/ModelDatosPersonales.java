package com.damian.dao.model;

import java.io.Serializable;
import java.util.Date;

public class ModelDatosPersonales implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 130820142307L;

	private int idDatosPers;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String sexo;
	private Date fechaNacimiento;
	private String nacionalidad;
	private String dni;
	private String email;
	private String telefono;
	private String observaciones;
	private int datospersonales_idUsr;

	public ModelDatosPersonales() {

	}

	/**
	 * @param idDatosPers
	 * @param nombre
	 * @param apellido1
	 * @param apellido2
	 * @param sexo
	 * @param fechaNacimiento
	 * @param nacionalidad
	 * @param dni
	 * @param email
	 * @param telefono
	 * @param observaciones
	 * @param datospersonales_idUsr
	 */
	public ModelDatosPersonales(int idDatosPers, String nombre, String apellido1, String apellido2, String sexo,
			Date fechaNacimiento, String nacionalidad, String dni, String email, String telefono, String observaciones,
			int datospersonales_idUsr) {
		this.idDatosPers = idDatosPers;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
		this.observaciones = observaciones;
		this.datospersonales_idUsr = datospersonales_idUsr;
	}

	/**
	 * @return the idDatosPers
	 */
	public int getIdDatosPers() {
		return idDatosPers;
	}

	/**
	 * @param idDatosPers
	 *            the idDatosPers to set
	 */
	public void setIdDatosPers(int idDatosPers) {
		this.idDatosPers = idDatosPers;
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
	 * @return the apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}

	/**
	 * @param apellido1
	 *            the apellido1 to set
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	/**
	 * @return the apellido2
	 */
	public String getApellido2() {
		return apellido2;
	}

	/**
	 * @param apellido2
	 *            the apellido2 to set
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @param sexo
	 *            the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento
	 *            the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the nacionalidad
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}

	/**
	 * @param nacionalidad
	 *            the nacionalidad to set
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni
	 *            the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	/**
	 * @return the datospersonales_idUsr
	 */
	public int getDatospersonales_idUsr() {
		return datospersonales_idUsr;
	}

	/**
	 * @param datospersonales_idUsr
	 *            the datospersonales_idUsr to set
	 */
	public void setDatospersonales_idUsr(int datospersonales_idUsr) {
		this.datospersonales_idUsr = datospersonales_idUsr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DatosPersonales [idDatosPers=" + idDatosPers + ", nombre=" + nombre + ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2 + ", sexo=" + sexo + ", fechaNacimiento=" + fechaNacimiento
				+ ", nacionalidad=" + nacionalidad + ", dni=" + dni + ", email=" + email + ", telefono=" + telefono
				+ ", observaciones=" + observaciones + ", datospersonales_idUsr=" + datospersonales_idUsr + "]";
	}

}
