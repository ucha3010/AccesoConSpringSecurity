package com.damian.pojo.front;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class FrontCuota {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaCobro;
	private double importeTotal;
	private int numeroCuota;

	/**
	 * @return the fechaCobro
	 */
	public Date getFechaCobro() {
		return fechaCobro;
	}

	/**
	 * @param fechaCobro
	 *            the fechaCobro to set
	 */
	public void setFechaCobro(Date fechaCobro) {
		this.fechaCobro = fechaCobro;
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
		return "FrontCuota [fechaCobro=" + fechaCobro + ", importeTotal=" + importeTotal + ", numeroCuota="
				+ numeroCuota + "]";
	}

}
