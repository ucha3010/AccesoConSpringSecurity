package com.damian.dao.model;

import java.sql.Timestamp;

public class ModelDireccionEmpresaPropia {

	private int idDirPropia;
	private String tipoVia;
	private String nombreVia;
	private String numero;
	private String resto;
	private String cp;
	private String provincia;
	private String ciudad;
	private int pais_idPais;
	private int idPropia;
	private String modificadoPor;
	private Timestamp fechaModificacion;

	public ModelDireccionEmpresaPropia() {

	}

	/**
	 * @param idDirPropia
	 * @param tipoVia
	 * @param nombreVia
	 * @param numero
	 * @param resto
	 * @param cp
	 * @param provincia
	 * @param ciudad
	 * @param pais_idPais
	 * @param idPropia
	 */
	public ModelDireccionEmpresaPropia(int idDirPropia, String tipoVia, String nombreVia, String numero, String resto,
			String cp, String provincia, String ciudad, int pais_idPais, int idPropia) {
		this.idDirPropia = idDirPropia;
		this.tipoVia = tipoVia;
		this.nombreVia = nombreVia;
		this.numero = numero;
		this.resto = resto;
		this.cp = cp;
		this.provincia = provincia;
		this.ciudad = ciudad;
		this.pais_idPais = pais_idPais;
		this.idPropia = idPropia;
	}

	/**
	 * @return the idDirPropia
	 */
	public int getIdDirPropia() {
		return idDirPropia;
	}

	/**
	 * @param idDirPropia
	 *            the idDirPropia to set
	 */
	public void setIdDirPropia(int idDirPropia) {
		this.idDirPropia = idDirPropia;
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
	 * @return the pais_idPais
	 */
	public int getPais_idPais() {
		return pais_idPais;
	}

	/**
	 * @param pais_idPais
	 *            the pais_idPais to set
	 */
	public void setPais_idPais(int pais_idPais) {
		this.pais_idPais = pais_idPais;
	}

	/**
	 * @return the idPropia
	 */
	public int getIdPropia() {
		return idPropia;
	}

	/**
	 * @param idPropia
	 *            the idPropia to set
	 */
	public void setIdPropia(int idPropia) {
		this.idPropia = idPropia;
	}

	/**
	 * @return the modificadoPor
	 */
	public String getModificadoPor() {
		return modificadoPor;
	}

	/**
	 * @param modificadoPor
	 *            the modificadoPor to set
	 */
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	/**
	 * @return the fechaModificacion
	 */
	public Timestamp getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @param fechaModificacion
	 *            the fechaModificacion to set
	 */
	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModelDireccionEmpresa [idDirPropia=" + idDirPropia + ", tipoVia=" + tipoVia + ", nombreVia=" + nombreVia
				+ ", numero=" + numero + ", resto=" + resto + ", cp=" + cp + ", provincia=" + provincia + ", ciudad="
				+ ciudad + ", pais_idPais=" + pais_idPais + ", idPropia=" + idPropia + ", modificadoPor="
				+ modificadoPor + ", fechaModificacion=" + fechaModificacion + "]";
	}
}
