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
import com.damian.pojo.Usuario;
import com.damian.service.EmpresaService;
import com.damian.service.UsuarioEmpresaService;
import com.damian.service.UsuarioService;

@Controller
@SessionAttributes({ "resultado" }) // los atributos que pueden mantenerse en sesión y verse en distintas
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
		modelAndView.addObject("usuario", usuarioService.findById(idUsr));
		modelAndView.addObject("auxString", new AuxString());
		modelAndView.addObject("usuarioEmpresas", usuarioEmpresaService.findByIdUsr(idUsr));
		modelAndView.addObject("empresas", empresaService.findAll(null, null));
		modelAndView.setViewName("usuarioEmpresa");
		return modelAndView;
	}

	@RequestMapping("/usuarioEmpresa/empresa/{idEmp}")
	public ModelAndView getCompanies(ModelAndView modelAndView, @PathVariable("idEmp") int idEmp, HttpServletRequest request) {
		modelAndView.addObject("empresa", empresaService.findById(idEmp));
		modelAndView.addObject("auxString", new AuxString());
		modelAndView.addObject("usuarioEmpresas", usuarioEmpresaService.findByIdEmp(idEmp));
		modelAndView.addObject("usuarios", usuarioService.findAll("datosPersonales.nombre", "ASC", request));
		modelAndView.setViewName("empresaUsuario");
		return modelAndView;
	}

	@RequestMapping("/usuarioEmpresa/usuario/save/{idUsr}")
	public String saveUserRelation(ModelAndView modelAndView, @PathVariable("idUsr") int idUsr, HttpServletRequest request, 
			RedirectAttributes ra, @ModelAttribute("auxString") AuxString auxString) {
		try {
			usuarioEmpresaService.save(idUsr, Integer.parseInt(auxString.getCampo()), request);
		} catch(Exception e) {
			ra.addFlashAttribute("error", true);
		}
		return "redirect:/usuarioEmpresa/usuario/"+idUsr;
	}

	@RequestMapping("/usuarioEmpresa/empresa/save/{idEmp}")
	public String saveCompanyRelation(ModelAndView modelAndView, @PathVariable("idEmp") int idEmp, HttpServletRequest request, 
			RedirectAttributes ra, @ModelAttribute("auxString") AuxString auxString) {
		try {		
			usuarioEmpresaService.save(Integer.parseInt(auxString.getCampo()), idEmp, request);
		} catch(Exception e) {
			Usuario usuario = usuarioService.findById(Integer.parseInt(auxString.getCampo()));
			ra.addFlashAttribute("existia", usuario.getDatosPersonales().getNombre()+" "+usuario.getDatosPersonales().getApellido1()+" "+usuario.getDatosPersonales().getApellido2());
			ra.addFlashAttribute("error", true);
		}
		return "redirect:/usuarioEmpresa/empresa/"+idEmp;
	}

	@RequestMapping("/usuarioEmpresa/usuario/delete/{idUsr}/{idEmp}")
	public String deleteUserRelation(@PathVariable("idUsr") int idUsr, @PathVariable("idEmp") int idEmp, RedirectAttributes ra) {

		usuarioEmpresaService.delete(idUsr, idEmp);				
		ra.addFlashAttribute("resultado", "Cambios realizados con éxito");

		return "redirect:/usuarioEmpresa/usuario/"+idUsr;

	}

	@RequestMapping("/usuarioEmpresa/empresa/delete/{idUsr}/{idEmp}")
	public String deleteCompanyRelation(@PathVariable("idUsr") int idUsr, @PathVariable("idEmp") int idEmp, RedirectAttributes ra) {

		usuarioEmpresaService.delete(idUsr, idEmp);				
		ra.addFlashAttribute("resultado", "Cambios realizados con éxito");

		return "redirect:/usuarioEmpresa/empresa/"+idEmp;

	}

}
