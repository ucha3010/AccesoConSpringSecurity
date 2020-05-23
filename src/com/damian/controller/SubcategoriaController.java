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
import com.damian.pojo.Subcategoria;
import com.damian.service.CategoriaService;
import com.damian.service.SubcategoriaService;

@Controller
public class SubcategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private SubcategoriaService subcategoriaService;

	@RequestMapping("/subcategoria")
	public ModelAndView getAll(ModelAndView modelAndView) {
		modelAndView.addObject("subcategorias", subcategoriaService.findAll());
		modelAndView.setViewName("subcategorias");
		return modelAndView;
	}

	@RequestMapping("/subcategoria/{idSub}")
	public ModelAndView getSubcategoria(ModelAndView modelAndView, @PathVariable("idSub") int idSub) {
		Subcategoria subcategoria = new Subcategoria();
		if (idSub > 0) {
			subcategoria = subcategoriaService.findById(idSub);
		}
		modelAndView.addObject("subcategoria", subcategoria);
		modelAndView.addObject("categorias", categoriaService.findAll());
		modelAndView.setViewName("subcategoria");
		return modelAndView;
	}

	@RequestMapping(value = { "/subcategoria/save" }, method = RequestMethod.POST)
	public String saveSubcategoria(@ModelAttribute("subcategoria") Subcategoria subcategoria, BindingResult result,
			Model model, RedirectAttributes ra, HttpServletRequest request) {
		if (result.hasErrors()) {
			// System.out.println(result.getAllErrors());
			// return "subcategoria";
		}
		boolean nueva = false;
		if (subcategoria.getIdSub() == 0) {
			nueva = true;
		}
		subcategoriaService.save(subcategoria, request);
		if (nueva) {
			ra.addFlashAttribute("subcategoria_agregado", "subcategoria_agregado");
		}
		return "redirect:/categoria";
	}

	@RequestMapping("/subcategoria/delete/{idSub}")
	public String deleteUser(@PathVariable("idSub") int idSub, RedirectAttributes ra) {

		try {
			subcategoriaService.delete(idSub);
			ra.addFlashAttribute("subcategoria_eliminado", "subcategoria_eliminado");
		} catch (NotEmptyException e) {
			ra.addFlashAttribute("subcategoria_asociado", "subcategoria_asociado");
		}
		return "redirect:/categoria";

	}

}
