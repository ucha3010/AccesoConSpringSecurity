package com.damian.dao.model;

public class ModelUsuarioOrden {

	private int id;
	private int idUsr;
	private String tabla;
	private String columna;
	private String orden;

	public ModelUsuarioOrden() {

	}

	/**
	 * @param id
	 * @param idUsr
	 * @param tabla
	 * @param columna
	 * @param orden
	 */
	public ModelUsuarioOrden(int id, int idUsr, String tabla, String columna, String orden) {
		this.id = id;
		this.idUsr = idUsr;
		this.tabla = tabla;
		this.columna = columna;
		this.orden = orden;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the idUsr
	 */
	public int getIdUsr() {
		return idUsr;
	}

	/**
	 * @param idUsr
	 *            the idUsr to set
	 */
	public void setIdUsr(int idUsr) {
		this.idUsr = idUsr;
	}

	/**
	 * @return the tabla
	 */
	public String getTabla() {
		return tabla;
	}

	/**
	 * @param tabla
	 *            the tabla to set
	 */
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	/**
	 * @return the columna
	 */
	public String getColumna() {
		return columna;
	}

	/**
	 * @param columna
	 *            the columna to set
	 */
	public void setColumna(String columna) {
		this.columna = columna;
	}

	/**
	 * @return the orden
	 */
	public String getOrden() {
		return orden;
	}

	/**
	 * @param orden
	 *            the orden to set
	 */
	public void setOrden(String orden) {
		this.orden = orden;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModelUsuarioOrden [id=" + id + ", idUsr=" + idUsr + ", tabla=" + tabla + ", columna=" + columna
				+ ", orden=" + orden + "]";
	}

}
