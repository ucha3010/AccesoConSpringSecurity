package com.damian.controller;

import java.util.List;

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
import com.damian.pojo.FiltroTitulo;
import com.damian.pojo.Producto;
import com.damian.pojo.Subcategoria;
import com.damian.service.CategoriaService;
import com.damian.service.FiltroTituloService;
import com.damian.service.IndexService;
import com.damian.service.ProductoService;
import com.damian.service.SubcategoriaService;

@Controller
//los atributos que pueden mantenerse en sesión y verse en distintas páginas
@SessionAttributes({ "resultado", "estoy", "errorUsuario", "idUsrLogged", "nameUsrLogged", "prinPicUsr", "prefUsr" })
public class SubcategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private FiltroTituloService filtroTituloService;

	@Autowired
	private IndexService indexService;

	@Autowired
	private ProductoService productoService;

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
	public String deleteUser(@PathVariable("idSub") int idSub, RedirectAttributes ra, HttpServletRequest request) {

		try {
			subcategoriaService.delete(idSub, request);
			ra.addFlashAttribute("subcategoria_eliminado", "subcategoria_eliminado");
		} catch (NotEmptyException e) {
			ra.addFlashAttribute("subcategoria_asociado", "subcategoria_asociado");
		}
		return "redirect:/categoria";

	}

	@RequestMapping("/front/subcategoria/{idSub}")
	public ModelAndView getFrontSubcategoria(ModelAndView modelAndView, @PathVariable("idSub") int idSub) {
		
		indexService.idUserLogged(modelAndView);
		indexService.chargeDivSearchBar(modelAndView);

		Subcategoria subcategoria = subcategoriaService.findByIdModel(idSub);
		modelAndView.addObject("subcategoria", subcategoria);

		List<FiltroTitulo> filtroTitulos = filtroTituloService.findByIdSub(subcategoria.getIdSub());
		modelAndView.addObject("filtroTitulos", filtroTitulos);

		List<Producto> productos = productoService.findByIdSubModel(idSub);
		productoService.fillFrontSubcategoria(productos);
		modelAndView.addObject("productos", productos);

		modelAndView.addObject("estoy", "front/subcategoria/" + idSub);

		modelAndView.setViewName("frontsubcategoria");

		return modelAndView;

	}

	@RequestMapping("/private/front/subcategoria/{idSub}")
	public ModelAndView getPrivateFrontSubcategoria(ModelAndView modelAndView, @PathVariable("idSub") int idSub) {
		return getFrontSubcategoria(modelAndView, idSub);
	}

}
