package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelFactura;
import com.damian.pojo.Cuota;
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
		f.setIvaImporteTotal(mf.getIvaImporteTotal());
		f.setDescuentoTotal(mf.getDescuentoTotal());
		f.setDescuentoImporteTotal(mf.getDescuentoImporteTotal());
		f.setImporteTotal(mf.getImporteTotal());
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
		Cuota cuota = new Cuota();
		cuota.setIdCuo(mf.getIdCuo());
		f.setCuota(cuota);
		f.setNumeroCuota(mf.getNumeroCuota());
		f.setInteresCuotaImporte(mf.getInteresCuotaImporte());
		f.setImporteCuotaTotal(mf.getImporteCuotaTotal());
		f.setCuotaConIva(mf.getCuotaConIva());
		f.setCuotaSinIva(mf.getCuotaSinIva());

		return f;

	}

	public ModelFactura convert(Factura f) {

		ModelFactura mf = new ModelFactura();
		mf.setIdFac(f.getIdFac());
		mf.setCompra(f.isCompra());
		mf.setIvaTotal(f.getIvaTotal());
		mf.setIvaImporteTotal(f.getIvaImporteTotal());
		mf.setDescuentoTotal(f.getDescuentoTotal());
		mf.setDescuentoImporteTotal(f.getDescuentoImporteTotal());
		mf.setImporteTotal(f.getImporteTotal());
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
		if (f.getCuota() != null) {
			mf.setIdCuo(f.getCuota().getIdCuo());
		}
		mf.setNumeroCuota(f.getNumeroCuota());
		mf.setInteresCuotaImporte(f.getInteresCuotaImporte());
		mf.setImporteCuotaTotal(f.getImporteCuotaTotal());
		mf.setCuotaConIva(f.getCuotaConIva());
		mf.setCuotaSinIva(f.getCuotaSinIva());

		return mf;

	}

}
