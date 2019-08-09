package com.damian.pojo;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.damian.valid.PersistenceGroup;
import com.damian.valid.SpringFormGroup;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsr;
	
	@NotEmpty(message=Constantes.NOT_EMPTY, groups= {PersistenceGroup.class, SpringFormGroup.class})
	@Size(min=3, max=50, message=Constantes.NOT_SIZE_USER)
	private String usuario;

	@NotEmpty(message=Constantes.NOT_EMPTY, groups= {PersistenceGroup.class, SpringFormGroup.class})
	@Size(min=8, max=20, message=Constantes.NOT_SIZE, groups= {SpringFormGroup.class})
	private String clave;

	@NotEmpty(message=Constantes.NOT_EMPTY, groups= {PersistenceGroup.class, SpringFormGroup.class})
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	private Set<Roles> roles;
	
	private boolean habilitado;
	
	private Timestamp fechaCreacion;

	public int getIdUsr() {
		return idUsr;
	}

	public void setIdUsr(int idUsr) {
		this.idUsr = idUsr;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Override
	public String toString() {
		return "Usuario [idUsr=" + idUsr + ", usuario=" + usuario + ", clave=" + clave + ", roles=" + roles
				+ ", habilitado=" + habilitado + ", fechaCreacion=" + fechaCreacion + "]";
	}
	
	

}
