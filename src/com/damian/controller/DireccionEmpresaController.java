package com.damian.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damian.pojo.DireccionEmpresa;
import com.damian.pojo.Empresa;
import com.damian.service.DireccionEmpresaService;
import com.damian.service.EmpresaService;
import com.damian.service.PaisService;

@Controller
@SessionAttributes({ "resultado", "estoy" })
public class DireccionEmpresaController {

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private DireccionEmpresaService direccionEmpresaService;

	@Autowired
	private PaisService paisService;

	@RequestMapping("/direccionEmpresa/{idEmp}")
	public String getAll(Model model, @PathVariable("idEmp") int idEmp) {

		model.addAttribute("empresa", empresaService.findById(idEmp));
		model.addAttribute("direccionesEmpresa", direccionEmpresaService.findListFromEmpresa(idEmp));

		return "direccionesEmpresa";
	}

	@RequestMapping("/direccionEmpresa/formulario/{idDirEmp}/{idEmp}")
	public ModelAndView getUser(ModelAndView modelAndView, @PathVariable("idDirEmp") int idDirEmp,
			@PathVariable("idEmp") int idEmp) {

		DireccionEmpresa direccionEmpresa;
		if (idDirEmp > 0) {
			direccionEmpresa = direccionEmpresaService.findById(idDirEmp);
		} else {
			direccionEmpresa = new DireccionEmpresa();
			Empresa empresa = new Empresa();
			empresa.setIdEmp(idEmp);
			direccionEmpresa.setEmpresa(empresa);
		}
		modelAndView.addObject("direccionEmpresa", direccionEmpresa);
		modelAndView.addObject("estoy", "direccionEmpresa");
		modelAndView.addObject("paises", paisService.findAll());
		modelAndView.setViewName("direccionEmpresa");
		return modelAndView;
	}

	@RequestMapping("/direccionEmpresa/save/{idEmp}")
	public String save(Model model, RedirectAttributes ra,
			@ModelAttribute("direccionEmpresa") DireccionEmpresa direccionEmpresa, @PathVariable("idEmp") int idEmp,
			HttpServletRequest request) {

		direccionEmpresaService.save(idEmp, direccionEmpresa, request);
		ra.addFlashAttribute("resultado", "Cambios realizados con éxito");

		return "redirect:/direccionEmpresa/" + idEmp;
	}

	@RequestMapping("/direccionEmpresa/delete/{idDirEmp}/{idEmp}")
	public String deleteUser(@PathVariable("idDirEmp") int idDirEmp, @PathVariable("idEmp") int idEmp,
			RedirectAttributes ra) {

		direccionEmpresaService.delete(idDirEmp);
		ra.addFlashAttribute("resultado", "Cambios realizados con éxito");

		return "redirect:/direccionEmpresa/" + idEmp;

	}
}
