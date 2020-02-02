package com.damian.pojo;

import java.util.List;

public class Cuota {

	private int idCuo;
	private int cantidadCuotas;
	private double comisionAperturaPor;
	private double comisionAperturaImp;
	private double interesPor;
	private double interesImp;
	private List<Factura> facturas;

	public Cuota() {

	}

	/**
	 * @param idCuo
	 * @param cantidadCuotas
	 * @param comisionAperturaPor
	 * @param comisionAperturaImp
	 * @param interesPor
	 * @param interesImp
	 */
	public Cuota(int idCuo, int cantidadCuotas, double comisionAperturaPor, double comisionAperturaImp,
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
	 * @return the facturas
	 */
	public List<Factura> getFacturas() {
		return facturas;
	}

	/**
	 * @param facturas the facturas to set
	 */
	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cuota [idCuo=" + idCuo + ", cantidadCuotas=" + cantidadCuotas + ", comisionAperturaPor="
				+ comisionAperturaPor + ", comisionAperturaImp=" + comisionAperturaImp + ", interesPor=" + interesPor
				+ ", interesImp=" + interesImp + "]";
	}

}
