package com.damian.service;

import org.springframework.web.servlet.ModelAndView;

public interface IndexService {
	
	ModelAndView manageIndex(ModelAndView model) throws Exception;
	
	ModelAndView manageAbout(ModelAndView model, String estoy) throws Exception;

}
