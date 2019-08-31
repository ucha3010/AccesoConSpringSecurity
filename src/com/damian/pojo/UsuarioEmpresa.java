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
@Table(name = "usuario_empresa")
@AssociationOverrides({
		@AssociationOverride(name = "pk.usuario", joinColumns = @JoinColumn(name = "idUsr")),
		@AssociationOverride(name = "pk.empresa", joinColumns = @JoinColumn(name = "idEmp")) })
public class UsuarioEmpresa implements java.io.Serializable  {

	/**
	 * Clase UsuarioEmpresa
	 */
	private static final long serialVersionUID = 130820142307L;

	@EmbeddedId
	private UsuarioEmpresaId pk = new UsuarioEmpresaId();

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaCreacion", nullable = false, length = 10)
	private Date fechaCreacion;

	@Column(name = "creadoPor", nullable = false, length = 50)
	private String creadoPor;

	public UsuarioEmpresaId getPk() {
		return pk;
	}

	public void setPk(UsuarioEmpresaId pk) {
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
	public Empresa getEmpresa() {
		return getPk().getEmpresa();
	}
	
	public void setEmpresa(Empresa empresa) {
		getPk().setEmpresa(empresa);
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		UsuarioEmpresa other = (UsuarioEmpresa) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsuarioEmpresa [pk=" + pk + ", fechaCreacion=" + fechaCreacion + ", creadoPor=" + creadoPor + "]";
	}

}
