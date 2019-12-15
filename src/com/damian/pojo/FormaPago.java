package com.damian.pojo;

import java.util.List;

public class FormaPago {
	
	private int idFor;
	private String nombre;
	private List<Factura> facturas;
	
	public FormaPago() {
		
	}

	/**
	 * @param idFor
	 * @param nombre
	 */
	public FormaPago(int idFor, String nombre) {
		this.idFor = idFor;
		this.nombre = nombre;
	}

	/**
	 * @param idFor
	 * @param nombre
	 * @param facturas
	 */
	public FormaPago(int idFor, String nombre, List<Factura> facturas) {
		this.idFor = idFor;
		this.nombre = nombre;
		this.facturas = facturas;
	}

	/**
	 * @return the idFor
	 */
	public int getIdFor() {
		return idFor;
	}

	/**
	 * @param idFor the idFor to set
	 */
	public void setIdFor(int idFor) {
		this.idFor = idFor;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the facturas
	 */
	public List<Factura> getFacturas() {
		return facturas;
	}

	/**
	 * @param facturas the facturas to set
	 */
	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModelFormaPago [idFor=" + idFor + ", nombre=" + nombre + "]";
	}

}
