package com.damian.dao.model;

import java.util.Date;

public class ModelCuotaDetalle {

	private int idCuDe;
	private int idCuo;
	private double importeSinInteres;
	private double importeInteres;
	private double importeCuota;
	private Date fecha;
	private double capitalPendienteAntes;
	private double capitalPendienteDespues;
	private int numeroCuota;

	public ModelCuotaDetalle() {

	}

	/**
	 * @return the idCuDe
	 */
	public int getIdCuDe() {
		return idCuDe;
	}

	/**
	 * @param idCuDe
	 *            the idCuDe to set
	 */
	public void setIdCuDe(int idCuDe) {
		this.idCuDe = idCuDe;
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
	 * @return the importeSinInteres
	 */
	public double getImporteSinInteres() {
		return importeSinInteres;
	}

	/**
	 * @param importeSinInteres
	 *            the importeSinInteres to set
	 */
	public void setImporteSinInteres(double importeSinInteres) {
		this.importeSinInteres = importeSinInteres;
	}

	/**
	 * @return the importeInteres
	 */
	public double getImporteInteres() {
		return importeInteres;
	}

	/**
	 * @param importeInteres
	 *            the importeInteres to set
	 */
	public void setImporteInteres(double importeInteres) {
		this.importeInteres = importeInteres;
	}

	/**
	 * @return the importeCuota
	 */
	public double getImporteCuota() {
		return importeCuota;
	}

	/**
	 * @param importeCuota
	 *            the importeCuota to set
	 */
	public void setImporteCuota(double importeCuota) {
		this.importeCuota = importeCuota;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the capitalPendienteAntes
	 */
	public double getCapitalPendienteAntes() {
		return capitalPendienteAntes;
	}

	/**
	 * @param capitalPendienteAntes
	 *            the capitalPendienteAntes to set
	 */
	public void setCapitalPendienteAntes(double capitalPendienteAntes) {
		this.capitalPendienteAntes = capitalPendienteAntes;
	}

	/**
	 * @return the capitalPendienteDespues
	 */
	public double getCapitalPendienteDespues() {
		return capitalPendienteDespues;
	}

	/**
	 * @param capitalPendienteDespues
	 *            the capitalPendienteDespues to set
	 */
	public void setCapitalPendienteDespues(double capitalPendienteDespues) {
		this.capitalPendienteDespues = capitalPendienteDespues;
	}

	/**
	 * @return the numeroCuota
	 */
	public int getNumeroCuota() {
		return numeroCuota;
	}

	/**
	 * @param numeroCuota
	 *            the numeroCuota to set
	 */
	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModelCuotaDetalle [idCuDe=" + idCuDe + ", idCuo=" + idCuo + ", importeSinInteres=" + importeSinInteres
				+ ", importeInteres=" + importeInteres + ", importeCuota=" + importeCuota + ", fecha=" + fecha
				+ ", capitalPendienteAntes=" + capitalPendienteAntes + ", capitalPendienteDespues="
				+ capitalPendienteDespues + ", numeroCuota=" + numeroCuota + "]";
	}

}
