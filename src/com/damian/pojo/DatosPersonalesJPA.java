package com.damian.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "datospersonales")
public class DatosPersonalesJPA implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 130820142307L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDatosPers", unique = true, nullable = false)
	private int idDatosPers;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido1")
	private String apellido1;
	
	@Column(name = "apellido2")
	private String apellido2;
	
	@Column(name = "sexo")
	private String sexo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fechaNacimiento")
	private Date fechaNacimiento;
	
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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="datospersonales_idUsr")
	private Usuario usuario;

	@OneToMany(mappedBy = "datosPersonales")
	private List<Direccion> direcciones = new ArrayList<Direccion>();

	public DatosPersonalesJPA() {
	}

	public DatosPersonalesJPA(int idDatosPers, String nombre, String apellido1, String apellido2, String sexo,
			Date fechaNacimiento, String nacionalidad, String dni, String email, String telefono,
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

	public DatosPersonalesJPA(int idDatosPers, String nombre, String apellido1, String apellido2, String sexo,
			Date fechaNacimiento, String nacionalidad, String dni, String email, String telefono,
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
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
