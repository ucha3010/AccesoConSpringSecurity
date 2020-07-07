package com.damian.dao.model;

public class ModelFacturaEnviarFacturar {

	private int idEnFa;
	private String nombre;
	private String direccion;
	private String cp;
	private String ciudad;
	private String provincia;
	private String pais;
	private String telefono;
	private boolean facturar;
	private boolean enviar;
	private int idFac;

	public ModelFacturaEnviarFacturar() {

	}

	/**
	 * @return the idEnFa
	 */
	public int getIdEnFa() {
		return idEnFa;
	}

	/**
	 * @param idEnFa
	 *            the idEnFa to set
	 */
	public void setIdEnFa(int idEnFa) {
		this.idEnFa = idEnFa;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion
	 *            the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
	 * @return the facturar
	 */
	public boolean isFacturar() {
		return facturar;
	}

	/**
	 * @param facturar
	 *            the facturar to set
	 */
	public void setFacturar(boolean facturar) {
		this.facturar = facturar;
	}

	/**
	 * @return the enviar
	 */
	public boolean isEnviar() {
		return enviar;
	}

	/**
	 * @param enviar
	 *            the enviar to set
	 */
	public void setEnviar(boolean enviar) {
		this.enviar = enviar;
	}

	/**
	 * @return the idFac
	 */
	public int getIdFac() {
		return idFac;
	}

	/**
	 * @param idFac
	 *            the idFac to set
	 */
	public void setIdFac(int idFac) {
		this.idFac = idFac;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModelFacturaEnviarFacturar [idEnFa=" + idEnFa + ", nombre=" + nombre + ", direccion=" + direccion
				+ ", cp=" + cp + ", ciudad=" + ciudad + ", provincia=" + provincia + ", pais=" + pais + ", telefono="
				+ telefono + ", facturar=" + facturar + ", enviar=" + enviar + ", idFac=" + idFac + "]";
	}

}
