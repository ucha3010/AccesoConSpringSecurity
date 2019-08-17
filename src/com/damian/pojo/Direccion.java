package com.damian.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "direccion")
public class Direccion implements Serializable {

	/**
	 * Clase Direccion
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDir")
	private int idDir;

	@Column(name = "calle")
	private String calle;

	@Column(name = "cp")
	private String cp;

	@ManyToOne
	@JoinColumn(name = "idDatosPers")
	private DatosPersonales datosPersonales;

	public Direccion() {

	}

	public Direccion(String calle, String cp) {
		this.calle = calle;
		this.cp = cp;
	}

	public Direccion(int idDir, String calle, String cp, DatosPersonales datosPersonales) {
		this.idDir = idDir;
		this.calle = calle;
		this.cp = cp;
		this.datosPersonales = datosPersonales;
	}

	public int getIdDir() {
		return idDir;
	}

	public void setIdDir(int idDir) {
		this.idDir = idDir;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public DatosPersonales getDatosPersonales() {
		return datosPersonales;
	}

	public void setDatosPersonales(DatosPersonales datosPersonales) {
		this.datosPersonales = datosPersonales;
	}

	@Override
	public String toString() {
		return "Direccion [idDir=" + idDir + ", calle=" + calle + ", cp=" + cp + "]";
	}

}
