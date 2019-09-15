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

	public Pais() {
	}


	public Pais(int idPais, String nombreES) {
		this.idPais = idPais;
		this.nombreES = nombreES;
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPais;
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
		if (nombreES == null) {
			if (other.nombreES != null)
				return false;
		} else if (!nombreES.equals(other.nombreES))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Pais [idPais=" + idPais + ", nombreES=" + nombreES + "]";
	}

}
