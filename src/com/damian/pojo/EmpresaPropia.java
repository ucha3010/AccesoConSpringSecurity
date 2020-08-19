package com.damian.pojo;

public class EmpresaPropia {

	private int idPropia;
	private String razonSocial;
	private String cif;
	private String telefono;
	private String fax;
	private String email;
	private boolean facturacion;
	private DireccionEmpresaPropia direccionEmpresaPropia;

	public EmpresaPropia() {

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
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * @param razonSocial
	 *            the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
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
	 * @return the facturacion
	 */
	public boolean isFacturacion() {
		return facturacion;
	}

	/**
	 * @param facturacion
	 *            the facturacion to set
	 */
	public void setFacturacion(boolean facturacion) {
		this.facturacion = facturacion;
	}

	public DireccionEmpresaPropia getDireccionEmpresaPropia() {
		return direccionEmpresaPropia;
	}

	public void setDireccionEmpresaPropia(DireccionEmpresaPropia direccionEmpresaPropia) {
		this.direccionEmpresaPropia = direccionEmpresaPropia;
	}

	@Override
	public String toString() {
		return "EmpresaPropia [idPropia=" + idPropia + ", razonSocial=" + razonSocial + ", cif=" + cif + ", telefono="
				+ telefono + ", fax=" + fax + ", email=" + email + ", facturacion=" + facturacion
				+ ", direccionEmpresaPropia=" + direccionEmpresaPropia + "]";
	}

}
