package com.damian.dao.model;

import java.io.Serializable;

public class ModelEmpresa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 130820142307L;

	private int idEmp;
	private String nombreComercial;
	private String tipoSociedad;
	private String actividad;
	private String cif;
	private String email;
	private String paginaWeb;
	private String telefono;
	private String fax;
	private String observaciones;

	public ModelEmpresa() {

	}

	/**
	 * @param idEmp
	 * @param nombreComercial
	 * @param tipoSociedad
	 * @param actividad
	 * @param cif
	 * @param email
	 * @param paginaWeb
	 * @param telefono
	 * @param fax
	 * @param observaciones
	 */
	public ModelEmpresa(int idEmp, String nombreComercial, String tipoSociedad, String actividad, String cif,
			String email, String paginaWeb, String telefono, String fax, String observaciones) {
		this.idEmp = idEmp;
		this.nombreComercial = nombreComercial;
		this.tipoSociedad = tipoSociedad;
		this.actividad = actividad;
		this.cif = cif;
		this.email = email;
		this.paginaWeb = paginaWeb;
		this.telefono = telefono;
		this.fax = fax;
		this.observaciones = observaciones;
	}

	/**
	 * @return the idEmp
	 */
	public int getIdEmp() {
		return idEmp;
	}

	/**
	 * @param idEmp
	 *            the idEmp to set
	 */
	public void setIdEmp(int idEmp) {
		this.idEmp = idEmp;
	}

	/**
	 * @return the nombreComercial
	 */
	public String getNombreComercial() {
		return nombreComercial;
	}

	/**
	 * @param nombreComercial
	 *            the nombreComercial to set
	 */
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	/**
	 * @return the tipoSociedad
	 */
	public String getTipoSociedad() {
		return tipoSociedad;
	}

	/**
	 * @param tipoSociedad
	 *            the tipoSociedad to set
	 */
	public void setTipoSociedad(String tipoSociedad) {
		this.tipoSociedad = tipoSociedad;
	}

	/**
	 * @return the actividad
	 */
	public String getActividad() {
		return actividad;
	}

	/**
	 * @param actividad
	 *            the actividad to set
	 */
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	/**
	 * @return the cif
	 */
	public String getCif() {
		return cif;
	}

	/**
	 * @param cif
	 *            the cif to set
	 */
	public void setCif(String cif) {
		this.cif = cif;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the paginaWeb
	 */
	public String getPaginaWeb() {
		return paginaWeb;
	}

	/**
	 * @param paginaWeb
	 *            the paginaWeb to set
	 */
	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones
	 *            the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Empresa [idEmp=" + idEmp + ", nombreComercial=" + nombreComercial + ", tipoSociedad=" + tipoSociedad
				+ ", actividad=" + actividad + ", cif=" + cif + ", email=" + email + ", paginaWeb=" + paginaWeb
				+ ", telefono=" + telefono + ", fax=" + fax + ", observaciones=" + observaciones + "]";
	}

}
