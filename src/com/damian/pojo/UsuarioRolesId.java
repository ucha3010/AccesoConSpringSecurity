package com.damian.pojo;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UsuarioRolesId implements java.io.Serializable {

	/**
	 * Clase UsuarioRolesId
	 */
	private static final long serialVersionUID = 130820142307L;

	@ManyToOne
	private Usuario usuario;

	@ManyToOne
	private Roles roles;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Roles getRoles() {
		return roles;
	}
	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioRolesId other = (UsuarioRolesId) obj;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "UsuarioRolesId [usuario=" + usuario + ", roles=" + roles + "]";
	}
	
}
