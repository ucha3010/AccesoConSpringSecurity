package com.damian.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.damian.service.IndexService;

@Controller
@SessionAttributes({ "resultado", "nombre", "valor", "estoy", "errorUsuario" }) // los atributos que pueden mantenerse
																				// en sesión y verse en distintas
																				// páginas
public class indexController {

	@Autowired
	private IndexService indexService;

	// index
	// ////////////////////////////////////////////////////////////////////////////////////////////
	// los mapping que son permitAll en security-context.xml tengo que hacerles el
	// método private

	@RequestMapping("/")
	public ModelAndView showIndex(ModelAndView model) {
		return indexService.manageIndex(model);
	}

	@RequestMapping("/private/index")
	public ModelAndView showPrivateIndex(ModelAndView model) {
		return indexService.manageIndex(model);
	}

	// about
	// ////////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping("/about")
	public ModelAndView showAbout(ModelAndView model, @ModelAttribute("estoy") String estoy) {
		return indexService.manageAbout(model, estoy);
	}

	@RequestMapping("/private/about")
	public ModelAndView showPrivateAbout(ModelAndView model, @ModelAttribute("estoy") String estoy) {
		return indexService.manageAbout(model, estoy);
	}

	// index2
	// ////////////////////////////////////////////////////////////////////////////////////////////

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
	public ModelAndView login(ModelAndView model, @ModelAttribute("estoy") String estoy,
			@RequestParam(value = "error", required = false) String error, HttpServletRequest request) {

		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}
		model.setViewName("login");
		return model;
	}

	@RequestMapping("/logout")
	public String logout() {
		return "/";
	}

	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		return exception.getMessage();
	}

}
