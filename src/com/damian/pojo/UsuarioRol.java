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
@Table(name = "usuario_rol")
@AssociationOverrides({
		@AssociationOverride(name = "pk.usuario", joinColumns = @JoinColumn(name = "idUsr")),
		@AssociationOverride(name = "pk.rol", joinColumns = @JoinColumn(name = "idRol")) })
public class UsuarioRol implements java.io.Serializable {

	/**
	 * Clase UsuarioRol
	 */
	private static final long serialVersionUID = 130820142307L;

	@EmbeddedId
	private UsuarioRolId pk = new UsuarioRolId();

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaCreacion", nullable = false, length = 10)
	private Date fechaCreacion;

	@Column(name = "creadoPor", nullable = false, length = 50)
	private String creadoPor;

	public UsuarioRolId getPk() {
		return pk;
	}

	public void setPk(UsuarioRolId pk) {
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
	public Rol getRol() {
		return getPk().getRol();
	}

	public void setRol(Rol rol) {
		getPk().setRol(rol);
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
		UsuarioRol other = (UsuarioRol) obj;
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
		return "UsuarioRol [pk=" + pk + ", fechaCreacion=" + fechaCreacion + ", creadoPor=" + creadoPor + "]";
	}

}
