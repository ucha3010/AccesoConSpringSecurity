package com.damian.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.damian.pojo.AdministracionOfertas;

public interface IndexService {

	ModelAndView manageIndex(ModelAndView model);

	ModelAndView manageAbout(ModelAndView model, String estoy);

	int idUserLogged(ModelAndView model);

	void agregarFotos(List<AdministracionOfertas> administracionOfertasList);

}
