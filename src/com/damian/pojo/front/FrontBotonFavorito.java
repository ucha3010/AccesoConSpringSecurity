package com.damian.pojo.front;

public class FrontBotonFavorito {

	private String nombre;
	private String codigoHTML;

	public FrontBotonFavorito() {
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
	 * @return the codigoHTML
	 */
	public String getCodigoHTML() {
		return codigoHTML;
	}

	/**
	 * @param codigoHTML
	 *            the codigoHTML to set
	 */
	public void setCodigoHTML(String codigoHTML) {
		this.codigoHTML = codigoHTML;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FrontBotonFavorito [nombre=" + nombre + ", codigoHTML=" + codigoHTML + "]";
	}

}
