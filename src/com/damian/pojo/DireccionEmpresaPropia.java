package com.damian.pojo;

import java.sql.Timestamp;

public class DireccionEmpresaPropia {

	private int idDirPropia;
	private String tipoVia;
	private String nombreVia;
	private String numero;
	private String resto;
	private String cp;
	private String provincia;
	private String ciudad;
	private Pais pais;
	private EmpresaPropia empresaPropia;
	private String modificadoPor;
	private Timestamp fechaModificacion;

	public DireccionEmpresaPropia() {

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
	 * @param pais
	 * @param empresaPropia
	 */
	public DireccionEmpresaPropia(int idDirPropia, String tipoVia, String nombreVia, String numero, String resto,
			String cp, String provincia, String ciudad, Pais pais, EmpresaPropia empresaPropia) {
		this.idDirPropia = idDirPropia;
		this.tipoVia = tipoVia;
		this.nombreVia = nombreVia;
		this.numero = numero;
		this.resto = resto;
		this.cp = cp;
		this.provincia = provincia;
		this.ciudad = ciudad;
		this.pais = pais;
		this.empresaPropia = empresaPropia;
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
	 * @return the pais
	 */
	public Pais getPais() {
		return pais;
	}

	/**
	 * @param pais
	 *            the pais to set
	 */
	public void setPais(Pais pais) {
		this.pais = pais;
	}

	/**
	 * @return the empresaPropia
	 */
	public EmpresaPropia getEmpresaPropia() {
		return empresaPropia;
	}

	/**
	 * @param empresaPropia
	 *            the empresaPropia to set
	 */
	public void setEmpresaPropia(EmpresaPropia empresaPropia) {
		this.empresaPropia = empresaPropia;
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
		return "DireccionEmpresaPropia [idDirPropia=" + idDirPropia + ", tipoVia=" + tipoVia + ", nombreVia="
				+ nombreVia + ", numero=" + numero + ", resto=" + resto + ", cp=" + cp + ", provincia=" + provincia
				+ ", ciudad=" + ciudad + ", pais=" + pais + ", modificadoPor=" + modificadoPor + ", fechaModificacion="
				+ fechaModificacion + "]";
	}

}
