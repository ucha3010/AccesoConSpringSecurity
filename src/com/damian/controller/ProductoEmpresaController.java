package com.damian.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damian.pojo.AuxString;
import com.damian.service.EmpresaService;
import com.damian.service.ProductoEmpresaService;
import com.damian.service.ProductoService;

@Controller
@SessionAttributes({ "resultado" }) // los atributos que pueden mantenerse en sesión y verse en distintas
									// páginas
public class ProductoEmpresaController {

	@Autowired
	private ProductoService productoService;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private ProductoEmpresaService productoEmpresaService;

	@RequestMapping("/productoEmpresa/producto/{idPro}")
	public ModelAndView getProductos(ModelAndView modelAndView, @PathVariable("idPro") int idPro) {
		modelAndView.addObject("producto", productoService.findById(idPro));
		modelAndView.addObject("productoEmpresas", productoEmpresaService.findByIdPro(idPro));
		modelAndView.addObject("empresas", empresaService.findSearchAll());
		modelAndView.addObject("auxString", new AuxString());
		modelAndView.setViewName("productoEmpresa");
		return modelAndView;
	}

	@RequestMapping("/productoEmpresa/empresa/{idEmp}")
	public ModelAndView getEmpresas(ModelAndView modelAndView, @PathVariable("idEmp") int idEmp,
			HttpServletRequest request) {
		modelAndView.addObject("empresa", empresaService.findById(idEmp));
		modelAndView.addObject("productoEmpresas", productoEmpresaService.findByIdEmp(idEmp));
		modelAndView.addObject("productos", productoService.findSearchAll());
		modelAndView.addObject("auxString", new AuxString());
		modelAndView.setViewName("empresaProducto");
		return modelAndView;
	}

	@RequestMapping("/productoEmpresa/producto/save/{idPro}")
	public String saveProducto(ModelAndView modelAndView, @PathVariable("idPro") int idPro, HttpServletRequest request,
			RedirectAttributes ra, @ModelAttribute("auxString") AuxString auxString) {

		try {
			productoEmpresaService.save(idPro, Integer.parseInt(auxString.getCampo()), request);
		} catch (Exception e) {
			ra.addFlashAttribute("error", true);
		}

		return "redirect:/productoEmpresa/producto/" + idPro;
	}

	@RequestMapping("/productoEmpresa/empresa/save/{idEmp}")
	public String saveEmpresa(ModelAndView modelAndView, @PathVariable("idEmp") int idEmp, HttpServletRequest request,
			RedirectAttributes ra, @ModelAttribute("auxString") AuxString auxString) {

		try {
			productoEmpresaService.save(Integer.parseInt(auxString.getCampo()), idEmp, request);
		} catch (Exception e) {
			ra.addFlashAttribute("error", true);
		}

		return "redirect:/productoEmpresa/empresa/" + idEmp;
	}

	@RequestMapping("/productoEmpresa/producto/delete/{idPro}/{idEmp}")
	public String deleteProducto(@PathVariable("idPro") int idPro, @PathVariable("idEmp") int idEmp,
			RedirectAttributes ra) {

		productoEmpresaService.delete(idPro, idEmp);
		ra.addFlashAttribute("resultado", "Cambios realizados con éxito");

		return "redirect:/productoEmpresa/producto/" + idPro;

	}

	@RequestMapping("/productoEmpresa/empresa/delete/{idPro}/{idEmp}")
	public String deleteEmpresa(@PathVariable("idPro") int idPro, @PathVariable("idEmp") int idEmp,
			RedirectAttributes ra) {

		productoEmpresaService.delete(idPro, idEmp);
		ra.addFlashAttribute("resultado", "Cambios realizados con éxito");

		return "redirect:/productoEmpresa/empresa/" + idEmp;

	}

}
