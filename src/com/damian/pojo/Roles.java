package com.damian.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Roles implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRoles")
	private int idRoles;

	@Column(name = "rol")
	private String rol;
	
	@ManyToOne()
	@JoinColumn(name = "idUsr")
	private Usuario usuario;
	
	public Roles() {		
	}

	public Roles(int idRoles, String rol, Usuario usuario) {
		this.idRoles = idRoles;
		this.rol = rol;
		this.usuario = usuario;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Roles [idRoles=" + idRoles + ", rol=" + rol + ", usuario=" + usuario.getIdUsr() + "]";
	}

}
