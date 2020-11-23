package com.damian.pojo;

public class Marca {

	private int idMar;
	private String nombre;

	public Marca() {

	}

	/**
	 * @return the idMar
	 */
	public int getIdMar() {
		return idMar;
	}

	/**
	 * @param idMar
	 *            the idMar to set
	 */
	public void setIdMar(int idMar) {
		this.idMar = idMar;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Marca [idMar=" + idMar + ", nombre=" + nombre + "]";
	}

}
