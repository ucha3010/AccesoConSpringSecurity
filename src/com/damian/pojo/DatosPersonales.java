package com.damian.pojo;

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
public class DatosPersonales {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDatosPers")
	private int idDatosPers;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido")
	private String apellido;
	
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

	@OneToOne
	@JoinColumn(name = "FK_idUsr")
	private Usuario usuario;

	@NotEmpty(message = Constantes.NOT_EMPTY, groups = { PersistenceGroup.class, SpringFormGroup.class })
	@OneToMany(mappedBy = "datosPersonales")
	private List<Direccion> direcciones = new ArrayList<Direccion>();

	public DatosPersonales() {
	}

	public DatosPersonales(int idDatosPers, String nombre, String apellido, String sexo, Timestamp fechaNacimiento,
			String nacionalidad, String dni, String email) {
		super();
		this.idDatosPers = idDatosPers;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.dni = dni;
		this.email = email;
	}

	public DatosPersonales(int idDatosPers, String nombre, String apellido, String sexo, Timestamp fechaNacimiento,
			String nacionalidad, String dni, String email, Usuario usuario, List<Direccion> direcciones) {
		super();
		this.idDatosPers = idDatosPers;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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
		return "DatosPersonales [idDatosPers=" + idDatosPers + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", sexo=" + sexo + ", fechaNacimiento=" + fechaNacimiento + ", nacionalidad=" + nacionalidad
				+ ", dni=" + dni + ", email=" + email + ", direcciones=" + direcciones + "]";
	}

	

}
