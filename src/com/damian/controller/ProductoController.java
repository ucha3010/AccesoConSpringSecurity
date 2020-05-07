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
import com.damian.pojo.Producto;
import com.damian.pojo.front.FrontProductoStock;
import com.damian.service.CategoriaService;
import com.damian.service.PaginacionService;
import com.damian.service.ProductoService;

@Controller
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private PaginacionService paginacionService;

	@RequestMapping("/producto/all/{column}/{paginaInicio}/{totalPaginas}")
	public ModelAndView getAll(ModelAndView modelAndView, @PathVariable("column") String column,
			@PathVariable("paginaInicio") int paginaInicio, @PathVariable("totalPaginas") int totalPaginas,
			HttpServletRequest request) {
		modelAndView.addObject("productos", productoService.findAll(column, paginaInicio, totalPaginas, request));
		modelAndView.addObject("paginacion", paginacionService.pagination(paginaInicio, totalPaginas, "producto"));
		modelAndView.addObject("buscarproductos", productoService.findSearchAll());
		modelAndView.addObject("estoy", "producto");
		modelAndView.setViewName("productos");
		return modelAndView;
	}

	@RequestMapping("/producto/filtered/{idPro}")
	public ModelAndView getFiltered(ModelAndView modelAndView, @PathVariable("idPro") int idPro) {
		modelAndView.addObject("productos", productoService.findByIdList(idPro));
		modelAndView.addObject("paginacion", paginacionService.pagination(0, 0, "producto"));
		modelAndView.addObject("buscarproductos", productoService.findSearchAll());
		modelAndView.addObject("estoy", "producto");
		modelAndView.setViewName("productos");
		return modelAndView;
	}

	@RequestMapping("/producto/{idPro}")
	public ModelAndView getProducto(ModelAndView modelAndView, @PathVariable("idPro") int idPro) {
		Producto producto = new Producto();
		if (idPro > 0) {
			producto = productoService.findById(idPro);
		}
		modelAndView.addObject("producto", producto);
		modelAndView.addObject("categorias", categoriaService.findAll());
		modelAndView.setViewName("producto");
		return modelAndView;
	}

	@RequestMapping(value = { "/producto/save" }, method = RequestMethod.POST)
	public String saveProducto(@ModelAttribute("producto") Producto producto, BindingResult result, Model model,
			RedirectAttributes ra) {
		if (result.hasErrors()) {
			// System.out.println(result.getAllErrors());
			// return "producto";
		}
		boolean nueva = false;
		if (producto.getIdPro() == 0) {
			nueva = true;
		}
		productoService.save(producto);
		if (nueva) {
			ra.addFlashAttribute("producto_agregado", "producto_agregado");
		}
		return "redirect:/producto/all/null/0/100";
	}

	@RequestMapping("/producto/delete/{idPro}")
	public String deleteUser(@PathVariable("idPro") int idPro, RedirectAttributes ra) {

		try {
			productoService.delete(idPro);
			ra.addFlashAttribute("producto_eliminado", "producto_eliminado");
		} catch (NotEmptyException e) {
			ra.addFlashAttribute("producto_asociado", "producto_asociado");
		}
		return "redirect:/producto/all/null/0/100";

	}

	@RequestMapping("/producto/stock/{idPro}")
	public ModelAndView getProductoStock(ModelAndView modelAndView, @PathVariable("idPro") int idPro) {
		Producto producto = productoService.findById(idPro);
		modelAndView.addObject("frontProductoStock", productoService.fillFrontProductoStock(producto));
		modelAndView.setViewName("productoStock");
		return modelAndView;
	}

	@RequestMapping(value = { "/producto/stock/save" }, method = RequestMethod.POST)
	public String saveProductoStock(@ModelAttribute("frontProductoStock") FrontProductoStock frontProductoStock,
			BindingResult result, Model model, RedirectAttributes ra, HttpServletRequest request) {

		productoService.saveProductoStock(frontProductoStock, request);
		return "redirect:/producto/all/null/0/100";
	}

}
