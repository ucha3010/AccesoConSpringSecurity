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

import com.damian.pojo.EmpresaPropia;
import com.damian.service.EmpresaPropiaService;
import com.damian.service.PaisService;

@Controller
public class EmpresaPropiaController {

	@Autowired
	private EmpresaPropiaService empresaPropiaService;

	@Autowired
	private PaisService paisService;

	@RequestMapping("/empresaPropia")
	public ModelAndView getAll(ModelAndView modelAndView) {
		modelAndView.addObject("empresaPropias", empresaPropiaService.findAll());
		modelAndView.setViewName("empresaPropias");
		return modelAndView;
	}

	@RequestMapping("/empresaPropia/{idPropia}")
	public ModelAndView getEmpresaPropia(ModelAndView modelAndView, @PathVariable("idPropia") int idPropia) {
		EmpresaPropia empresaPropia = new EmpresaPropia();
		if (idPropia > 0) {
			empresaPropia = empresaPropiaService.findById(idPropia);
		}
		modelAndView.addObject("empresaPropia", empresaPropia);
		modelAndView.addObject("estoy", "empresaPropia");
		modelAndView.addObject("paises", paisService.findAll());
		modelAndView.setViewName("empresaPropia");
		return modelAndView;
	}

	@RequestMapping(value = { "/empresaPropia/save" }, method = RequestMethod.POST)
	public String saveEmpresaPropia(@ModelAttribute("empresaPropia") EmpresaPropia empresaPropia, BindingResult result,
			Model model, RedirectAttributes ra, HttpServletRequest request) {

		empresaPropiaService.save(empresaPropia, request);
		if (empresaPropia.getIdPropia() == 0) {
			ra.addFlashAttribute("empresaPropia_agregado", "empresaPropia_agregado");
		}
		return "redirect:/empresaPropia";
	}

	@RequestMapping("/empresaPropia/delete/{idPropia}")
	public String deleteUser(@PathVariable("idPropia") int idPropia, RedirectAttributes ra,
			HttpServletRequest request) {

		empresaPropiaService.delete(idPropia, request);
		ra.addFlashAttribute("empresaPropia_eliminado", "empresaPropia_eliminado");
		return "redirect:/empresaPropia";

	}

	@RequestMapping("/empresaPropia/available/{idPropia}")
	public ModelAndView changeAvailable(ModelAndView modelAndView, @PathVariable("idPropia") int idPropia,
			HttpServletRequest request) {

		empresaPropiaService.changeAvailable(idPropia, request);
		return getAll(modelAndView);
	}

}
