package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelUsuario;
import com.damian.pojo.Usuario;

@Component
public class ConverterUsuario {

	@Autowired
	private ConverterRellenaObjeto converterRellenaObjeto;

	public Usuario convertAll(ModelUsuario mu) {

		Usuario u = convert(mu);
		converterRellenaObjeto.rellenaUsuario(u, mu);

		return u;

	}

	public Usuario convert(ModelUsuario mu) {

		Usuario u = new Usuario();
		u.setIdUsr(mu.getIdUsr());
		u.setUsuario(mu.getUsuario());
		u.setClave(mu.getClave());
		u.setHabilitado(mu.isHabilitado());
		u.setFechaCreacion(mu.getFechaCreacion());
		u.setModificadoPor(mu.getModificadoPor());
		u.setFechaModificacion(mu.getFechaModificacion());

		return u;

	}

	public ModelUsuario convert(Usuario u) {

		ModelUsuario mu = new ModelUsuario();
		mu.setIdUsr(u.getIdUsr());
		mu.setUsuario(u.getUsuario());
		mu.setClave(u.getClave());
		mu.setHabilitado(u.isHabilitado());
		mu.setFechaCreacion(u.getFechaCreacion());
		mu.setModificadoPor(u.getModificadoPor());
		mu.setFechaModificacion(u.getFechaModificacion());

		return mu;

	}

}
