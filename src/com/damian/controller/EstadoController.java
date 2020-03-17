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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Estado;
import com.damian.service.EstadoService;

@Controller
public class EstadoController {

	@Autowired
	private EstadoService estadoService;

	@RequestMapping("/estado")
	public ModelAndView getAll(ModelAndView modelAndView) {
		modelAndView.addObject("estados", estadoService.findAll());
		modelAndView.setViewName("estados");
		return modelAndView;
	}

	@RequestMapping("/estado/{idEst}")
	public ModelAndView getEstado(ModelAndView modelAndView, @PathVariable("idEst") int idEst) {
		Estado estado = new Estado();
		if (idEst > 0) {
			estado = estadoService.findById(idEst);
		}
		modelAndView.addObject("estado", estado);
		modelAndView.setViewName("estado");
		return modelAndView;
	}

	@RequestMapping(value = { "/estado/save" }, method = RequestMethod.POST)
	public String saveEstado(@ModelAttribute("estado") Estado estado, BindingResult result, Model model,
			RedirectAttributes ra) {
		if (result.hasErrors()) {
			// System.out.println(result.getAllErrors());
			// return "estado";
		}
		boolean nueva = false;
		if (estado.getIdEst() == 0) {
			nueva = true;
		}
		estadoService.save(estado);
		if (nueva) {
			ra.addFlashAttribute("estado_agregado", "estado_agregado");
		}
		return "redirect:/estado";
	}

	@RequestMapping("/estado/delete/{idEst}")
	public String deleteUser(@PathVariable("idEst") int idEst, RedirectAttributes ra, HttpServletRequest request) {

		try {
			estadoService.delete(idEst, "null", request);
			ra.addFlashAttribute("estado_eliminado", "estado_eliminado");
		} catch (NotEmptyException e) {
			ra.addFlashAttribute("estado_asociado", "estado_asociado");
		}
		return "redirect:/estado";

	}

}
