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
		f.setTotalSinIvaEnvDescfac(mf.getTotalSinIvaEnvDescfac());
		f.setDescuentoTotal(mf.getDescuentoTotal());
		f.setDescuentoImporteProductos(mf.getDescuentoImporteProductos());
		f.setDescuentoImporteFactura(mf.getDescuentoImporteFactura());
		f.setDescuentoImporteTotal(mf.getDescuentoImporteTotal());
		f.setImporteEnvioSinIva(mf.getImporteEnvioSinIva());
		f.setEnvioIvaPor(mf.getEnvioIvaPor());
		f.setEnvioIvaImp(mf.getEnvioIvaImp());
		f.setProductosIvaImp(mf.getProductosIvaImp());
		f.setTotalSinIvaConDescfac(mf.getTotalSinIvaConDescfac());
		f.setIvaTotal(mf.getIvaTotal());
		f.setIvaImporteTotal(mf.getIvaImporteTotal());
		f.setImporteTotal(mf.getImporteTotal());
		f.setFechaCompra(mf.getFechaCompra());
		f.setFechaEntrega(mf.getFechaEntrega());
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
		f.setModificadoPor(mf.getModificadoPor());
		f.setFechaModificacion(mf.getFechaModificacion());

		return f;

	}

	public ModelFactura convert(Factura f) {

		ModelFactura mf = new ModelFactura();
		mf.setIdFac(f.getIdFac());
		mf.setCompra(f.isCompra());
		mf.setTotalSinIvaEnvDescfac(f.getTotalSinIvaEnvDescfac());
		mf.setDescuentoTotal(f.getDescuentoTotal());
		mf.setDescuentoImporteProductos(f.getDescuentoImporteProductos());
		mf.setDescuentoImporteFactura(f.getDescuentoImporteFactura());
		mf.setDescuentoImporteTotal(f.getDescuentoImporteTotal());
		mf.setImporteEnvioSinIva(f.getImporteEnvioSinIva());
		mf.setEnvioIvaPor(f.getEnvioIvaPor());
		mf.setEnvioIvaImp(f.getEnvioIvaImp());
		mf.setProductosIvaImp(f.getProductosIvaImp());
		mf.setTotalSinIvaConDescfac(f.getTotalSinIvaConDescfac());
		mf.setIvaTotal(f.getIvaTotal());
		mf.setIvaImporteTotal(f.getIvaImporteTotal());
		mf.setImporteTotal(f.getImporteTotal());
		mf.setFechaCompra(f.getFechaCompra());
		mf.setFechaEntrega(f.getFechaEntrega());
		if (f.getEstado() != null) {
			mf.setIdEst(f.getEstado().getIdEst());
		}
		mf.setObservaciones(f.getObservaciones());
		mf.setCreadoPor(f.getCreadoPor());
		if (f.getFormaPago() != null) {
			mf.setIdFor(f.getFormaPago().getIdFor());
		}
		if (f.getCuota() != null) {
			mf.setIdCuo(f.getCuota().getIdCuo());
		}
		mf.setModificadoPor(f.getModificadoPor());
		mf.setFechaModificacion(f.getFechaModificacion());

		return mf;

	}

}
