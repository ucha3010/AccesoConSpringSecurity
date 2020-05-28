package com.damian.pojo;

import java.util.Date;

public class UsuarioEmpresa implements java.io.Serializable {

	/**
	 * Clase UsuarioEmpresa
	 */
	private static final long serialVersionUID = 130820142307L;

	private Usuario usuario;
	private Empresa empresa;
	private Date fechaCreacion;
	private String creadoPor;

	public UsuarioEmpresa() {

	}

	/**
	 * @param usuario
	 * @param empresa
	 * @param fechaCreacion
	 * @param creadoPor
	 */
	public UsuarioEmpresa(Usuario usuario, Empresa empresa, Date fechaCreacion, String creadoPor) {
		this.usuario = usuario;
		this.empresa = empresa;
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
	 * @return the empresa
	 */
	public Empresa getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa
	 *            the empresa to set
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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
		return "UsuarioEmpresa [usuario=" + usuario + ", empresa=" + empresa + ", fechaCreacion=" + fechaCreacion
				+ ", creadoPor=" + creadoPor + "]";
	}

}
