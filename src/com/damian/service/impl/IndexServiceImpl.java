package com.damian.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.damian.service.IndexService;

@Service
public class IndexServiceImpl implements IndexService {

	@Override
	public ModelAndView manageIndex(ModelAndView model) {
		model.addObject("estoy", "index");
		model.setViewName("index");
		return model;
	}

	@Override
	public ModelAndView manageAbout(ModelAndView model, String estoy) {
		model.addObject("estoy", "about");
		model.setViewName("about");
		return model;
	}

}
