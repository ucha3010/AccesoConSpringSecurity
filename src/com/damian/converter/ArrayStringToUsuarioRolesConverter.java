package com.damian.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.damian.pojo.Rol;
import com.damian.pojo.UsuarioRol;

@Component
public class ArrayStringToUsuarioRolesConverter implements Converter<Object, UsuarioRol> {

	@Override
	public UsuarioRol convert(Object obj) {
		System.out.println("entro en convert*************************************************************************");
		if (obj == null) {
			throw new IllegalArgumentException();
		}
		UsuarioRol usuarioRoles = null;
		Rol rol;
		List<Object> rolesList = new ArrayList<>();
		if(obj != null) {
//			for(int i=0; i < obj.length; i++) {
				rol = new Rol();
				rol.setRol(obj.toString());
				usuarioRoles = new UsuarioRol();
				usuarioRoles.setRol(rol);
				rolesList.add(usuarioRoles);
//			}
		}
		return usuarioRoles;
	}

}
