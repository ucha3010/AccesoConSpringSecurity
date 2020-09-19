package com.damian.dao.model;

public class ModelBotonFavorito {

	private int idBot;
	private String nombre;

	public ModelBotonFavorito() {
	}

	/**
	 * @return the idBot
	 */
	public int getIdBot() {
		return idBot;
	}

	/**
	 * @param idBot
	 *            the idBot to set
	 */
	public void setIdBot(int idBot) {
		this.idBot = idBot;
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
		return "ModelBotonFavorito [idBot=" + idBot + ", nombre=" + nombre + "]";
	}

}
