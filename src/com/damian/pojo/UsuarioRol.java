package com.damian.pojo;

import java.util.Date;

public class UsuarioRol implements java.io.Serializable {

	/**
	 * Clase UsuarioRol
	 */
	private static final long serialVersionUID = 130820142307L;

	private Usuario usuario;
	private Rol rol;
	private Date fechaCreacion;
	private String creadoPor;

	public UsuarioRol() {

	}

	/**
	 * @param usuario
	 * @param rol
	 * @param fechaCreacion
	 * @param creadoPor
	 */
	public UsuarioRol(Usuario usuario, Rol rol, Date fechaCreacion, String creadoPor) {
		this.usuario = usuario;
		this.rol = rol;
		this.fechaCreacion = fechaCreacion;
		this.creadoPor = creadoPor;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the rol
	 */
	public Rol getRol() {
		return rol;
	}

	/**
	 * @param rol
	 *            the rol to set
	 */
	public void setRol(Rol rol) {
		this.rol = rol;
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
		return "UsuarioRol [usuario=" + usuario + ", rol=" + rol + ", fechaCreacion=" + fechaCreacion + ", creadoPor="
				+ creadoPor + "]";
	}

}
