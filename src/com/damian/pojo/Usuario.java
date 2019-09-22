package com.damian.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.damian.valid.PersistenceGroup;
import com.damian.valid.SpringFormGroup;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	/**
	 * Clase Usuario
	 */
	private static final long serialVersionUID = 130820142307L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUsr", unique = true, nullable = false)
	private int idUsr;

	@NotEmpty(message = Constantes.NOT_EMPTY, groups = { PersistenceGroup.class, SpringFormGroup.class })
	@Size(min = 3, max = 50, message = Constantes.NOT_SIZE_USER)
	@Column(name = "usuario")
	private String usuario;

	@NotEmpty(message = Constantes.NOT_EMPTY, groups = { PersistenceGroup.class, SpringFormGroup.class })
//	@Size(min = 8, max = 20, message = Constantes.NOT_SIZE, groups = { SpringFormGroup.class })
	@Column(name = "clave")
	private String clave;

	//para relaciones many to many
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.usuario", cascade = CascadeType.ALL)
	private List<UsuarioRol> usuarioRol = new ArrayList<UsuarioRol>();

	//para relaciones many to many
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.usuario", cascade = CascadeType.ALL)
	private List<UsuarioEmpresa> usuarioEmpresa = new ArrayList<UsuarioEmpresa>();

	//para relaciones one to one
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL)
	private DatosPersonales datosPersonales;

	@Column(name = "habilitado")
	private boolean habilitado;

	@Column(name = "fechaCreacion")
	private Timestamp fechaCreacion;

	public Usuario() {
	}

	public Usuario(int idUsr, String usuario, String clave, boolean habilitado, Timestamp fechaCreacion) {
		this.idUsr = idUsr;
		this.usuario = usuario;
		this.clave = clave;
		this.habilitado = habilitado;
		this.fechaCreacion = fechaCreacion;
	}

	public Usuario(int idUsr, String usuario, String clave, List<UsuarioRol> usuarioRol,
			List<UsuarioEmpresa> usuarioEmpresa, DatosPersonales datosPersonales, boolean habilitado,
			Timestamp fechaCreacion) {
		this.idUsr = idUsr;
		this.usuario = usuario;
		this.clave = clave;
		this.usuarioRol = usuarioRol;
		this.usuarioEmpresa = usuarioEmpresa;
		this.datosPersonales = datosPersonales;
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

	public List<UsuarioRol> getUsuarioRol() {
		return usuarioRol;
	}

	public void setUsuarioRol(List<UsuarioRol> usuarioRol) {
		this.usuarioRol = usuarioRol;
	}

	public List<UsuarioEmpresa> getUsuarioEmpresa() {
		return usuarioEmpresa;
	}

	public void setUsuarioEmpresa(List<UsuarioEmpresa> usuarioEmpresa) {
		this.usuarioEmpresa = usuarioEmpresa;
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

	public DatosPersonales getDatosPersonales() {
		return datosPersonales;
	}

	public void setDatosPersonales(DatosPersonales datosPersonales) {
		this.datosPersonales = datosPersonales;
	}

	@Override
	public String toString() {
		return "Usuario [idUsr=" + idUsr + ", usuario=" + usuario + ", clave=" + clave + ", habilitado=" + habilitado
				+ ", fechaCreacion=" + fechaCreacion + "]";
	}

}
