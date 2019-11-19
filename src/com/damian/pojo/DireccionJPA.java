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
public class DireccionJPA implements Serializable {

	/**
	 * Clase Direccion
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDir")
	private int idDir;

	@Column(name = "tipoVia")
	private String tipoVia;

	@Column(name = "nombreVia")
	private String nombreVia;

	@Column(name = "numero")
	private String numero;

	@Column(name = "resto")
	private String resto;

	@Column(name = "cp")
	private String cp;

	@Column(name = "provincia")
	private String provincia;

	@Column(name = "ciudad")
	private String ciudad;

	@Column(name = "pais")
	private String pais;

	@ManyToOne
	@JoinColumn(name = "idDatosPers")
	private DatosPersonales datosPersonales;

	public DireccionJPA() {

	}

	public DireccionJPA(int idDir, String tipoVia, String nombreVia, String numero, String resto, String cp,
			String provincia, String ciudad, String pais) {
		this.idDir = idDir;
		this.tipoVia = tipoVia;
		this.nombreVia = nombreVia;
		this.numero = numero;
		this.resto = resto;
		this.cp = cp;
		this.provincia = provincia;
		this.ciudad = ciudad;
		this.pais = pais;
	}

	public DireccionJPA(int idDir, String tipoVia, String nombreVia, String numero, String resto, String cp,
			String provincia, String ciudad, String pais, DatosPersonales datosPersonales) {
		this.idDir = idDir;
		this.tipoVia = tipoVia;
		this.nombreVia = nombreVia;
		this.numero = numero;
		this.resto = resto;
		this.cp = cp;
		this.provincia = provincia;
		this.ciudad = ciudad;
		this.pais = pais;
		this.datosPersonales = datosPersonales;
	}

	public int getIdDir() {
		return idDir;
	}

	public void setIdDir(int idDir) {
		this.idDir = idDir;
	}

	public String getTipoVia() {
		return tipoVia;
	}

	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	public String getNombreVia() {
		return nombreVia;
	}

	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getResto() {
		return resto;
	}

	public void setResto(String resto) {
		this.resto = resto;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public DatosPersonales getDatosPersonales() {
		return datosPersonales;
	}

	public void setDatosPersonales(DatosPersonales datosPersonales) {
		this.datosPersonales = datosPersonales;
	}

	@Override
	public String toString() {
		return "Direccion [idDir=" + idDir + ", tipoVia=" + tipoVia + ", nombreVia=" + nombreVia + ", numero=" + numero
				+ ", resto=" + resto + ", cp=" + cp + ", provincia=" + provincia + ", ciudad=" + ciudad + ", pais="
				+ pais + "]";
	}

}
