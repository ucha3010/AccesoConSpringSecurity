package com.damian.pojo.front;

import java.util.Date;
import java.util.List;

public class ImpresionFactura {

	// title
	private Date fechaCompra;
	private int idFac;

	// page header
	private String entrega_nombre;
	private String entrega_direccion1;
	private String entrega_direccion2;
	private String entrega_cp;
	private String entrega_ciudad;
	private String entrega_provincia;
	private String entrega_pais;
	private String entrega_telefono;
	private String factura_nombre;
	private String factura_direccion1;
	private String factura_direccion2;
	private String factura_cp;
	private String factura_ciudad;
	private String factura_provincia;
	private String factura_pais;
	private String factura_telefono;

	// column header detail
	private List<ImpresionProducto> impresionProductoList;

	// column footer
	private String formaPago_nombre;
	private String factura_observaciones;
	private double totalSinIvaEnvDescfac;
	private double importeEnvioSinIva;
	private double descuentoTotal;
	private double descuentoImporteFactura;
	private double descuentoImporteTotal;
	private double totalSinIvaConDescfac;
	private double envioIvaImp;
	private double productosIvaImp;
	private double ivaImporteTotal;
	private double importeTotal;

	// cuotas
	private boolean hayCuotas;
	private int idCuo;
	private double comisionAperturaPor;
	private double interesPor;

	// page footer
	private String primerRenglon;
	private String segundoRenglon;
	private String tercerRenglon;

	private String jrxml;

