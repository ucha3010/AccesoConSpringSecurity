package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;

import com.damian.dao.model.ModelUsuarioEmpresa;
import com.damian.pojo.UsuarioEmpresa;
import com.damian.service.EmpresaService;
import com.damian.service.UsuarioService;

public class ConverterUsuarioEmpresa {

	@Autowired
	private static UsuarioService usuarioService;

	@Autowired
	private static EmpresaService empresaService;

	public static UsuarioEmpresa convert(ModelUsuarioEmpresa mue) {

		UsuarioEmpresa ue = new UsuarioEmpresa();
		ue.setUsuario(usuarioService.findById(mue.getIdUsr()));
		ue.setEmpresa(empresaService.findById(mue.getIdEmp()));
		ue.setFechaCreacion(mue.getFechaCreacion());
		ue.setCreadoPor(mue.getCreadoPor());

		return ue;

	}

	public static ModelUsuarioEmpresa convert(UsuarioEmpresa ue) {

		ModelUsuarioEmpresa mue = new ModelUsuarioEmpresa();
		mue.setIdUsr(ue.getUsuario().getIdUsr());
		mue.setIdEmp(ue.getEmpresa().getIdEmp());
		mue.setFechaCreacion(ue.getFechaCreacion());
		mue.setCreadoPor(ue.getCreadoPor());

		return mue;

	}

}
