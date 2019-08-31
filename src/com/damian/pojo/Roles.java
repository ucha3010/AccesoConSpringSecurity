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
@Table(name="roles")
public class Roles implements Serializable {
	
	/**
	 * Clase Roles
	 */
	private static final long serialVersionUID = 130820142307L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRoles", unique = true, nullable = false)
	private int idRoles;

	@Column(name = "rol")
	private String rol;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.roles")
	private List<UsuarioRoles> usuarioRoles = new ArrayList<UsuarioRoles>();
	
	public Roles() {		
	}

	public Roles(int idRoles, String rol) {
		this.idRoles = idRoles;
		this.rol = rol;
	}

	public Roles(int idRoles, String rol, List<UsuarioRoles> usuarioRoles) {
		this.idRoles = idRoles;
		this.rol = rol;
		this.usuarioRoles = usuarioRoles;
	}

	public int getIdRoles() {
		return idRoles;
	}

	public void setIdRoles(int idRoles) {
		this.idRoles = idRoles;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public List<UsuarioRoles> getUsuarioRoles() {
		return usuarioRoles;
	}

	public void setUsuarioRoles(List<UsuarioRoles> usuarioRoles) {
		this.usuarioRoles = usuarioRoles;
	}

	@Override
	public String toString() {
		return "Roles [idRoles=" + idRoles + ", rol=" + rol + "]";
	}

}
