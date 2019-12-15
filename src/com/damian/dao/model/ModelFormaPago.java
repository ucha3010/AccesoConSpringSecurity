package com.damian.dao.model;

public class ModelFormaPago {
	
	private int idFor;
	private String nombre;
	
	public ModelFormaPago() {
		
	}

	/**
	 * @param idFor
	 * @param nombre
	 */
	public ModelFormaPago(int idFor, String nombre) {
		this.idFor = idFor;
		this.nombre = nombre;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModelFormaPago [idFor=" + idFor + ", nombre=" + nombre + "]";
	}

}
