package com.damian.pojo;

public class FacturaOrdenar {

	private int idFac;
	private double importe;

	public FacturaOrdenar() {

	}

	/**
	 * @param idFac
	 * @param importe
	 */
	public FacturaOrdenar(int idFac, double importe) {
		this.idFac = idFac;
		this.importe = importe;
	}

	/**
	 * @return the idFac
	 */
	public int getIdFac() {
		return idFac;
	}

	/**
	 * @param idFac
	 *            the idFac to set
	 */
	public void setIdFac(int idFac) {
		this.idFac = idFac;
	}

	/**
	 * @return the importe
	 */
	public double getImporte() {
		return importe;
	}

	/**
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(double importe) {
		this.importe = importe;
	}

}