	public ImpresionFactura() {
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
	 * @return the idFac
	 */
	public int getIdFac() {
		return idFac;
	}

	/**
	 * @param idFac
	 *            the idFac to set
	 */
	public void setIdFac(int idFac) {
		this.idFac = idFac;
	}

	/**
	 * @return the entrega_nombre
	 */
	public String getEntrega_nombre() {
		return entrega_nombre;
	}

	/**
	 * @param entrega_nombre
	 *            the entrega_nombre to set
	 */
	public void setEntrega_nombre(String entrega_nombre) {
		this.entrega_nombre = entrega_nombre;
	}

	/**
	 * @return the entrega_direccion1
	 */
	public String getEntrega_direccion1() {
		return entrega_direccion1;
	}

	/**
	 * @param entrega_direccion1
	 *            the entrega_direccion1 to set
	 */
	public void setEntrega_direccion1(String entrega_direccion1) {
		this.entrega_direccion1 = entrega_direccion1;
	}

	/**
	 * @return the entrega_direccion2
	 */
	public String getEntrega_direccion2() {
		return entrega_direccion2;
	}

	/**
	 * @param entrega_direccion2
	 *            the entrega_direccion2 to set
	 */
	public void setEntrega_direccion2(String entrega_direccion2) {
		this.entrega_direccion2 = entrega_direccion2;
	}

	/**
	 * @return the entrega_cp
	 */
	public String getEntrega_cp() {
		return entrega_cp;
	}

	/**
	 * @param entrega_cp
	 *            the entrega_cp to set
	 */
	public void setEntrega_cp(String entrega_cp) {
		this.entrega_cp = entrega_cp;
	}

	/**
	 * @return the entrega_ciudad
	 */
	public String getEntrega_ciudad() {
		return entrega_ciudad;
	}

	/**
	 * @param entrega_ciudad
	 *            the entrega_ciudad to set
	 */
	public void setEntrega_ciudad(String entrega_ciudad) {
		this.entrega_ciudad = entrega_ciudad;
	}

	/**
	 * @return the entrega_provincia
	 */
	public String getEntrega_provincia() {
		return entrega_provincia;
	}

	/**
	 * @param entrega_provincia
	 *            the entrega_provincia to set
	 */
	public void setEntrega_provincia(String entrega_provincia) {
		this.entrega_provincia = entrega_provincia;
	}

	/**
	 * @return the entrega_pais
	 */
	public String getEntrega_pais() {
		return entrega_pais;
	}

	/**
	 * @param entrega_pais
	 *            the entrega_pais to set
	 */
	public void setEntrega_pais(String entrega_pais) {
		this.entrega_pais = entrega_pais;
	}

	/**
	 * @return the entrega_telefono
	 */
	public String getEntrega_telefono() {
		return entrega_telefono;
	}

	/**
	 * @param entrega_telefono
	 *            the entrega_telefono to set
	 */
	public void setEntrega_telefono(String entrega_telefono) {
		this.entrega_telefono = entrega_telefono;
	}

	/**
	 * @return the factura_nombre
	 */
	public String getFactura_nombre() {
		return factura_nombre;
	}

	/**
	 * @param factura_nombre
	 *            the factura_nombre to set
	 */
	public void setFactura_nombre(String factura_nombre) {
		this.factura_nombre = factura_nombre;
	}

	/**
	 * @return the factura_direccion1
	 */
	public String getFactura_direccion1() {
		return factura_direccion1;
	}

	/**
	 * @param factura_direccion1
	 *            the factura_direccion1 to set
	 */
	public void setFactura_direccion1(String factura_direccion1) {
		this.factura_direccion1 = factura_direccion1;
	}

	/**
	 * @return the factura_direccion2
	 */
	public String getFactura_direccion2() {
		return factura_direccion2;
	}

	/**
	 * @param factura_direccion2
	 *            the factura_direccion2 to set
	 */
	public void setFactura_direccion2(String factura_direccion2) {
		this.factura_direccion2 = factura_direccion2;
	}

	/**
	 * @return the factura_cp
	 */
	public String getFactura_cp() {
		return factura_cp;
	}

	/**
	 * @param factura_cp
	 *            the factura_cp to set
	 */
	public void setFactura_cp(String factura_cp) {
		this.factura_cp = factura_cp;
	}

	/**
	 * @return the factura_ciudad
	 */
	public String getFactura_ciudad() {
		return factura_ciudad;
	}

	/**
	 * @param factura_ciudad
	 *            the factura_ciudad to set
	 */
	public void setFactura_ciudad(String factura_ciudad) {
		this.factura_ciudad = factura_ciudad;
	}

	/**
	 * @return the factura_provincia
	 */
	public String getFactura_provincia() {
		return factura_provincia;
	}

	/**
	 * @param factura_provincia
	 *            the factura_provincia to set
	 */
	public void setFactura_provincia(String factura_provincia) {
		this.factura_provincia = factura_provincia;
	}

	/**
	 * @return the factura_pais
	 */
	public String getFactura_pais() {
		return factura_pais;
	}

	/**
	 * @param factura_pais
	 *            the factura_pais to set
	 */
	public void setFactura_pais(String factura_pais) {
		this.factura_pais = factura_pais;
	}

	/**
	 * @return the factura_telefono
	 */
	public String getFactura_telefono() {
		return factura_telefono;
	}

	/**
	 * @param factura_telefono
	 *            the factura_telefono to set
	 */
	public void setFactura_telefono(String factura_telefono) {
		this.factura_telefono = factura_telefono;
	}

	/**
	 * @return the impresionProductoList
	 */
	public List<ImpresionProducto> getImpresionProductoList() {
		return impresionProductoList;
	}

	/**
	 * @param impresionProductoList
	 *            the impresionProductoList to set
	 */
	public void setImpresionProductoList(List<ImpresionProducto> impresionProductoList) {
		this.impresionProductoList = impresionProductoList;
	}

	/**
	 * @return the formaPago_nombre
	 */
	public String getFormaPago_nombre() {
		return formaPago_nombre;
	}

	/**
	 * @param formaPago_nombre
	 *            the formaPago_nombre to set
	 */
	public void setFormaPago_nombre(String formaPago_nombre) {
		this.formaPago_nombre = formaPago_nombre;
	}

	/**
	 * @return the factura_observaciones
	 */
	public String getFactura_observaciones() {
		return factura_observaciones;
	}

	/**
	 * @param factura_observaciones
	 *            the factura_observaciones to set
	 */
	public void setFactura_observaciones(String factura_observaciones) {
		this.factura_observaciones = factura_observaciones;
	}

	/**
	 * @return the totalSinIvaEnvDescfac
	 */
	public double getTotalSinIvaEnvDescfac() {
		return totalSinIvaEnvDescfac;
	}

	/**
	 * @param totalSinIvaEnvDescfac
	 *            the totalSinIvaEnvDescfac to set
	 */
	public void setTotalSinIvaEnvDescfac(double totalSinIvaEnvDescfac) {
		this.totalSinIvaEnvDescfac = totalSinIvaEnvDescfac;
	}

	/**
	 * @return the importeEnvioSinIva
	 */
	public double getImporteEnvioSinIva() {
		return importeEnvioSinIva;
	}

	/**
	 * @param importeEnvioSinIva
	 *            the importeEnvioSinIva to set
	 */
	public void setImporteEnvioSinIva(double importeEnvioSinIva) {
		this.importeEnvioSinIva = importeEnvioSinIva;
	}

	/**
	 * @return the descuentoTotal
	 */
	public double getDescuentoTotal() {
		return descuentoTotal;
	}

	/**
	 * @param descuentoTotal
	 *            the descuentoTotal to set
	 */
	public void setDescuentoTotal(double descuentoTotal) {
		this.descuentoTotal = descuentoTotal;
	}

	/**
	 * @return the descuentoImporteFactura
	 */
	public double getDescuentoImporteFactura() {
		return descuentoImporteFactura;
	}

	/**
	 * @param descuentoImporteFactura
	 *            the descuentoImporteFactura to set
	 */
	public void setDescuentoImporteFactura(double descuentoImporteFactura) {
		this.descuentoImporteFactura = descuentoImporteFactura;
	}

	/**
	 * @return the descuentoImporteTotal
	 */
	public double getDescuentoImporteTotal() {
		return descuentoImporteTotal;
	}

	/**
	 * @param descuentoImporteTotal
	 *            the descuentoImporteTotal to set
	 */
	public void setDescuentoImporteTotal(double descuentoImporteTotal) {
		this.descuentoImporteTotal = descuentoImporteTotal;
	}

	/**
	 * @return the totalSinIvaConDescfac
	 */
	public double getTotalSinIvaConDescfac() {
		return totalSinIvaConDescfac;
	}

	/**
	 * @param totalSinIvaConDescfac
	 *            the totalSinIvaConDescfac to set
	 */
	public void setTotalSinIvaConDescfac(double totalSinIvaConDescfac) {
		this.totalSinIvaConDescfac = totalSinIvaConDescfac;
	}

	/**
	 * @return the envioIvaImp
	 */
	public double getEnvioIvaImp() {
		return envioIvaImp;
	}

	/**
	 * @param envioIvaImp
	 *            the envioIvaImp to set
	 */
	public void setEnvioIvaImp(double envioIvaImp) {
		this.envioIvaImp = envioIvaImp;
	}

	/**
	 * @return the productosIvaImp
	 */
	public double getProductosIvaImp() {
		return productosIvaImp;
	}

	/**
	 * @param productosIvaImp
	 *            the productosIvaImp to set
	 */
	public void setProductosIvaImp(double productosIvaImp) {
		this.productosIvaImp = productosIvaImp;
	}

	/**
	 * @return the ivaImporteTotal
	 */
	public double getIvaImporteTotal() {
		return ivaImporteTotal;
	}

	/**
	 * @param ivaImporteTotal
	 *            the ivaImporteTotal to set
	 */
	public void setIvaImporteTotal(double ivaImporteTotal) {
		this.ivaImporteTotal = ivaImporteTotal;
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
	 * @return the hayCuotas
	 */
	public boolean isHayCuotas() {
		return hayCuotas;
	}

	/**
	 * @param hayCuotas
	 *            the hayCuotas to set
	 */
	public void setHayCuotas(boolean hayCuotas) {
		this.hayCuotas = hayCuotas;
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
	 * @return the primerRenglon
	 */
	public String getPrimerRenglon() {
		return primerRenglon;
	}

	/**
	 * @param primerRenglon
	 *            the primerRenglon to set
	 */
	public void setPrimerRenglon(String primerRenglon) {
		this.primerRenglon = primerRenglon;
	}

	/**
	 * @return the segundoRenglon
	 */
	public String getSegundoRenglon() {
		return segundoRenglon;
	}

	/**
	 * @param segundoRenglon
	 *            the segundoRenglon to set
	 */
	public void setSegundoRenglon(String segundoRenglon) {
		this.segundoRenglon = segundoRenglon;
	}

	/**
	 * @return the tercerRenglon
	 */
	public String getTercerRenglon() {
		return tercerRenglon;
	}

	/**
	 * @param tercerRenglon
	 *            the tercerRenglon to set
	 */
	public void setTercerRenglon(String tercerRenglon) {
		this.tercerRenglon = tercerRenglon;
	}

	/**
	 * @return the jrxml
	 */
	public String getJrxml() {
		return jrxml;
	}

	/**
	 * @param jrxml
	 *            the jrxml to set
	 */
	public void setJrxml(String jrxml) {
		this.jrxml = jrxml;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ImpresionFactura [fechaCompra=" + fechaCompra + ", idFac=" + idFac + ", entrega_nombre="
				+ entrega_nombre + ", entrega_direccion1=" + entrega_direccion1 + ", entrega_direccion2="
				+ entrega_direccion2 + ", entrega_cp=" + entrega_cp + ", entrega_ciudad=" + entrega_ciudad
				+ ", entrega_provincia=" + entrega_provincia + ", entrega_pais=" + entrega_pais + ", entrega_telefono="
				+ entrega_telefono + ", factura_nombre=" + factura_nombre + ", factura_direccion1=" + factura_direccion1
				+ ", factura_direccion2=" + factura_direccion2 + ", factura_cp=" + factura_cp + ", factura_ciudad="
				+ factura_ciudad + ", factura_provincia=" + factura_provincia + ", factura_pais=" + factura_pais
				+ ", factura_telefono=" + factura_telefono + ", impresionProductoList=" + impresionProductoList
				+ ", formaPago_nombre=" + formaPago_nombre + ", factura_observaciones=" + factura_observaciones
				+ ", totalSinIvaEnvDescfac=" + totalSinIvaEnvDescfac + ", importeEnvioSinIva=" + importeEnvioSinIva
				+ ", descuentoTotal=" + descuentoTotal + ", descuentoImporteFactura=" + descuentoImporteFactura
				+ ", descuentoImporteTotal=" + descuentoImporteTotal + ", totalSinIvaConDescfac="
				+ totalSinIvaConDescfac + ", envioIvaImp=" + envioIvaImp + ", productosIvaImp=" + productosIvaImp
				+ ", ivaImporteTotal=" + ivaImporteTotal + ", importeTotal=" + importeTotal + ", hayCuotas=" + hayCuotas
				+ ", idCuo=" + idCuo + ", comisionAperturaPor=" + comisionAperturaPor + ", interesPor=" + interesPor
				+ ", primerRenglon=" + primerRenglon + ", segundoRenglon=" + segundoRenglon + ", tercerRenglon="
				+ tercerRenglon + ", jrxml=" + jrxml + "]";
	}

}
