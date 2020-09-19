package com.damian.pojo;

public class PreferenciaUsuario {

	private int idPrefUsr;
	private String tema;
	private String botonFavorito;
	private boolean recibirPublicidad;

	public PreferenciaUsuario() {
	}

	/**
	 * @return the idPrefUsr
	 */
	public int getIdPrefUsr() {
		return idPrefUsr;
	}

	/**
	 * @param idPrefUsr
	 *            the idPrefUsr to set
	 */
	public void setIdPrefUsr(int idPrefUsr) {
		this.idPrefUsr = idPrefUsr;
	}

	/**
	 * @return the tema
	 */
	public String getTema() {
		return tema;
	}

	/**
	 * @param tema
	 *            the tema to set
	 */
	public void setTema(String tema) {
		this.tema = tema;
	}

	/**
	 * @return the botonFavorito
	 */
	public String getBotonFavorito() {
		return botonFavorito;
	}

	/**
	 * @param botonFavorito
	 *            the botonFavorito to set
	 */
	public void setBotonFavorito(String botonFavorito) {
		this.botonFavorito = botonFavorito;
	}

	/**
	 * @return the recibirPublicidad
	 */
	public boolean isRecibirPublicidad() {
		return recibirPublicidad;
	}

	/**
	 * @param recibirPublicidad
	 *            the recibirPublicidad to set
	 */
	public void setRecibirPublicidad(boolean recibirPublicidad) {
		this.recibirPublicidad = recibirPublicidad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PreferenciaUsuario [idPrefUsr=" + idPrefUsr + ", tema=" + tema + ", botonFavorito=" + botonFavorito
				+ ", recibirPublicidad=" + recibirPublicidad + "]";
	}

}
