package com.damian.converter;

import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelPreferenciaUsuario;
import com.damian.pojo.PreferenciaUsuario;

@Component
public class ConverterPreferenciaUsuario {

	public PreferenciaUsuario convertAll(ModelPreferenciaUsuario mc) {

		PreferenciaUsuario c = convert(mc);

		return c;

	}

	public PreferenciaUsuario convert(ModelPreferenciaUsuario mpu) {

		PreferenciaUsuario pu = new PreferenciaUsuario();
		pu.setIdPrefUsr(mpu.getIdPrefUsr());
		pu.setTema(mpu.getTema());
		pu.setBotonFavorito(mpu.getBotonFavorito());
		pu.setRecibirPublicidad(mpu.isRecibirPublicidad());

		return pu;

	}

	public ModelPreferenciaUsuario convert(PreferenciaUsuario pu) {

		ModelPreferenciaUsuario mpu = new ModelPreferenciaUsuario();
		mpu.setIdPrefUsr(pu.getIdPrefUsr());
		mpu.setTema(pu.getTema());
		mpu.setBotonFavorito(pu.getBotonFavorito());
		mpu.setRecibirPublicidad(pu.isRecibirPublicidad());

		return mpu;

	}

}
