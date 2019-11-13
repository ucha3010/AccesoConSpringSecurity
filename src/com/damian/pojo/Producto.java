package com.damian.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 130820142307L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPro", unique = true, nullable = false)
	private int idPro;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "unidades")
	private int unidades;

	@Column(name = "precioVenta")
	private double precioVenta;

	@Column(name = "precioCompra")
	private double precioCompra;

	@Column(name = "marca")
	private String marca;

	@Column(name = "modelo")
	private String modelo;

	@Column(name = "serie")
	private String serie;

	/**
	 * Ubicación dentro del almacén
	 */
	@Column(name = "ubicacion")
	private String ubicacion;

	/**
	 * nuevo, 90%, 80%...
	 */
	@Column(name = "partida")
	private String partida;

	@Column(name = "fechaCompra")
	private Date fechaCompra;

	@Column(name = "enviar")
	private boolean enviar;

	/**
	 * Vendible o de uso interno
	 */
	@Column(name = "vendible")
	private boolean vendible;

	@Column(name = "mesesGarantia")
	private double mesesGarantia;

	@Column(name = "peso")
	private double peso;

	@Column(name = "volumen")
	private double volumen;

}
