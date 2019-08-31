package com.damian.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
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
public class DatosPersonales implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 130820142307L;

	@Id
	@Column(name = "idDatosPers")
	private int idDatosPers;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido1")
	private String apellido1;
	
	@Column(name = "apellido2")
	private String apellido2;
	
	@Column(name = "sexo")
	private String sexo;
	
	@Column(name = "fechaNacimiento")
	private Timestamp fechaNacimiento;
	
	@Column(name = "nacionalidad")
	private String nacionalidad;	

	@Column(name = "dni")
	private String dni;

	@Column(name = "email")
	private String email;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "observaciones")	
	private String observaciones;

	@OneToOne
	@JoinColumn(name = "FK_idUsr")
	private Usuario usuario;

	@NotEmpty(message = Constantes.NOT_EMPTY, groups = { PersistenceGroup.class, SpringFormGroup.class })
	@OneToMany(mappedBy = "datosPersonales")
	private List<Direccion> direcciones = new ArrayList<Direccion>();

	public DatosPersonales() {
	}

	public DatosPersonales(int idDatosPers, String nombre, String apellido1, String apellido2, String sexo,
			Timestamp fechaNacimiento, String nacionalidad, String dni, String email, String telefono,
			String observaciones) {
		this.idDatosPers = idDatosPers;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
		this.observaciones = observaciones;
	}

	public DatosPersonales(int idDatosPers, String nombre, String apellido1, String apellido2, String sexo,
			Timestamp fechaNacimiento, String nacionalidad, String dni, String email, String telefono,
			String observaciones, Usuario usuario, List<Direccion> direcciones) {
		this.idDatosPers = idDatosPers;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
		this.observaciones = observaciones;
		this.usuario = usuario;
		this.direcciones = direcciones;
	}

	public int getIdDatosPers() {
		return idDatosPers;
	}

	public void setIdDatosPers(int idDatosPers) {
		this.idDatosPers = idDatosPers;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Timestamp getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Timestamp fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Direccion> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(List<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	@Override
	public String toString() {
		return "DatosPersonales [idDatosPers=" + idDatosPers + ", nombre=" + nombre + ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2 + ", sexo=" + sexo + ", fechaNacimiento=" + fechaNacimiento
				+ ", nacionalidad=" + nacionalidad + ", dni=" + dni + ", email=" + email + ", telefono=" + telefono
				+ ", observaciones=" + observaciones + ", direcciones=" + direcciones + "]";
	}

}
