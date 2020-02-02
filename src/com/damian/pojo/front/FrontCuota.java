package com.damian.pojo.front;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class FrontCuota {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaCompra;
	private double importeTotal;
	private int numeroCuota;

	/**
	 * @return the fechaCompra
	 */
	public Date getFechaCompra() {
		return fechaCompra;
	}

	/**
	 * @param fechaCompra
	 *            the fechaCompra to set
	 */
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	/**
	 * @return the importeTotal
	 */
	public double getImporteTotal() {
		return importeTotal;
	}

	/**
	 * @param importeTotal
	 *            the importeTotal to set
	 */
	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
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
		return "FrontCuota [fechaCompra=" + fechaCompra + ", importeTotal=" + importeTotal + ", numeroCuota="
				+ numeroCuota + "]";
	}

}
