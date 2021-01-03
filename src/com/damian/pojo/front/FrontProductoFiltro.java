package com.damian.pojo.front;

import java.util.List;

public class FrontProductoFiltro {

	private int idSub;
	private String tituloNuevo;
	private int idTituloNuevo;
	private String nombreNuevo;
	private List<Integer> idNombres;
	private int productoPaginaInicio;
	private int productoTotalPaginas;
	private int idPro;

	/**
	 * @return the idSub
	 */
	public int getIdSub() {
		return idSub;
	}

	/**
	 * @param idSub
	 *            the idSub to set
	 */
	public void setIdSub(int idSub) {
		this.idSub = idSub;
	}

	/**
	 * @return the tituloNuevo
	 */
	public String getTituloNuevo() {
		return tituloNuevo;
	}

	/**
	 * @param tituloNuevo
	 *            the tituloNuevo to set
	 */
	public void setTituloNuevo(String tituloNuevo) {
		this.tituloNuevo = tituloNuevo;
	}

	/**
	 * @return the idTituloNuevo
	 */
	public int getIdTituloNuevo() {
		return idTituloNuevo;
	}

	/**
	 * @param idTituloNuevo
	 *            the idTituloNuevo to set
	 */
	public void setIdTituloNuevo(int idTituloNuevo) {
		this.idTituloNuevo = idTituloNuevo;
	}

	/**
	 * @return the nombreNuevo
	 */
	public String getNombreNuevo() {
		return nombreNuevo;
	}

	/**
	 * @param nombreNuevo
	 *            the nombreNuevo to set
	 */
	public void setNombreNuevo(String nombreNuevo) {
		this.nombreNuevo = nombreNuevo;
	}

	/**
	 * @return the idNombres
	 */
	public List<Integer> getIdNombres() {
		return idNombres;
	}

	/**
	 * @param idNombres
	 *            the idNombres to set
	 */
	public void setIdNombres(List<Integer> idNombres) {
		this.idNombres = idNombres;
	}

	/**
	 * @return the productoPaginaInicio
	 */
	public int getProductoPaginaInicio() {
		return productoPaginaInicio;
	}

	/**
	 * @param productoPaginaInicio
	 *            the productoPaginaInicio to set
	 */
	public void setProductoPaginaInicio(int productoPaginaInicio) {
		this.productoPaginaInicio = productoPaginaInicio;
	}

	/**
	 * @return the productoTotalPaginas
	 */
	public int getProductoTotalPaginas() {
		return productoTotalPaginas;
	}

	/**
	 * @param productoTotalPaginas
	 *            the productoTotalPaginas to set
	 */
	public void setProductoTotalPaginas(int productoTotalPaginas) {
		this.productoTotalPaginas = productoTotalPaginas;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FrontProductoFiltro [idSub=" + idSub + ", tituloNuevo=" + tituloNuevo + ", idTituloNuevo="
				+ idTituloNuevo + ", nombreNuevo=" + nombreNuevo + ", idNombres=" + idNombres
				+ ", productoPaginaInicio=" + productoPaginaInicio + ", productoTotalPaginas=" + productoTotalPaginas
				+ ", idPro=" + idPro + "]";
	}

}
