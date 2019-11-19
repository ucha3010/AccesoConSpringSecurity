package com.damian.dao.model;

import java.io.Serializable;

public class ModelDireccion implements Serializable {

	/**
	 * Clase Direccion
	 */
	private static final long serialVersionUID = 1L;

	private int idDir;
	private String tipoVia;
	private String nombreVia;
	private String numero;
	private String resto;
	private String cp;
	private String provincia;
	private String ciudad;
	private String pais;
	private int idDatosPers;

	public ModelDireccion() {

	}

	/**
	 * @param idDir
	 * @param tipoVia
	 * @param nombreVia
	 * @param numero
	 * @param resto
	 * @param cp
	 * @param provincia
	 * @param ciudad
	 * @param pais
	 * @param idDatosPers
	 */
	public ModelDireccion(int idDir, String tipoVia, String nombreVia, String numero, String resto, String cp,
			String provincia, String ciudad, String pais, int idDatosPers) {
		this.idDir = idDir;
		this.tipoVia = tipoVia;
		this.nombreVia = nombreVia;
		this.numero = numero;
		this.resto = resto;
		this.cp = cp;
		this.provincia = provincia;
		this.ciudad = ciudad;
		this.pais = pais;
		this.idDatosPers = idDatosPers;
	}

	/**
	 * @return the idDir
	 */
	public int getIdDir() {
		return idDir;
	}

	/**
	 * @param idDir
	 *            the idDir to set
	 */
	public void setIdDir(int idDir) {
		this.idDir = idDir;
	}

	/**
	 * @return the tipoVia
	 */
	public String getTipoVia() {
		return tipoVia;
	}

	/**
	 * @param tipoVia
	 *            the tipoVia to set
	 */
	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	/**
	 * @return the nombreVia
	 */
	public String getNombreVia() {
		return nombreVia;
	}

	/**
	 * @param nombreVia
	 *            the nombreVia to set
	 */
	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the resto
	 */
	public String getResto() {
		return resto;
	}

	/**
	 * @param resto
	 *            the resto to set
	 */
	public void setResto(String resto) {
		this.resto = resto;
	}

	/**
	 * @return the cp
	 */
	public String getCp() {
		return cp;
	}

	/**
	 * @param cp
	 *            the cp to set
	 */
	public void setCp(String cp) {
		this.cp = cp;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia
	 *            the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad
	 *            the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais
	 *            the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * @return the idDatosPers
	 */
	public int getIdDatosPers() {
		return idDatosPers;
	}

	/**
	 * @param idDatosPers
	 *            the idDatosPers to set
	 */
	public void setIdDatosPers(int idDatosPers) {
		this.idDatosPers = idDatosPers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModelDireccion [idDir=" + idDir + ", tipoVia=" + tipoVia + ", nombreVia=" + nombreVia + ", numero="
				+ numero + ", resto=" + resto + ", cp=" + cp + ", provincia=" + provincia + ", ciudad=" + ciudad
				+ ", pais=" + pais + ", idDatosPers=" + idDatosPers + "]";
	}

}