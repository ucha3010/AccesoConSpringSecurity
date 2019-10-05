package com.damian.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 130820142307L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEmp", unique = true, nullable = false)
	private int idEmp;

	@Column(name = "nombreComercial")
	private String nombreComercial;

	@Column(name = "tipoSociedad")
	private String tipoSociedad;

	@Column(name = "actividad")
	private String actividad;

	@Column(name = "cif")
	private String cif;

	@Column(name = "email")
	private String email;

	@Column(name = "paginaWeb")
	private String paginaWeb;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "fax")
	private String fax;

	@Column(name = "observaciones")	
	private String observaciones;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.empresa")
	private List<UsuarioEmpresa> usuarioEmpresa = new ArrayList<UsuarioEmpresa>();

	@OneToMany(mappedBy = "empresa")
	private List<DireccionEmpresa> direccionesEmpresa = new ArrayList<DireccionEmpresa>();
	
	public Empresa() {
		
	}

	public Empresa(int idEmp, String nombreComercial, String tipoSociedad, String actividad, String cif, String email,
			String paginaWeb, String telefono, String fax, String observaciones) {
		this.idEmp = idEmp;
		this.nombreComercial = nombreComercial;
		this.tipoSociedad = tipoSociedad;
		this.actividad = actividad;
		this.cif = cif;
		this.email = email;
		this.paginaWeb = paginaWeb;
		this.telefono = telefono;
		this.fax = fax;
		this.observaciones = observaciones;
	}

	public Empresa(int idEmp, String nombreComercial, String tipoSociedad, String actividad, String cif, String email,
			String paginaWeb, String telefono, String fax, String observaciones, List<UsuarioEmpresa> usuarioEmpresa,
			List<DireccionEmpresa> direccionesEmpresa) {
		this.idEmp = idEmp;
		this.nombreComercial = nombreComercial;
		this.tipoSociedad = tipoSociedad;
		this.actividad = actividad;
		this.cif = cif;
		this.email = email;
		this.paginaWeb = paginaWeb;
		this.telefono = telefono;
		this.fax = fax;
		this.observaciones = observaciones;
		this.usuarioEmpresa = usuarioEmpresa;
		this.direccionesEmpresa = direccionesEmpresa;
	}

	public int getIdEmp() {
		return idEmp;
	}

	public void setIdEmp(int idEmp) {
		this.idEmp = idEmp;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getTipoSociedad() {
		return tipoSociedad;
	}

	public void setTipoSociedad(String tipoSociedad) {
		this.tipoSociedad = tipoSociedad;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getPaginaWeb() {
		return paginaWeb;
	}

	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UsuarioEmpresa> getUsuarioEmpresa() {
		return usuarioEmpresa;
	}

	public void setUsuarioEmpresa(List<UsuarioEmpresa> usuarioEmpresa) {
		this.usuarioEmpresa = usuarioEmpresa;
	}

	public List<DireccionEmpresa> getDireccionesEmpresa() {
		return direccionesEmpresa;
	}

	public void setDireccionesEmpresa(List<DireccionEmpresa> direccionesEmpresa) {
		this.direccionesEmpresa = direccionesEmpresa;
	}

	@Override
	public String toString() {
		return "Empresa [idEmp=" + idEmp + ", nombreComercial=" + nombreComercial + ", tipoSociedad=" + tipoSociedad
				+ ", actividad=" + actividad + ", cif=" + cif + ", email=" + email + ", paginaWeb=" + paginaWeb
				+ ", telefono=" + telefono + ", fax=" + fax + ", observaciones=" + observaciones + "]";
	}

}
