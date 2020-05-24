package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelProducto;
import com.damian.pojo.Producto;
import com.damian.pojo.Subcategoria;

@Component
public class ConverterProducto {

	@Autowired
	private ConverterRellenaObjeto converterRellenaObjeto;

	public Producto convertAll(ModelProducto mp) {

		Producto p = convert(mp);
		converterRellenaObjeto.rellenaProducto(p, mp);

		return p;

	}

	public Producto convert(ModelProducto mp) {

		Producto p = new Producto();
		p.setIdPro(mp.getIdPro());
		p.setNombreES(mp.getNombreES());
		p.setNombreEN(mp.getNombreEN());
		p.setNombrePT(mp.getNombrePT());
		p.setNombreFR(mp.getNombreFR());
		p.setNombreIT(mp.getNombreIT());
		p.setNombreGE(mp.getNombreGE());
		p.setNombreCA(mp.getNombreCA());
		p.setNombreEU(mp.getNombreEU());
		p.setUnidades(mp.getUnidades());
		p.setPrecioVenta(mp.getPrecioVenta());
		p.setPrecioCompra(mp.getPrecioCompra());
		p.setMarca(mp.getMarca());
		p.setModelo(mp.getModelo());
		p.setSerie(mp.getSerie());
		p.setUbicacion(mp.getUbicacion());
		p.setEstado(mp.getEstado());
		p.setPartida(mp.getPartida());
		p.setFechaCompra(mp.getFechaCompra());
		p.setEnviar(mp.isEnviar());
		p.setVendible(mp.isVendible());
		p.setMesesGarantia(mp.getMesesGarantia());
		p.setPeso(mp.getPeso());
		p.setVolumen(mp.getVolumen());
		Subcategoria subcategoria = new Subcategoria();
		subcategoria.setIdSub(mp.getIdSub());
		p.setSubcategoria(subcategoria);
		p.setModificadoPor(mp.getModificadoPor());
		p.setFechaModificacion(mp.getFechaModificacion());

		return p;

	}

	public ModelProducto convert(Producto p) {

		ModelProducto mp = new ModelProducto();
		mp.setIdPro(p.getIdPro());
		if (p.getNombreES() != null) {
			mp.setNombreES(p.getNombreES());
		} else {
			mp.setNombreES("Descripción producto no ingresado");
		}
		if (p.getNombreEN() != null) {
			mp.setNombreEN(p.getNombreEN());
		} else {
			mp.setNombreEN("Product description didn't write");
		}
		mp.setNombrePT(p.getNombrePT());
		mp.setNombreFR(p.getNombreFR());
		mp.setNombreIT(p.getNombreIT());
		mp.setNombreGE(p.getNombreGE());
		mp.setNombreCA(p.getNombreCA());
		mp.setNombreEU(p.getNombreEU());
		mp.setUnidades(p.getUnidades());
		mp.setPrecioVenta(p.getPrecioVenta());
		mp.setPrecioCompra(p.getPrecioCompra());
		mp.setMarca(p.getMarca());
		mp.setModelo(p.getModelo());
		mp.setSerie(p.getSerie());
		mp.setUbicacion(p.getUbicacion());
		mp.setEstado(p.getEstado());
		mp.setPartida(p.getPartida());
		mp.setFechaCompra(p.getFechaCompra());
		mp.setEnviar(p.isEnviar());
		mp.setVendible(p.isVendible());
		mp.setMesesGarantia(p.getMesesGarantia());
		mp.setPeso(p.getPeso());
		mp.setVolumen(p.getVolumen());
		if (p.getSubcategoria() != null) {
			mp.setIdSub(p.getSubcategoria().getIdSub());
		}
		mp.setModificadoPor(p.getModificadoPor());
		mp.setFechaModificacion(p.getFechaModificacion());

		return mp;

	}

}
