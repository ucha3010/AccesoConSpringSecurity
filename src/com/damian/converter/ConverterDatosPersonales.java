package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;

import com.damian.dao.model.ModelDatosPersonales;
import com.damian.pojo.DatosPersonales;
import com.damian.service.DireccionService;
import com.damian.service.UsuarioService;

public class ConverterDatosPersonales {
	
	@Autowired
	private static UsuarioService usuarioService;
	
	@Autowired
	private static DireccionService direccionService;
	
	public static DatosPersonales convert(ModelDatosPersonales mdp) {
		
		DatosPersonales dp = new DatosPersonales();
		dp.setIdDatosPers(mdp.getIdDatosPers());
		dp.setNombre(mdp.getNombre());
		dp.setApellido1(mdp.getApellido1());
		dp.setApellido2(mdp.getApellido2());
		dp.setSexo(mdp.getSexo());
		dp.setFechaNacimiento(mdp.getFechaNacimiento());
		dp.setNacionalidad(mdp.getNacionalidad());
		dp.setDni(mdp.getDni());
		dp.setEmail(mdp.getEmail());
		dp.setTelefono(mdp.getTelefono());
		dp.setObservaciones(mdp.getObservaciones());
		dp.setUsuario(usuarioService.findById(mdp.getDatospersonales_idUsr()));
		dp.setDirecciones(direccionService.findListFromUsuario(mdp.getDatospersonales_idUsr()));
		
		return dp;
		
	}
	
	public static ModelDatosPersonales convert(DatosPersonales dp) {
		
		ModelDatosPersonales mdp = new ModelDatosPersonales();
		mdp.setIdDatosPers(dp.getIdDatosPers());
		mdp.setNombre(dp.getNombre());
		mdp.setApellido1(dp.getApellido1());
		mdp.setApellido2(dp.getApellido2());
		mdp.setSexo(dp.getSexo());
		mdp.setFechaNacimiento(dp.getFechaNacimiento());
		mdp.setNacionalidad(dp.getNacionalidad());
		mdp.setDni(dp.getDni());
		mdp.setEmail(dp.getEmail());
		mdp.setTelefono(dp.getTelefono());
		mdp.setObservaciones(dp.getObservaciones());
		mdp.setDatospersonales_idUsr(dp.getUsuario().getIdUsr());
		
		return mdp;
		
	}

}
