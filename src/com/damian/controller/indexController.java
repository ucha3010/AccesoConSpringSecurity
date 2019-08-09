package com.damian.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@SessionAttributes({"resultado","nombre","valor"}) //los atributos que pueden mantenerse en sesión y verse en distintas páginas
public class indexController {
	
	@RequestMapping("/")
	public ModelAndView showIndex(ModelAndView model) {
		
		model.addObject("resultado", "Resultado desde Session");
		model.setViewName("index");
		
		return model;
	}
	
	@RequestMapping("/about")
	public String showAbout(SessionStatus sessionStatus) {
		return "about";
	}

	@RequestMapping("/index2")
	public ModelAndView showIndex2(ModelAndView model) {
		model.addObject("resultado", "Resultado desde index2");
		model.setViewName("redirect:/index3");
		return model;
	}

	@RequestMapping("/index3")
	public ModelAndView showIndex3(ModelAndView model) {
		model.setViewName("index3");
		return model;
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/logout")
	public String logout() {
		return "/";
	}
	
	

}
