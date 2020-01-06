package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.EstadoDAO;
import com.damian.dao.FacturaDAO;
import com.damian.dao.model.ModelFacturaEstado;
import com.damian.pojo.Estado;
import com.damian.pojo.Factura;
import com.damian.pojo.FacturaEstado;

@Component
public class ConverterFacturaEstado {

	@Autowired
	private EstadoDAO estadoDAO;

	@Autowired
	private FacturaDAO facturaDAO;

	public FacturaEstado convertAll(ModelFacturaEstado mfe) {

		FacturaEstado fe = convert(mfe);
		fe.setEstado(estadoDAO.findByIdModel(mfe.getIdEst()));
		fe.setFactura(facturaDAO.findByIdModel(mfe.getIdFac()));

		return fe;

	}

	public FacturaEstado convert(ModelFacturaEstado mfe) {

		FacturaEstado fe = new FacturaEstado();
		fe.setId(mfe.getId());
		fe.setFecha(mfe.getFecha());
		fe.setCreadoPor(mfe.getCreadoPor());
		fe.setObservaciones(mfe.getObservaciones());
		Estado estado = new Estado();
		estado.setIdEst(mfe.getIdEst());
		fe.setEstado(estado);
		Factura factura = new Factura();
		factura.setIdFac(mfe.getIdFac());
		fe.setFactura(factura);

		return fe;

	}

	public ModelFacturaEstado convert(FacturaEstado fe) {

		ModelFacturaEstado mfe = new ModelFacturaEstado();
		if (fe.getEstado() != null) {
			mfe.setIdEst(fe.getEstado().getIdEst());
		}
		if (fe.getFactura() != null) {
			mfe.setIdFac(fe.getFactura().getIdFac());
		}
		mfe.setId(fe.getId());
		mfe.setFecha(fe.getFecha());
		mfe.setCreadoPor(fe.getCreadoPor());
		mfe.setObservaciones(fe.getObservaciones());

		return mfe;

	}

}
