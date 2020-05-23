package com.damian.dao.model;

import java.sql.Timestamp;
import java.util.Date;

public class ModelProducto {

	private int idPro;
	private String nombreES;
	private String nombreEN;
	private String nombrePT;
	private String nombreFR;
	private String nombreIT;
	private String nombreGE;
	private String nombreCA;
	private String nombreEU;
	private int unidades;
	private double precioVenta;
	private double precioCompra;
	private String marca;
	private String modelo;
	private String serie;
	private String ubicacion;
	private String estado;
	private String partida;
	private Date fechaCompra;
	private boolean enviar;
	private boolean vendible;
	private double mesesGarantia;
	private double peso;
	private double volumen;
	private int idSub;
	private String modificadoPor;
	private Timestamp fechaModificacion;

	public ModelProducto() {

	}

	/**
	 * @param idPro
	 * @param nombreES
	 * @param nombreEN
	 * @param unidades
	 * @param precioVenta
	 * @param precioCompra
	 * @param marca
	 * @param modelo
	 * @param serie
	 * @param ubicacion
	 * @param estado
	 * @param partida
	 * @param fechaCompra
	 * @param enviar
	 * @param vendible
	 * @param mesesGarantia
	 * @param peso
	 * @param volumen
	 * @param idSub
	 */
	public ModelProducto(int idPro, String nombreES, String nombreEN, int unidades, double precioVenta,
			double precioCompra, String marca, String modelo, String serie, String ubicacion, String estado,
			String partida, Date fechaCompra, boolean enviar, boolean vendible, double mesesGarantia, double peso,
			double volumen, int idSub) {
		this.idPro = idPro;
		this.nombreES = nombreES;
		this.nombreEN = nombreEN;
		this.unidades = unidades;
		this.precioVenta = precioVenta;
		this.precioCompra = precioCompra;
		this.marca = marca;
		this.modelo = modelo;
		this.serie = serie;
		this.ubicacion = ubicacion;
		this.estado = estado;
		this.partida = partida;
		this.fechaCompra = fechaCompra;
		this.enviar = enviar;
		this.vendible = vendible;
		this.mesesGarantia = mesesGarantia;
		this.peso = peso;
		this.volumen = volumen;
		this.idSub = idSub;
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
	 * @return the nombreES
	 */
	public String getNombreES() {
		return nombreES;
	}

	/**
	 * @param nombreES
	 *            the nombreES to set
	 */
	public void setNombreES(String nombreES) {
		this.nombreES = nombreES;
	}

	/**
	 * @return the nombreEN
	 */
	public String getNombreEN() {
		return nombreEN;
	}

	/**
	 * @param nombreEN
	 *            the nombreEN to set
	 */
	public void setNombreEN(String nombreEN) {
		this.nombreEN = nombreEN;
	}

	/**
	 * @return the nombrePT
	 */
	public String getNombrePT() {
		return nombrePT;
	}

	/**
	 * @param nombrePT
	 *            the nombrePT to set
	 */
	public void setNombrePT(String nombrePT) {
		this.nombrePT = nombrePT;
	}

	/**
	 * @return the nombreFR
	 */
	public String getNombreFR() {
		return nombreFR;
	}

	/**
	 * @param nombreFR
	 *            the nombreFR to set
	 */
	public void setNombreFR(String nombreFR) {
		this.nombreFR = nombreFR;
	}

	/**
	 * @return the nombreIT
	 */
	public String getNombreIT() {
		return nombreIT;
	}

	/**
	 * @param nombreIT
	 *            the nombreIT to set
	 */
	public void setNombreIT(String nombreIT) {
		this.nombreIT = nombreIT;
	}

	/**
	 * @return the nombreGE
	 */
	public String getNombreGE() {
		return nombreGE;
	}

	/**
	 * @param nombreGE
	 *            the nombreGE to set
	 */
	public void setNombreGE(String nombreGE) {
		this.nombreGE = nombreGE;
	}

	/**
	 * @return the nombreCA
	 */
	public String getNombreCA() {
		return nombreCA;
	}

	/**
	 * @param nombreCA
	 *            the nombreCA to set
	 */
	public void setNombreCA(String nombreCA) {
		this.nombreCA = nombreCA;
	}

	/**
	 * @return the nombreEU
	 */
	public String getNombreEU() {
		return nombreEU;
	}

	/**
	 * @param nombreEU
	 *            the nombreEU to set
	 */
	public void setNombreEU(String nombreEU) {
		this.nombreEU = nombreEU;
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
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
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
		return "ModelProducto [idPro=" + idPro + ", nombreES=" + nombreES + ", nombreEN=" + nombreEN + ", nombrePT="
				+ nombrePT + ", nombreFR=" + nombreFR + ", nombreIT=" + nombreIT + ", nombreGE=" + nombreGE
				+ ", nombreCA=" + nombreCA + ", nombreEU=" + nombreEU + ", unidades=" + unidades + ", precioVenta="
				+ precioVenta + ", precioCompra=" + precioCompra + ", marca=" + marca + ", modelo=" + modelo
				+ ", serie=" + serie + ", ubicacion=" + ubicacion + ", estado=" + estado + ", partida=" + partida
				+ ", fechaCompra=" + fechaCompra + ", enviar=" + enviar + ", vendible=" + vendible + ", mesesGarantia="
				+ mesesGarantia + ", peso=" + peso + ", volumen=" + volumen + ", idSub=" + idSub + "]";
	}

}
