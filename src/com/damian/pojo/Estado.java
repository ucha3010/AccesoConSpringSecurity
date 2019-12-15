package com.damian.pojo;

import java.util.List;

public class Estado {
	
	private int idEst;
	private String nombre;
	private List<Factura> facturas;
	
	public Estado() {
		
	}

	/**
	 * @param idEst
	 * @param nombre
	 */
	public Estado(int idEst, String nombre) {
		this.idEst = idEst;
		this.nombre = nombre;
	}

	/**
	 * @param idEst
	 * @param nombre
	 * @param facturas
	 */
	public Estado(int idEst, String nombre, List<Factura> facturas) {
		this.idEst = idEst;
		this.nombre = nombre;
		this.facturas = facturas;
	}

	/**
	 * @return the idEst
	 */
	public int getIdEst() {
		return idEst;
	}

	/**
	 * @param idEst the idEst to set
	 */
	public void setIdEst(int idEst) {
		this.idEst = idEst;
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
		return "ModelEstado [idEst=" + idEst + ", nombre=" + nombre + "]";
	}
	
}
