package com.damian.dao.model;

public class ModelSubcategoria {
	
	private int idSub;
	private String nombre;
	private int idCat;
	
	public ModelSubcategoria() {
		
	}

	/**
	 * @param idSub
	 * @param nombre
	 * @param idCat
	 */
	public ModelSubcategoria(int idSub, String nombre, int idCat) {
		this.idSub = idSub;
		this.nombre = nombre;
		this.idCat = idCat;
	}

	/**
	 * @return the idSub
	 */
	public int getIdSub() {
		return idSub;
	}

	/**
	 * @param idSub the idSub to set
	 */
	public void setIdSub(int idSub) {
		this.idSub = idSub;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModelSubcategoria [idSub=" + idSub + ", nombre=" + nombre + ", idCat=" + idCat + "]";
	}

}
