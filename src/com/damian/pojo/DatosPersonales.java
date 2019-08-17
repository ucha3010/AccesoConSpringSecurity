package com.damian.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.damian.valid.PersistenceGroup;
import com.damian.valid.SpringFormGroup;

@Entity
@Table(name = "datospersonales")
public class DatosPersonales {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDatosPers")
	private int idDatosPers;

	@Column(name = "dni")
	private String dni;

	@Column(name = "email")
	private String email;

	@OneToOne
	@JoinColumn(name = "FK_idUsr")
	private Usuario usuario;

	@NotEmpty(message = Constantes.NOT_EMPTY, groups = { PersistenceGroup.class, SpringFormGroup.class })
	@OneToMany(mappedBy = "datosPersonales")
	private List<Direccion> direcciones = new ArrayList<Direccion>();

	public DatosPersonales() {
	}

	public DatosPersonales(int idDatosPers, String dni, String email) {
		this.idDatosPers = idDatosPers;
		this.dni = dni;
		this.email = email;
	}

	public DatosPersonales(int idDatosPers, String dni, String email, Usuario usuario, List<Direccion> direcciones) {
		super();
		this.idDatosPers = idDatosPers;
		this.dni = dni;
		this.email = email;
		this.usuario = usuario;
		this.direcciones = direcciones;
	}

	public int getIdDatosPers() {
		return idDatosPers;
	}

	public void setIdDatosPers(int idDatosPers) {
		this.idDatosPers = idDatosPers;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Direccion> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(List<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "DatosPersonales [idDatosPers=" + idDatosPers + ", dni=" + dni + ", email=" + email + ", usuario="
				+ usuario + ", direcciones=" + direcciones + "]";
	}

}
