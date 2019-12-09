package com.damian.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 130820142307L;

	private int idPro;
	private String descripcion;
	private int unidades;
	private double precioVenta;
	private double precioCompra;
	private String marca;
	private String modelo;
	private String serie;

	/**
	 * Ubicación dentro del almacén
	 */
	private String ubicacion;

	/**
	 * nuevo, 90%, 80%...
	 */
	private String estado;
	
	private String partida;
	private Date fechaCompra;
	private boolean enviar;

	/**
	 * Vendible o de uso interno
	 */
	private boolean vendible;

	private double mesesGarantia;
	private double peso;
	private double volumen;
	private List<Empresa> proveedores;

	public Producto() {

	}

	/**
	 * @param idPro
	 * @param descripcion
	 * @param unidades
	 * @param precioVenta
	 * @param precioCompra
	 * @param marca
	 * @param modelo
	 * @param serie
	 * @param ubicacion
	 * @param partida
	 * @param fechaCompra
	 * @param enviar
	 * @param vendible
	 * @param mesesGarantia
	 * @param peso
	 * @param volumen
	 */
	public Producto(int idPro, String descripcion, int unidades, double precioVenta, double precioCompra, String marca,
			String modelo, String serie, String ubicacion, String partida, Date fechaCompra, boolean enviar,
			boolean vendible, double mesesGarantia, double peso, double volumen) {
		this.idPro = idPro;
		this.descripcion = descripcion;
		this.unidades = unidades;
		this.precioVenta = precioVenta;
		this.precioCompra = precioCompra;
		this.marca = marca;
		this.modelo = modelo;
		this.serie = serie;
		this.ubicacion = ubicacion;
		this.partida = partida;
		this.fechaCompra = fechaCompra;
		this.enviar = enviar;
		this.vendible = vendible;
		this.mesesGarantia = mesesGarantia;
		this.peso = peso;
		this.volumen = volumen;
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

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the unidades
	 */
	public int getUnidades() {
		return unidades;
	}

	/**
	 * @param unidades
	 *            the unidades to set
	 */
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	/**
	 * @return the precioVenta
	 */
	public double getPrecioVenta() {
		return precioVenta;
	}

	/**
	 * @param precioVenta
	 *            the precioVenta to set
	 */
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	/**
	 * @return the precioCompra
	 */
	public double getPrecioCompra() {
		return precioCompra;
	}

	/**
	 * @param precioCompra
	 *            the precioCompra to set
	 */
	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca
	 *            the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * @param modelo
	 *            the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the serie
	 */
	public String getSerie() {
		return serie;
	}

	/**
	 * @param serie
	 *            the serie to set
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}

	/**
	 * @return the ubicacion
	 */
	public String getUbicacion() {
		return ubicacion;
	}

	/**
	 * @param ubicacion
	 *            the ubicacion to set
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * @return the partida
	 */
	public String getPartida() {
		return partida;
	}

	/**
	 * @param partida
	 *            the partida to set
	 */
	public void setPartida(String partida) {
		this.partida = partida;
	}

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
	 * @return the enviar
	 */
	public boolean isEnviar() {
		return enviar;
	}

	/**
	 * @param enviar
	 *            the enviar to set
	 */
	public void setEnviar(boolean enviar) {
		this.enviar = enviar;
	}

	/**
	 * @return the vendible
	 */
	public boolean isVendible() {
		return vendible;
	}

	/**
	 * @param vendible
	 *            the vendible to set
	 */
	public void setVendible(boolean vendible) {
		this.vendible = vendible;
	}

	/**
	 * @return the mesesGarantia
	 */
	public double getMesesGarantia() {
		return mesesGarantia;
	}

	/**
	 * @param mesesGarantia
	 *            the mesesGarantia to set
	 */
	public void setMesesGarantia(double mesesGarantia) {
		this.mesesGarantia = mesesGarantia;
	}

	/**
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * @param peso
	 *            the peso to set
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}

	/**
	 * @return the volumen
	 */
	public double getVolumen() {
		return volumen;
	}

	/**
	 * @param volumen
	 *            the volumen to set
	 */
	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Producto [idPro=" + idPro + ", descripcion=" + descripcion + ", unidades=" + unidades + ", precioVenta="
				+ precioVenta + ", precioCompra=" + precioCompra + ", marca=" + marca + ", modelo=" + modelo
				+ ", serie=" + serie + ", ubicacion=" + ubicacion + ", partida=" + partida + ", fechaCompra="
				+ fechaCompra + ", enviar=" + enviar + ", vendible=" + vendible + ", mesesGarantia=" + mesesGarantia
				+ ", peso=" + peso + ", volumen=" + volumen + "]";
	}

}
