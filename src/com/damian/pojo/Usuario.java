package com.damian.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {

	/**
	 * Clase Usuario
	 */
	private static final long serialVersionUID = 130820142307L;

	private int idUsr;
	private String usuario;
	private String clave;
	private List<UsuarioRol> usuarioRol = new ArrayList<UsuarioRol>();
	private List<UsuarioEmpresa> usuarioEmpresa = new ArrayList<UsuarioEmpresa>();
	private DatosPersonales datosPersonales;
	private boolean habilitado;
	private Timestamp fechaCreacion;

	public Usuario() {

	}

	/**
	 * @param idUsr
	 * @param usuario
	 * @param clave
	 * @param usuarioRol
	 * @param usuarioEmpresa
	 * @param datosPersonales
	 * @param habilitado
	 * @param fechaCreacion
	 */
	public Usuario(int idUsr, String usuario, String clave, List<UsuarioRol> usuarioRol,
			List<UsuarioEmpresa> usuarioEmpresa, DatosPersonales datosPersonales, boolean habilitado,
			Timestamp fechaCreacion) {
		this.idUsr = idUsr;
		this.usuario = usuario;
		this.clave = clave;
		this.usuarioRol = usuarioRol;
		this.usuarioEmpresa = usuarioEmpresa;
		this.datosPersonales = datosPersonales;
		this.habilitado = habilitado;
		this.fechaCreacion = fechaCreacion;
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
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param clave
	 *            the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * @return the usuarioRol
	 */
	public List<UsuarioRol> getUsuarioRol() {
		return usuarioRol;
	}

	/**
	 * @param usuarioRol
	 *            the usuarioRol to set
	 */
	public void setUsuarioRol(List<UsuarioRol> usuarioRol) {
		this.usuarioRol = usuarioRol;
	}

	/**
	 * @return the usuarioEmpresa
	 */
	public List<UsuarioEmpresa> getUsuarioEmpresa() {
		return usuarioEmpresa;
	}

	/**
	 * @param usuarioEmpresa
	 *            the usuarioEmpresa to set
	 */
	public void setUsuarioEmpresa(List<UsuarioEmpresa> usuarioEmpresa) {
		this.usuarioEmpresa = usuarioEmpresa;
	}

	/**
	 * @return the datosPersonales
	 */
	public DatosPersonales getDatosPersonales() {
		return datosPersonales;
	}

	/**
	 * @param datosPersonales
	 *            the datosPersonales to set
	 */
	public void setDatosPersonales(DatosPersonales datosPersonales) {
		this.datosPersonales = datosPersonales;
	}

	/**
	 * @return the habilitado
	 */
	public boolean isHabilitado() {
		return habilitado;
	}

	/**
	 * @param habilitado
	 *            the habilitado to set
	 */
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion
	 *            the fechaCreacion to set
	 */
	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Usuario [idUsr=" + idUsr + ", usuario=" + usuario + ", clave=" + clave + ", usuarioRol=" + usuarioRol
				+ ", usuarioEmpresa=" + usuarioEmpresa + ", datosPersonales=" + datosPersonales + ", habilitado="
				+ habilitado + ", fechaCreacion=" + fechaCreacion + "]";
	}

}
