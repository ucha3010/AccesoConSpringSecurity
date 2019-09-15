package com.damian.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.damian.pojo.Rol;
import com.damian.pojo.UsuarioRol;

@Component
public class ArrayStringToUsuarioRolesConverter implements Converter<Object, Set<UsuarioRol>> {

	@Override
	public Set<UsuarioRol> convert(Object obj) {
		if (obj == null) {
			throw new IllegalArgumentException();
		}
		UsuarioRol usuarioRoles;
		Rol rol;
		Set<UsuarioRol> rolesList = new HashSet<>();
		if(obj != null) {
//			for(int i=0; i < obj.length; i++) {
				rol = new Rol();
				rol.setRol((String)obj);
				usuarioRoles = new UsuarioRol();
				usuarioRoles.setRol(rol);
				rolesList.add(usuarioRoles);
//			}
		}
		return rolesList;
	}

}
