package com.damian.utils;

import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;

import com.damian.converter.ArrayStringToUsuarioRolesConverter;
import com.damian.pojo.UsuarioRol;

public class TestArrayStringToUsuarioRolesConverter {

	public static void main(String[] args) {
		GenericConversionService conversionService = new GenericConversionService();
		Converter<String[], Set<UsuarioRol>> conv = new ArrayStringToUsuarioRolesConverter();
		conversionService.addConverter(conv);
		String[] lista = {"ROL_USUARIO","ROL_CLIENTE"};
		Set<UsuarioRol> salida = conv.convert(lista);
		System.out.println(salida);
	}

}
