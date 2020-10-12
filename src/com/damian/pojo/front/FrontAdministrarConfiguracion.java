package com.damian.pojo.front;

public class FrontAdministrarConfiguracion {

	private int idUsr;
	private double ivaEnvio;
	private String speech;

	public FrontAdministrarConfiguracion() {

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
	 * @return the ivaEnvio
	 */
	public double getIvaEnvio() {
		return ivaEnvio;
	}

	/**
	 * @param ivaEnvio
	 *            the ivaEnvio to set
	 */
	public void setIvaEnvio(double ivaEnvio) {
		this.ivaEnvio = ivaEnvio;
	}

	/**
	 * @return the speech
	 */
	public String getSpeech() {
		return speech;
	}

	/**
	 * @param speech the speech to set
	 */
	public void setSpeech(String speech) {
		this.speech = speech;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FrontAdministrarConfiguracion [idUsr=" + idUsr + ", ivaEnvio=" + ivaEnvio + ", speech=" + speech + "]";
	}

}
