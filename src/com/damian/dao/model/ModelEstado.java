package com.damian.dao.model;

public class ModelEstado {
	
	private int idEst;
	private String nombre;
	
	public ModelEstado() {
		
	}

	/**
	 * @param idEst
	 * @param nombre
	 */
	public ModelEstado(int idEst, String nombre) {
		this.idEst = idEst;
		this.nombre = nombre;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModelEstado [idEst=" + idEst + ", nombre=" + nombre + "]";
	}
	
}
