package com.damian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.damian.pojo.Usuario;
import com.damian.service.IndexService;
import com.damian.service.UsuarioService;

@Service
public class IndexServiceImpl implements IndexService {
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public ModelAndView manageIndex(ModelAndView model) {

		idUserLogged(model);
		model.addObject("estoy", "index");
		model.setViewName("index");
		return model;
	}

	@Override
	public ModelAndView manageAbout(ModelAndView model, String estoy) {

		idUserLogged(model);
		model.addObject("estoy", "about");
		model.setViewName("about");
		return model;
	}
	
	private void idUserLogged(ModelAndView model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!authentication.getName().equals("anonymousUser")) {
			model.addObject("idUsrLogged", ((Usuario)usuarioService.findByUsername(authentication.getName())).getIdUsr());
		}
	}

}
