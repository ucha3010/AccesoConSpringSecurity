package com.damian.dao.model;

public class ModelTema {

	private int idTem;
	private String nombre;

	public ModelTema() {
	}

	/**
	 * @return the idTem
	 */
	public int getIdTem() {
		return idTem;
	}

	/**
	 * @param idTem
	 *            the idTem to set
	 */
	public void setIdTem(int idTem) {
		this.idTem = idTem;
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
		return "ModelTema [idTem=" + idTem + ", nombre=" + nombre + "]";
	}

}
