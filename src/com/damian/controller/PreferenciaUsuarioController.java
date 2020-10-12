package com.damian.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damian.pojo.PreferenciaUsuario;
import com.damian.service.BotonFavoritoService;
import com.damian.service.IndexService;
import com.damian.service.PreferenciaUsuarioService;

@Controller
@SessionAttributes({ "resultado", "estoy", "errorUsuario", "idUsrLogged", "nameUsrLogged", "prinPicUsr", "prefUsr" })
public class PreferenciaUsuarioController {

	@Autowired
	private PreferenciaUsuarioService preferenciaUsuarioService;

	@Autowired
	private BotonFavoritoService botonFavoritoService;

	@Autowired
	private IndexService indexService;

	@RequestMapping("/preferenciaUsuario")
	public ModelAndView getAll(ModelAndView modelAndView) {
		modelAndView.addObject("preferenciaUsuarios", preferenciaUsuarioService.findAll());
		modelAndView.setViewName("preferenciaUsuarios");
		return modelAndView;
	}

	@RequestMapping("/preferenciaUsuario/{idPrefUsr}")
	public ModelAndView getPreferenciaUsuario(ModelAndView modelAndView, @PathVariable("idPrefUsr") int idPrefUsr) {
		indexService.idUserLogged(modelAndView);
		modelAndView.addObject("favoritos", botonFavoritoService.findAllFront());
		modelAndView.addObject("preferenciaUsuario", preferenciaUsuarioService.findById(idPrefUsr));
		modelAndView.setViewName("preferenciaUsuario");
		return modelAndView;
	}

	@RequestMapping(value = { "/preferenciaUsuario/update" }, method = RequestMethod.POST)
	public String savePreferenciaUsuario(@ModelAttribute("preferenciaUsuario") PreferenciaUsuario preferenciaUsuario,
			BindingResult result, ModelAndView modelAndView, RedirectAttributes ra, HttpServletRequest request) {
		if (result.hasErrors()) {
			// System.out.println(result.getAllErrors());
			// return "preferenciaUsuario";
		}
		if (preferenciaUsuarioService.update(preferenciaUsuario, request) > 0) {
			ra.addFlashAttribute("preferenciaUsuario_actualizado", "preferenciaUsuario_actualizado");
		} else {
			ra.addFlashAttribute("preferenciaUsuario_problemas", "preferenciaUsuario_problemas");
		}
		return "redirect:/preferenciaUsuario/" + preferenciaUsuario.getIdPrefUsr();
	}

}
