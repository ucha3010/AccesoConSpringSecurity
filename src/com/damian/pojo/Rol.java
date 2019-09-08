package com.damian.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="rol")
public class Rol implements Serializable {
	
	/**
	 * Clase Roles
	 */
	private static final long serialVersionUID = 130820142307L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRol", unique = true, nullable = false)
	private int idRol;

	@Column(name = "rol")
	private String rol;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.rol")
	private List<UsuarioRol> usuarioRol = new ArrayList<UsuarioRol>();
	
	public Rol() {		
	}

	public Rol(int idRol, String rol) {
		this.idRol = idRol;
		this.rol = rol;
	}

	public Rol(int idRol, String rol, List<UsuarioRol> usuarioRol) {
		this.idRol = idRol;
		this.rol = rol;
		this.usuarioRol = usuarioRol;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public List<UsuarioRol> getUsuarioRol() {
		return usuarioRol;
	}

	public void setUsuarioRol(List<UsuarioRol> usuarioRol) {
		this.usuarioRol = usuarioRol;
	}

	@Override
	public String toString() {
		return "Rol [idRol=" + idRol + ", rol=" + rol + "]";
	}

}
