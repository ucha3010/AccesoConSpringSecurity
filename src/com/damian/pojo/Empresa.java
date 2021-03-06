package com.damian.pojo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Empresa {

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
	private List<UsuarioEmpresa> usuarioEmpresa = new ArrayList<UsuarioEmpresa>();
	private List<DireccionEmpresa> direccionesEmpresa = new ArrayList<DireccionEmpresa>();
	private List<ProductoEmpresa> productoEmpresaList;
	private List<Foto> fotos;
	private String modificadoPor;
	private Timestamp fechaModificacion;

	public Empresa() {

	}

	public Empresa(int idEmp) {
		this.idEmp = idEmp;
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

	/**
	 * @return the usuarioEmpresa
	 */
	public List<UsuarioEmpresa> getUsuarioEmpresa() {
		return usuarioEmpresa;
	}

	/**
	 * @param usuarioEmpresa
	 *            the usuarioEmpresa to set
	 */
	public void setUsuarioEmpresa(List<UsuarioEmpresa> usuarioEmpresa) {
		this.usuarioEmpresa = usuarioEmpresa;
	}

	/**
	 * @return the direccionesEmpresa
	 */
	public List<DireccionEmpresa> getDireccionesEmpresa() {
		return direccionesEmpresa;
	}

	/**
	 * @param direccionesEmpresa
	 *            the direccionesEmpresa to set
	 */
	public void setDireccionesEmpresa(List<DireccionEmpresa> direccionesEmpresa) {
		this.direccionesEmpresa = direccionesEmpresa;
	}

	/**
	 * @return the productoEmpresaList
	 */
	public List<ProductoEmpresa> getProductoEmpresaList() {
		return productoEmpresaList;
	}

	/**
	 * @param productoEmpresaList
	 *            the productoEmpresaList to set
	 */
	public void setProductoEmpresaList(List<ProductoEmpresa> productoEmpresaList) {
		this.productoEmpresaList = productoEmpresaList;
	}

	/**
	 * @return the fotos
	 */
	public List<Foto> getFotos() {
		return fotos;
	}

	/**
	 * @param fotos
	 *            the fotos to set
	 */
	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
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
		return "Empresa [idEmp=" + idEmp + ", nombreComercial=" + nombreComercial + ", tipoSociedad=" + tipoSociedad
				+ ", actividad=" + actividad + ", cif=" + cif + ", email=" + email + ", paginaWeb=" + paginaWeb
				+ ", telefono=" + telefono + ", fax=" + fax + ", observaciones=" + observaciones + ", usuarioEmpresa="
				+ usuarioEmpresa + ", direccionesEmpresa=" + direccionesEmpresa + ", productoEmpresaList="
				+ productoEmpresaList + ", fotos=" + fotos + ", modificadoPor=" + modificadoPor + ", fechaModificacion="
				+ fechaModificacion + "]";
	}

}
