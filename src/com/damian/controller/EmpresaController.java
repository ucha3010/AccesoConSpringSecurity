package com.damian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damian.pojo.Empresa;
import com.damian.service.EmpresaService;
import com.damian.service.PaisService;
import com.damian.valid.SpringFormGroup;

@Controller
@SessionAttributes({ "resultado", "estoy", "roles" }) // los atributos que pueden mantenerse en sesión y verse en distintas
												// páginas
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private PaisService paisService;

	@RequestMapping("/empresa")
	public ModelAndView getAll(ModelAndView modelAndView) {
		modelAndView.addObject("empresas", empresaService.findAll());
		modelAndView.setViewName("empresas");
		return modelAndView;
	}

	@RequestMapping("/empresa/{idEmp}")
	public ModelAndView getUser(ModelAndView modelAndView, @PathVariable("idEmp") int idEmp) {
		Empresa empresa = new Empresa();
		if (idEmp > 0) {
			empresa = empresaService.findById(idEmp);
		}
		modelAndView.addObject("paises", paisService.findAll());
		modelAndView.addObject("empresa", empresa);
		modelAndView.setViewName("empresa");
		return modelAndView;
	}

	@RequestMapping(value = { "/empresa/save" }, method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("empresa") @Validated(value = SpringFormGroup.class) Empresa empresa,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
//			return "empresa";
		}
		empresaService.save(empresa);
		return "redirect:/empresa";
	}

	@RequestMapping("/empresa/{idEmp}/delete")
	public String deleteUser(@PathVariable("idEmp") int idEmp, RedirectAttributes ra) {

		empresaService.delete(idEmp);
		ra.addFlashAttribute("resultado", "Cambios realizados con éxito");

		return "redirect:/empresa";

	}

}
