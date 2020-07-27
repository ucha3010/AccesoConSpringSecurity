package com.damian.dao.model;

import java.sql.Timestamp;

public class ModelCuota {

	private int idCuo;
	private int cantidadCuotas;
	private double comisionAperturaPor;
	private double comisionAperturaImp;
	private double interesPor;
	private double interesImp;
	private double totalCompletoAPagar;
	private String modificadoPor;
	private Timestamp fechaModificacion;

	public ModelCuota() {

	}

	/**
	 * @param idCuo
	 * @param cantidadCuotas
	 * @param comisionAperturaPor
	 * @param comisionAperturaImp
	 * @param interesPor
	 * @param interesImp
	 */
	public ModelCuota(int idCuo, int cantidadCuotas, double comisionAperturaPor, double comisionAperturaImp,
			double interesPor, double interesImp) {
		this.idCuo = idCuo;
		this.cantidadCuotas = cantidadCuotas;
		this.comisionAperturaPor = comisionAperturaPor;
		this.comisionAperturaImp = comisionAperturaImp;
		this.interesPor = interesPor;
		this.interesImp = interesImp;
	}

	/**
	 * @return the idCuo
	 */
	public int getIdCuo() {
		return idCuo;
	}

	/**
	 * @param idCuo
	 *            the idCuo to set
	 */
	public void setIdCuo(int idCuo) {
		this.idCuo = idCuo;
	}

	/**
	 * @return the cantidadCuotas
	 */
	public int getCantidadCuotas() {
		return cantidadCuotas;
	}

	/**
	 * @param cantidadCuotas
	 *            the cantidadCuotas to set
	 */
	public void setCantidadCuotas(int cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}

	/**
	 * @return the comisionAperturaPor
	 */
	public double getComisionAperturaPor() {
		return comisionAperturaPor;
	}

	/**
	 * @param comisionAperturaPor
	 *            the comisionAperturaPor to set
	 */
	public void setComisionAperturaPor(double comisionAperturaPor) {
		this.comisionAperturaPor = comisionAperturaPor;
	}

	/**
	 * @return the comisionAperturaImp
	 */
	public double getComisionAperturaImp() {
		return comisionAperturaImp;
	}

	/**
	 * @param comisionAperturaImp
	 *            the comisionAperturaImp to set
	 */
	public void setComisionAperturaImp(double comisionAperturaImp) {
		this.comisionAperturaImp = comisionAperturaImp;
	}

	/**
	 * @return the interesPor
	 */
	public double getInteresPor() {
		return interesPor;
	}

	/**
	 * @param interesPor
	 *            the interesPor to set
	 */
	public void setInteresPor(double interesPor) {
		this.interesPor = interesPor;
	}

	/**
	 * @return the interesImp
	 */
	public double getInteresImp() {
		return interesImp;
	}

	/**
	 * @param interesImp
	 *            the interesImp to set
	 */
	public void setInteresImp(double interesImp) {
		this.interesImp = interesImp;
	}

	/**
	 * @return the totalCompletoAPagar
	 */
	public double getTotalCompletoAPagar() {
		return totalCompletoAPagar;
	}

	/**
	 * @param totalCompletoAPagar
	 *            the totalCompletoAPagar to set
	 */
	public void setTotalCompletoAPagar(double totalCompletoAPagar) {
		this.totalCompletoAPagar = totalCompletoAPagar;
	}

	/**
	 * @return the modificadoPor
	 */
	public String getModificadoPor() {
		return modificadoPor;
	}

	/**
	 * @param modificadoPor
	 *            the modificadoPor to set
	 */
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	/**
	 * @return the fechaModificacion
	 */
	public Timestamp getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @param fechaModificacion
	 *            the fechaModificacion to set
	 */
	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModelCuota [idCuo=" + idCuo + ", cantidadCuotas=" + cantidadCuotas + ", comisionAperturaPor="
				+ comisionAperturaPor + ", comisionAperturaImp=" + comisionAperturaImp + ", interesPor=" + interesPor
				+ ", interesImp=" + interesImp + ", totalCompletoAPagar=" + totalCompletoAPagar + ", modificadoPor="
				+ modificadoPor + ", fechaModificacion=" + fechaModificacion + "]";
	}

}
