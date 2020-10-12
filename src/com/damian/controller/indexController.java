package com.damian.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.damian.service.IndexService;

@Controller
//los atributos que pueden mantenerse en sesión y verse en distintas páginas
@SessionAttributes({ "resultado", "estoy", "errorUsuario", "idUsrLogged", "nameUsrLogged", "prinPicUsr", "prefUsr" })
public class indexController {

	@Autowired
	private IndexService indexService;

	// index
	// ////////////////////////////////////////////////////////////////////////////////////////////
	// los mapping que son permitAll en security-context.xml tengo que hacerles el
	// método private

	@RequestMapping("/")
	public ModelAndView showIndex(ModelAndView model) throws Exception {
		return indexService.manageIndex(model);
	}

	@RequestMapping("/private/index")
	public ModelAndView showPrivateIndex(ModelAndView model) throws Exception {
		return indexService.manageIndex(model);
	}

	// about
	// ////////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping("/about")
	public ModelAndView showAbout(ModelAndView model, HttpServletRequest request) throws Exception {
		Object estoy = request.getSession().getAttribute("estoy");
		if (estoy == null) {
			model.addObject("estoy", "about");
			estoy = "about";
		}
		return indexService.manageAbout(model, estoy.toString());
	}

	@RequestMapping("/private/about")
	public ModelAndView showPrivateAbout(ModelAndView model, HttpServletRequest request) throws Exception {
		Object estoy = request.getSession().getAttribute("estoy");
		if (estoy == null) {
			model.addObject("estoy", "about");
			estoy = "about";
		}
		return indexService.manageAbout(model, estoy.toString());
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
	public ModelAndView login(ModelAndView model, @RequestParam(value = "error", required = false) String error,
			HttpServletRequest request) {

		Object estoy = request.getSession().getAttribute("estoy");
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}
		if (estoy == null) {
			model.addObject("estoy", "index");
			String rutaSalida = ((org.springframework.security.web.savedrequest.DefaultSavedRequest) request
					.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST")).getServletPath();
			model.setViewName("redirect:" + rutaSalida);
			return model;
		}
		model.setViewName("login");
		return model;
	}

	@RequestMapping("/logout")
	public String logout(ModelAndView model) {
		model.addObject("prinPicUsr", null);
		return "/";
	}

	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		return exception.getMessage();
	}

}
