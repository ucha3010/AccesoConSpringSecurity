package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelFormaPago;
import com.damian.pojo.FormaPago;

@Component
public class ConverterFormaPago {

	@Autowired
	private ConverterRellenaObjeto converterRellenaObjeto;

	public FormaPago convertAll(ModelFormaPago mf) {

		FormaPago f = convert(mf);
		converterRellenaObjeto.rellenaFormaPago(f, mf);

		return f;

	}

	public FormaPago convert(ModelFormaPago mf) {

		FormaPago f = new FormaPago();
		f.setIdFor(mf.getIdFor());
		f.setNombreES(mf.getNombreES());
		f.setNombreEN(mf.getNombreEN());
		f.setNombrePT(mf.getNombrePT());
		f.setNombreFR(mf.getNombreFR());
		f.setNombreIT(mf.getNombreIT());
		f.setNombreGE(mf.getNombreGE());
		f.setNombreCA(mf.getNombreCA());
		f.setNombreEU(mf.getNombreEU());
		f.setModificadoPor(mf.getModificadoPor());
		f.setFechaModificacion(mf.getFechaModificacion());

		return f;

	}

	public ModelFormaPago convert(FormaPago f) {

		ModelFormaPago mf = new ModelFormaPago();
		mf.setIdFor(f.getIdFor());
		mf.setNombreES(f.getNombreES());
		mf.setNombreEN(f.getNombreEN());
		mf.setNombrePT(f.getNombrePT());
		mf.setNombreFR(f.getNombreFR());
		mf.setNombreIT(f.getNombreIT());
		mf.setNombreGE(f.getNombreGE());
		mf.setNombreCA(f.getNombreCA());
		mf.setNombreEU(f.getNombreEU());
		mf.setModificadoPor(f.getModificadoPor());
		mf.setFechaModificacion(f.getFechaModificacion());

		return mf;

	}

}
