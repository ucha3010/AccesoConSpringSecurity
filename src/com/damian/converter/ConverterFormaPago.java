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
		f.setNombre(mf.getNombre());

		return f;

	}

	public ModelFormaPago convert(FormaPago f) {

		ModelFormaPago mf = new ModelFormaPago();
		mf.setIdFor(f.getIdFor());
		mf.setNombre(f.getNombre());

		return mf;

	}

}
