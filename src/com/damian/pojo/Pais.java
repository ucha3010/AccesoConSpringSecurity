package com.damian.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "paises")
public class Pais implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 130820142307L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPais", unique = true, nullable = false)
	private int idPais;

	@Column(name = "nombreES")
	private String nombreES;
	


	@Column(name = "nombreEN")
	private String nombreEN;

	public Pais() {
	}


	public Pais(int idPais, String nombreES, String nombreEN) {
		this.idPais = idPais;
		this.nombreES = nombreES;
		this.nombreEN = nombreEN;
	}


	public int getIdPais() {
		return idPais;
	}


	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}


	public String getNombreES() {
		return nombreES;
	}


	public void setNombreES(String nombreES) {
		this.nombreES = nombreES;
	}


	public String getNombreEN() {
		return nombreEN;
	}


	public void setNombreEN(String nombreEN) {
		this.nombreEN = nombreEN;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPais;
		result = prime * result + ((nombreEN == null) ? 0 : nombreEN.hashCode());
		result = prime * result + ((nombreES == null) ? 0 : nombreES.hashCode());
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
		Pais other = (Pais) obj;
		if (idPais != other.idPais)
			return false;
		if (nombreEN == null) {
			if (other.nombreEN != null)
				return false;
		} else if (!nombreEN.equals(other.nombreEN))
			return false;
		if (nombreES == null) {
			if (other.nombreES != null)
				return false;
		} else if (!nombreES.equals(other.nombreES))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Pais [idPais=" + idPais + ", nombreES=" + nombreES + ", nombreEN=" + nombreEN + "]";
	}

}
