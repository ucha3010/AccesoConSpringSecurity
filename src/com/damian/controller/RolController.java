package com.damian.controller;

import java.util.List;

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

import com.damian.pojo.Rol;
import com.damian.pojo.UsuarioRol;
import com.damian.service.RolService;
import com.damian.service.UsuarioRolService;
import com.damian.valid.SpringFormGroup;

@Controller
@SessionAttributes({ "resultado" }) // los atributos que pueden mantenerse en sesión y verse en distintas
												// páginas
public class RolController {

	@Autowired
	private RolService rolService;

	@Autowired
	private UsuarioRolService usuarioRolService;

	@RequestMapping("/rol")
	public ModelAndView getAll(ModelAndView modelAndView) {
		modelAndView.addObject("roles", rolService.findAll());
		modelAndView.setViewName("roles");
		return modelAndView;
	}

	@RequestMapping("/rol/{idRol}")
	public ModelAndView getUser(ModelAndView modelAndView, @PathVariable("idRol") int idRol) {
		Rol rol = new Rol();
		if (idRol > 0) {
			rol = rolService.findById(idRol);
		}
		modelAndView.addObject("rol", rol);
		modelAndView.setViewName("rol");
		return modelAndView;
	}

	@RequestMapping(value = { "/rol/save" }, method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("rol") @Validated(value = SpringFormGroup.class) Rol rol,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
//			return "rol";
		}
		rolService.save(rol);
		return "redirect:/rol";
	}

	@RequestMapping("/rol/delete/{idRol}")
	public String deleteUser(@PathVariable("idRol") int idRol, RedirectAttributes ra) {

		List<UsuarioRol> usuarioRolList = usuarioRolService.findByIdRol(idRol);
		if(usuarioRolList == null || usuarioRolList.isEmpty()) {
			rolService.delete(idRol);
			ra.addFlashAttribute("eliminado", "eliminado");
		} else {
			ra.addFlashAttribute("asociado", "asociado");
		}
		return "redirect:/rol";

	}

}
