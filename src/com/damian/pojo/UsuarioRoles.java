package com.damian.pojo;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "usuario_roles")
@AssociationOverrides({
		@AssociationOverride(name = "pk.usuario", joinColumns = @JoinColumn(name = "idUsr")),
		@AssociationOverride(name = "pk.roles", joinColumns = @JoinColumn(name = "idRoles")) })
public class UsuarioRoles implements java.io.Serializable {

	/**
	 * Clase UsuarioRoles
	 */
	private static final long serialVersionUID = 130820142307L;

	@EmbeddedId
	private UsuarioRolesId pk = new UsuarioRolesId();

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaCreacion", nullable = false, length = 10)
	private Date fechaCreacion;

	@Column(name = "creadoPor", nullable = false, length = 50)
	private String creadoPor;

	public UsuarioRolesId getPk() {
		return pk;
	}

	public void setPk(UsuarioRolesId pk) {
		this.pk = pk;
	}
	
	@Transient
	public Usuario getUsuario() {
		return getPk().getUsuario();
	}

	public void setUsuario(Usuario usuario) {
		getPk().setUsuario(usuario);
	}

	@Transient
	public Roles getRoles() {
		return getPk().getRoles();
	}

	public void setRoles(Roles roles) {
		getPk().setRoles(roles);
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		UsuarioRoles other = (UsuarioRoles) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}

	@Override
	public String toString() {
		return "UsuarioRoles [pk=" + pk + ", fechaCreacion=" + fechaCreacion + ", creadoPor=" + creadoPor + "]";
	}

}
