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
import com.damian.pojo.Categoria;
import com.damian.service.CategoriaService;
import com.damian.service.IndexService;

@Controller
@SessionAttributes({ "resultado", "estoy", "errorUsuario", "idUsrLogged", "nameUsrLogged", "prinPicUsr", "prefUsr" })
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private IndexService indexService;

	@RequestMapping("/categoria")
	public ModelAndView getAll(ModelAndView modelAndView) {
		indexService.idUserLogged(modelAndView);
		modelAndView.addObject("categorias", categoriaService.findAll());
		modelAndView.setViewName("categorias");
		return modelAndView;
	}

	@RequestMapping("/categoria/{idCat}")
	public ModelAndView getCategoria(ModelAndView modelAndView, @PathVariable("idCat") int idCat) {
		Categoria categoria = new Categoria();
		if (idCat > 0) {
			categoria = categoriaService.findById(idCat);
		}
		modelAndView.addObject("categoria", categoria);
		modelAndView.setViewName("categoria");
		return modelAndView;
	}

	@RequestMapping(value = { "/categoria/save" }, method = RequestMethod.POST)
	public String saveCategoria(@ModelAttribute("categoria") Categoria categoria, BindingResult result, Model model,
			RedirectAttributes ra, HttpServletRequest request) {
		if (result.hasErrors()) {
			// System.out.println(result.getAllErrors());
			// return "categoria";
		}
		boolean nueva = false;
		if (categoria.getIdCat() == 0) {
			nueva = true;
		}
		categoriaService.save(categoria, request);
		if (nueva) {
			ra.addFlashAttribute("categoria_agregado", "categoria_agregado");
		}
		return "redirect:/categoria";
	}

	@RequestMapping("/categoria/delete/{idCat}")
	public String deleteCategoria(@PathVariable("idCat") int idCat, RedirectAttributes ra, HttpServletRequest request) {

		try {
			categoriaService.delete(idCat, request);
			ra.addFlashAttribute("categoria_eliminado", "categoria_eliminado");
		} catch (NotEmptyException e) {
			ra.addFlashAttribute("categoria_asociado", "categoria_asociado");
		}
		return "redirect:/categoria";

	}

}
