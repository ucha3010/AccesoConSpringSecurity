package com.damian.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Usuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUsr")
	private int idUsr;
	
	@NotEmpty(message=Constantes.NOT_EMPTY, groups= {PersistenceGroup.class, SpringFormGroup.class})
	@Size(min=3, max=50, message=Constantes.NOT_SIZE_USER)
	@Column(name = "usuario")
	private String usuario;

	@NotEmpty(message=Constantes.NOT_EMPTY, groups= {PersistenceGroup.class, SpringFormGroup.class})
	@Size(min=8, max=20, message=Constantes.NOT_SIZE, groups= {SpringFormGroup.class})
	@Column(name = "clave")
	private String clave;

	@NotEmpty(message=Constantes.NOT_EMPTY, groups= {PersistenceGroup.class, SpringFormGroup.class})
	@OneToMany(mappedBy = "usuario")
	private List<Roles> roles = new ArrayList<Roles>();

	@Column(name = "habilitado")
	private boolean habilitado;

	@Column(name = "fechaCreacion")
	private Timestamp fechaCreacion;
	
	public Usuario() {
		
	}

	public Usuario(int idUsr, String usuario, String clave, List<Roles> roles, boolean habilitado,
			Timestamp fechaCreacion) {
		this.idUsr = idUsr;
		this.usuario = usuario;
		this.clave = clave;
		this.roles = roles;
		this.habilitado = habilitado;
		this.fechaCreacion = fechaCreacion;
	}



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

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
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
