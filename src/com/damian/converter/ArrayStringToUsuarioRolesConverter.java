package com.damian.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;

import com.damian.pojo.Rol;
import com.damian.pojo.UsuarioRol;

public class ArrayStringToUsuarioRolesConverter implements Converter<String[], Set<UsuarioRol>> {

	@Override
	public Set<UsuarioRol> convert(String[] obj) {
		UsuarioRol usuarioRoles;
		Rol rol;
		Set<UsuarioRol> rolesList = new HashSet<>();
		if(obj != null) {
			for(int i=0; i < obj.length; i++) {
				rol = new Rol();
				rol.setRol(obj[i]);
				usuarioRoles = new UsuarioRol();
				usuarioRoles.setRol(rol);
				rolesList.add(usuarioRoles);
			}
		}
		return rolesList;
	}

}
