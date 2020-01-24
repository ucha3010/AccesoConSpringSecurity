package com.damian.pojo;

import java.util.List;

public class FormaPago {

	private int idFor;
	private String nombreES;
	private String nombreEN;
	private List<Factura> facturas;

	public FormaPago() {

	}

	/**
	 * @param idFor
	 * @param nombreES
	 * @param nombreEN
	 */
	public FormaPago(int idFor, String nombreES, String nombreEN) {
		this.idFor = idFor;
		this.nombreES = nombreES;
		this.nombreEN = nombreEN;
	}

	/**
	 * @return the idFor
	 */
	public int getIdFor() {
		return idFor;
	}

	/**
	 * @param idFor
	 *            the idFor to set
	 */
	public void setIdFor(int idFor) {
		this.idFor = idFor;
	}

	/**
	 * @return the nombreES
	 */
	public String getNombreES() {
		return nombreES;
	}

	/**
	 * @param nombreES
	 *            the nombreES to set
	 */
	public void setNombreES(String nombreES) {
		this.nombreES = nombreES;
	}

	/**
	 * @return the nombreEN
	 */
	public String getNombreEN() {
		return nombreEN;
	}

	/**
	 * @param nombreEN
	 *            the nombreEN to set
	 */
	public void setNombreEN(String nombreEN) {
		this.nombreEN = nombreEN;
	}

	/**
	 * @return the facturas
	 */
	public List<Factura> getFacturas() {
		return facturas;
	}

	/**
	 * @param facturas
	 *            the facturas to set
	 */
	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FormaPago [idFor=" + idFor + ", nombreES=" + nombreES + ", nombreEN=" + nombreEN + "]";
	}

}
