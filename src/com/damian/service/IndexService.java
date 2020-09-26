package com.damian.service;

import org.springframework.web.servlet.ModelAndView;

public interface IndexService {

	ModelAndView manageIndex(ModelAndView model);

	ModelAndView manageAbout(ModelAndView model, String estoy);

	int idUserLogged(ModelAndView model);

}
