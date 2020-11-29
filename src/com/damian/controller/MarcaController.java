package com.damian.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Marca;
import com.damian.service.MarcaService;
import com.damian.service.IndexService;

@Controller
@SessionAttributes({ "resultado", "estoy", "errorUsuario", "idUsrLogged", "nameUsrLogged", "prinPicUsr", "prefUsr" })
public class MarcaController {

	@Autowired
	private MarcaService marcaService;

	@Autowired
	private IndexService indexService;

	@RequestMapping("/marca")
	public ModelAndView getAll(ModelAndView modelAndView) {
		indexService.idUserLogged(modelAndView);
		modelAndView.addObject("marcas", marcaService.findAll());
		modelAndView.setViewName("marcas");
		return modelAndView;
	}

	@RequestMapping("/marca/{idMar}")
	public ModelAndView getMarca(ModelAndView modelAndView, @PathVariable("idMar") int idMar) {
		Marca marca = new Marca();
		if (idMar > 0) {
			marca = marcaService.findById(idMar);
		}
		modelAndView.addObject("marca", marca);
		modelAndView.setViewName("marca");
		return modelAndView;
	}

	@RequestMapping(value = { "/marca/save" }, method = RequestMethod.POST)
	public String saveMarca(@ModelAttribute("marca") Marca marca, BindingResult result, Model model,
			RedirectAttributes ra, HttpServletRequest request) {
		if (result.hasErrors()) {
			// System.out.println(result.getAllErrors());
			// return "marca";
		}
		boolean nueva = false;
		if (marca.getIdMar() == 0) {
			nueva = true;
		}
		marcaService.save(marca, request);
		if (nueva) {
			ra.addFlashAttribute("marca_agregado", "marca_agregado");
		}
		return "redirect:/marca";
	}

	@RequestMapping("/marca/delete/{idMar}")
	public String deleteUser(@PathVariable("idMar") int idMar, RedirectAttributes ra, HttpServletRequest request) {

		try {
			marcaService.delete(idMar, request);
			ra.addFlashAttribute("marca_eliminado", "marca_eliminado");
		} catch (NotEmptyException e) {
			ra.addFlashAttribute("marca_asociado", "marca_asociado");
		}
		return "redirect:/marca";

	}

}
