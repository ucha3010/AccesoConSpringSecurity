package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelFactura;
import com.damian.pojo.Estado;
import com.damian.pojo.Factura;
import com.damian.pojo.FormaPago;

@Component
public class ConverterFactura {

	@Autowired
	private ConverterRellenaObjeto converterRellenaObjeto;

	public Factura convertAll(ModelFactura mf) {

		Factura f = convert(mf);
		converterRellenaObjeto.rellenaFactura(f, mf);

		return f;

	}

	public Factura convert(ModelFactura mf) {

		Factura f = new Factura();
		f.setIdFac(mf.getIdFac());
		f.setCompra(mf.isCompra());
		f.setIvaTotal(mf.getIvaTotal());
		f.setDescuentoTotal(mf.getDescuentoTotal());
		f.setFechaCompra(mf.getFechaCompra());
		f.setFechaEntrega(mf.getFechaEntrega());
		f.setDireccionEntrega(mf.getDireccionEntrega());
		f.setObservaciones(mf.getObservaciones());
		f.setCreadoPor(mf.getCreadoPor());
		Estado estado = new Estado();
		estado.setIdEst(mf.getIdEst());
		f.setEstado(estado);
		FormaPago formaPago = new FormaPago();
		formaPago.setIdFor(mf.getIdFor());
		f.setFormaPago(formaPago);

		return f;

	}

	public ModelFactura convert(Factura f) {

		ModelFactura mf = new ModelFactura();
		mf.setIdFac(f.getIdFac());
		mf.setCompra(f.isCompra());
		mf.setIvaTotal(f.getIvaTotal());
		mf.setDescuentoTotal(f.getDescuentoTotal());
		mf.setFechaCompra(f.getFechaCompra());
		mf.setFechaEntrega(f.getFechaEntrega());
		if (f.getEstado() != null) {
			mf.setIdEst(f.getEstado().getIdEst());
		}
		mf.setDireccionEntrega(f.getDireccionEntrega());
		mf.setObservaciones(f.getObservaciones());
		mf.setCreadoPor(f.getCreadoPor());
		if (f.getFormaPago() != null) {
			mf.setIdFor(f.getFormaPago().getIdFor());
		}

		return mf;

	}

}
