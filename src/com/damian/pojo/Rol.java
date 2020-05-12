package com.damian.pojo;

import java.io.Serializable;
import java.util.List;

public class Rol implements Serializable {

	/**
	 * Clase Roles
	 */
	private static final long serialVersionUID = 130820142307L;

	private int idRol;
	private String rol;
	private List<UsuarioRol> usuarioRol;
	Foto foto;

	public Rol() {

	}

	/**
	 * @param idRol
	 * @param rol
	 * @param usuarioRol
	 */
	public Rol(int idRol, String rol, List<UsuarioRol> usuarioRol) {
		this.idRol = idRol;
		this.rol = rol;
		this.usuarioRol = usuarioRol;
	}

	/**
	 * @return the idRol
	 */
	public int getIdRol() {
		return idRol;
	}

	/**
	 * @param idRol
	 *            the idRol to set
	 */
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	/**
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * @param rol
	 *            the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
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
	 * @return the foto
	 */
	public Foto getFoto() {
		return foto;
	}

	/**
	 * @param foto
	 *            the foto to set
	 */
	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Rol [idRol=" + idRol + ", rol=" + rol + ", usuarioRol=" + usuarioRol + ", foto=" + foto + "]";
	}
}
