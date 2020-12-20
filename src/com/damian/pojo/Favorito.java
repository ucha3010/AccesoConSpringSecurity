package com.damian.pojo;

import java.sql.Timestamp;

public class Favorito {

	private int idUsr;
	private int idPro;
	private Timestamp fechaAlta;

	public Favorito() {
	}

	public Favorito(int idUsr, int idPro, Timestamp fechaAlta) {
		this.idUsr = idUsr;
		this.idPro = idPro;
		this.fechaAlta = fechaAlta;
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
	 * @return the idPro
	 */
	public int getIdPro() {
		return idPro;
	}

	/**
	 * @param idPro
	 *            the idPro to set
	 */
	public void setIdPro(int idPro) {
		this.idPro = idPro;
	}

	/**
	 * @return the fechaAlta
	 */
	public Timestamp getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @param fechaAlta
	 *            the fechaAlta to set
	 */
	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Favorito [idUsr=" + idUsr + ", idPro=" + idPro + ", fechaAlta=" + fechaAlta + "]";
	}

}
