package com.damian.pojo.front;

import java.util.ArrayList;
import java.util.List;

public class Paginacion {

	private int actual;
	private int anterior;
	private int siguiente;
	private int totalRegistros;
	private int registrosPorPagina;
	private int totalPaginas;
	private List<Integer> comienzaPagina = new ArrayList<>();
	private boolean primeraPagina;
	private boolean ultimaPagina;

	/**
	 * @return the actual
	 */
	public int getActual() {
		return actual;
	}

	/**
	 * @param actual
	 *            the actual to set
	 */
	public void setActual(int actual) {
		this.actual = actual;
	}

	/**
	 * @return the anterior
	 */
	public int getAnterior() {
		return anterior;
	}

	/**
	 * @param anterior
	 *            the anterior to set
	 */
	public void setAnterior(int anterior) {
		this.anterior = anterior;
	}

	/**
	 * @return the siguiente
	 */
	public int getSiguiente() {
		return siguiente;
	}

	/**
	 * @param siguiente
	 *            the siguiente to set
	 */
	public void setSiguiente(int siguiente) {
		this.siguiente = siguiente;
	}

	/**
	 * @return the totalRegistros
	 */
	public int getTotalRegistros() {
		return totalRegistros;
	}

	/**
	 * @param totalRegistros
	 *            the totalRegistros to set
	 */
	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	/**
	 * @return the registrosPorPagina
	 */
	public int getRegistrosPorPagina() {
		return registrosPorPagina;
	}

	/**
	 * @param registrosPorPagina
	 *            the registrosPorPagina to set
	 */
	public void setRegistrosPorPagina(int registrosPorPagina) {
		this.registrosPorPagina = registrosPorPagina;
	}

	/**
	 * @return the totalPaginas
	 */
	public int getTotalPaginas() {
		return totalPaginas;
	}

	/**
	 * @param totalPaginas
	 *            the totalPaginas to set
	 */
	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}

	/**
	 * @return the comienzaPagina
	 */
	public List<Integer> getComienzaPagina() {
		return comienzaPagina;
	}

	/**
	 * @param comienzaPagina
	 *            the comienzaPagina to set
	 */
	public void setComienzaPagina(List<Integer> comienzaPagina) {
		this.comienzaPagina = comienzaPagina;
	}

	/**
	 * @return the primeraPagina
	 */
	public boolean isPrimeraPagina() {
		return primeraPagina;
	}

	/**
	 * @param primeraPagina
	 *            the primeraPagina to set
	 */
	public void setPrimeraPagina(boolean primeraPagina) {
		this.primeraPagina = primeraPagina;
	}

	/**
	 * @return the ultimaPagina
	 */
	public boolean isUltimaPagina() {
		return ultimaPagina;
	}

	/**
	 * @param ultimaPagina
	 *            the ultimaPagina to set
	 */
	public void setUltimaPagina(boolean ultimaPagina) {
		this.ultimaPagina = ultimaPagina;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Paginacion [actual=" + actual + ", anterior=" + anterior + ", siguiente=" + siguiente
				+ ", totalRegistros=" + totalRegistros + ", registrosPorPagina=" + registrosPorPagina
				+ ", totalPaginas=" + totalPaginas + ", comienzaPagina=" + comienzaPagina + ", primeraPagina="
				+ primeraPagina + ", ultimaPagina=" + ultimaPagina + "]";
	}

}
