package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.DatosPersonalesDAO;
import com.damian.dao.EmpresaDAO;
import com.damian.dao.UsuarioDAO;
import com.damian.dao.model.ModelUsuarioEmpresa;
import com.damian.pojo.Usuario;
import com.damian.pojo.UsuarioEmpresa;

@Component
public class ConverterUsuarioEmpresa {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private EmpresaDAO empresaDAO;
	
	@Autowired
	private DatosPersonalesDAO datosPersonalesDAO;

	public UsuarioEmpresa convertAll(ModelUsuarioEmpresa mue) {

		UsuarioEmpresa ue = convert(mue);
		Usuario u = usuarioDAO.findByIdModel(mue.getIdUsr());
		u.setDatosPersonales(datosPersonalesDAO.findByUsrIdModel(u.getIdUsr()));
		ue.setUsuario(u);
		ue.setEmpresa(empresaDAO.findByIdModel(mue.getIdEmp()));

		return ue;

	}

	public UsuarioEmpresa convert(ModelUsuarioEmpresa mue) {

		UsuarioEmpresa ue = new UsuarioEmpresa();
		ue.setFechaCreacion(mue.getFechaCreacion());
		ue.setCreadoPor(mue.getCreadoPor());

		return ue;

	}

	public ModelUsuarioEmpresa convert(UsuarioEmpresa ue) {

		ModelUsuarioEmpresa mue = new ModelUsuarioEmpresa();
		mue.setIdUsr(ue.getUsuario().getIdUsr());
		mue.setIdEmp(ue.getEmpresa().getIdEmp());
		mue.setFechaCreacion(ue.getFechaCreacion());
		mue.setCreadoPor(ue.getCreadoPor());

		return mue;

	}

}
