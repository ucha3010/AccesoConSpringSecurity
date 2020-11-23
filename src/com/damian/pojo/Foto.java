package com.damian.pojo;

import java.sql.Timestamp;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class Foto {

	private int idFot;
	private CommonsMultipartFile archivo;
	private Usuario usuario;
	private Producto producto;
	private Empresa empresa;
	private EmpresaPropia empresaPropia;
	private Categoria categoria;
	private Subcategoria subcategoria;
	private Pais pais;
	private FormaPago formaPago;
	private Estado estado;
	private Rol rol;
	private Marca marca;
	private String nombre;
	private String ruta;
	private String descripcion;
	private long peso;
	private boolean principal;
	private boolean slide;
	private String extension;
	private Timestamp fechaCreacion;
	private String creadoPor;
	private String modificadoPor;
	private Timestamp fechaModificacion;

	public Foto() {

	}

	/**
	 * @param idFot
	 */
	public Foto(int idFot) {
		this.idFot = idFot;
	}

	/**
	 * @return the idFot
	 */
	public int getIdFot() {
		return idFot;
	}

	/**
	 * @param idFot
	 *            the idFot to set
	 */
	public void setIdFot(int idFot) {
		this.idFot = idFot;
	}

	/**
	 * @return the archivo
	 */
	public CommonsMultipartFile getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo
	 *            the archivo to set
	 */
	public void setArchivo(CommonsMultipartFile archivo) {
		this.archivo = archivo;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto
	 *            the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * @return the empresa
	 */
	public Empresa getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa
	 *            the empresa to set
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the empresaPropia
	 */
	public EmpresaPropia getEmpresaPropia() {
		return empresaPropia;
	}

	/**
	 * @param empresaPropia
	 *            the empresaPropia to set
	 */
	public void setEmpresaPropia(EmpresaPropia empresaPropia) {
		this.empresaPropia = empresaPropia;
	}

	/**
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria
	 *            the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the subcategoria
	 */
	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	/**
	 * @param subcategoria
	 *            the subcategoria to set
	 */
	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	/**
	 * @return the pais
	 */
	public Pais getPais() {
		return pais;
	}

	/**
	 * @param pais
	 *            the pais to set
	 */
	public void setPais(Pais pais) {
		this.pais = pais;
	}

	/**
	 * @return the formaPago
	 */
	public FormaPago getFormaPago() {
		return formaPago;
	}

	/**
	 * @param formaPago
	 *            the formaPago to set
	 */
	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * @return the rol
	 */
	public Rol getRol() {
		return rol;
	}

	/**
	 * @param rol
	 *            the rol to set
	 */
	public void setRol(Rol rol) {
		this.rol = rol;
	}

	/**
	 * @return the marca
	 */
	public Marca getMarca() {
		return marca;
	}

	/**
	 * @param marca
	 *            the marca to set
	 */
	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the ruta
	 */
	public String getRuta() {
		return ruta;
	}

	/**
	 * @param ruta
	 *            the ruta to set
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
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
	 * @return the peso
	 */
	public long getPeso() {
		return peso;
	}

	/**
	 * @param peso
	 *            the peso to set
	 */
	public void setPeso(long peso) {
		this.peso = peso;
	}

	/**
	 * @return the principal
	 */
	public boolean isPrincipal() {
		return principal;
	}

	/**
	 * @param principal
	 *            the principal to set
	 */
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	/**
	 * @return the slide
	 */
	public boolean isSlide() {
		return slide;
	}

	/**
	 * @param slide
	 *            the slide to set
	 */
	public void setSlide(boolean slide) {
		this.slide = slide;
	}

	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * @param extension
	 *            the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion
	 *            the fechaCreacion to set
	 */
	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the creadoPor
	 */
	public String getCreadoPor() {
		return creadoPor;
	}

	/**
	 * @param creadoPor
	 *            the creadoPor to set
	 */
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Foto [idFot=" + idFot + ", archivo=" + archivo + ", usuario=" + usuario + ", producto=" + producto
				+ ", empresa=" + empresa + ", empresaPropia=" + empresaPropia + ", categoria=" + categoria
				+ ", subcategoria=" + subcategoria + ", pais=" + pais + ", formaPago=" + formaPago + ", estado="
				+ estado + ", rol=" + rol + ", marca=" + marca + ", nombre=" + nombre + ", ruta=" + ruta
				+ ", descripcion=" + descripcion + ", peso=" + peso + ", principal=" + principal + ", slide=" + slide
				+ ", extension=" + extension + ", fechaCreacion=" + fechaCreacion + ", creadoPor=" + creadoPor
				+ ", modificadoPor=" + modificadoPor + ", fechaModificacion=" + fechaModificacion + "]";
	}

}
