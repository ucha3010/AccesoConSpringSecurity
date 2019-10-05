package com.damian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damian.pojo.UsuarioEmpresa;
import com.damian.service.EmpresaService;
import com.damian.service.UsuarioEmpresaService;
import com.damian.service.UsuarioService;

@Controller
@SessionAttributes({ "resultado", "estoy", "roles" }) // los atributos que pueden mantenerse en sesión y verse en distintas
												// páginas
public class UsuarioEmpresaController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private UsuarioEmpresaService usuarioEmpresaService;

	@RequestMapping("/usuarioEmpresa/usuario/{idUsr}")
	public ModelAndView getUsers(ModelAndView modelAndView, @PathVariable("idUsr") int idUsr) {
		modelAndView.addObject("usuarioEmpresas", usuarioEmpresaService.findByIdUsr(idUsr));
		modelAndView.addObject("empresas", empresaService.findAll());
		modelAndView.setViewName("usuarioEmpresa");
		return modelAndView;
	}

	@RequestMapping("/usuarioEmpresa/empresa/{idEmp}")
	public ModelAndView getCompanies(ModelAndView modelAndView, @PathVariable("idEmp") int idEmp) {
		modelAndView.addObject("usuarioEmpresas", usuarioEmpresaService.findByIdEmp(idEmp));
		modelAndView.addObject("usuarios", usuarioService.findAll());
		modelAndView.setViewName("empresaUsuario");
		return modelAndView;
	}

	@RequestMapping(value = { "/usuarioEmpresa/usuario/save" }, method = RequestMethod.POST)
	public String saveUserRelation(@ModelAttribute("usuarioEmpresa") UsuarioEmpresa usuarioEmpresa,
			BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
//			return "empresa";
		}
		usuarioEmpresaService.save(usuarioEmpresa);
		return "redirect:/usuarioEmpresa/usuario/"+usuarioEmpresa.getUsuario().getIdUsr();
	}

	@RequestMapping(value = { "/usuarioEmpresa/empresa/save" }, method = RequestMethod.POST)
	public String saveCompanyRelation(@ModelAttribute("usuarioEmpresa") UsuarioEmpresa usuarioEmpresa,
			BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
//			return "empresa";
		}
		usuarioEmpresaService.save(usuarioEmpresa);
		return "redirect:/usuarioEmpresa/empresa/"+usuarioEmpresa.getEmpresa().getIdEmp();
	}

	@RequestMapping("/usuarioEmpresa/usuario/{idUsr}/{idEmp}/delete")
	public String deleteUserRelation(@PathVariable("idUsr") int idUsr, @PathVariable("idEmp") int idEmp, RedirectAttributes ra) {

		usuarioEmpresaService.delete(idUsr, idEmp);				
		ra.addFlashAttribute("resultado", "Cambios realizados con éxito");

		return "redirect:/usuarioEmpresa/usuario/"+idUsr;

	}

	@RequestMapping("/usuarioEmpresa/emresa/{idUsr}/{idEmp}/delete")
	public String deleteCompanyRelation(@PathVariable("idUsr") int idUsr, @PathVariable("idEmp") int idEmp, RedirectAttributes ra) {

		usuarioEmpresaService.delete(idUsr, idEmp);				
		ra.addFlashAttribute("resultado", "Cambios realizados con éxito");

		return "redirect:/usuarioEmpresa/empresa/"+idEmp;

	}

}
