package com.damian.dao.model;

public class ModelCategoria {
	
	private int idCat;
	private String nombre;

	/**
	 * 
	 */
	public ModelCategoria() {
	}
	
	/**
	 * @param idCat
	 * @param nombre
	 */
	public ModelCategoria(int idCat, String nombre) {
		this.idCat = idCat;
		this.nombre = nombre;
	}

	/**
	 * @return the idCat
	 */
	public int getIdCat() {
		return idCat;
	}

	/**
	 * @param idCat the idCat to set
	 */
	public void setIdCat(int idCat) {
		this.idCat = idCat;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModelCategoria [idCat=" + idCat + ", nombre=" + nombre + "]";
	}

}
