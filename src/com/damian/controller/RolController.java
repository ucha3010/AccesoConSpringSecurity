package com.damian.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.damian.service.IndexService;
import com.damian.service.RolService;
import com.damian.service.UsuarioRolService;
import com.damian.valid.SpringFormGroup;

@Controller
@SessionAttributes({ "resultado", "estoy", "errorUsuario", "idUsrLogged", "nameUsrLogged", "prinPicUsr", "prefUsr" })
public class RolController {

	@Autowired
	private RolService rolService;

	@Autowired
	private IndexService indexService;

	@Autowired
	private UsuarioRolService usuarioRolService;

	@RequestMapping("/rol")
	public ModelAndView getAll(ModelAndView modelAndView) {
		indexService.idUserLogged(modelAndView);
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
			BindingResult result, Model model, RedirectAttributes ra, HttpServletRequest request) {
		if (result.hasErrors()) {
			// System.out.println(result.getAllErrors());
			// return "rol";
		}
		boolean nueva = false;
		if (rol.getIdRol() == 0) {
			nueva = true;
		}
		rolService.save(rol, request);
		if (nueva) {
			ra.addFlashAttribute("rol_agregado", "rol_agregado");
		}
		return "redirect:/rol";
	}

	@RequestMapping("/rol/delete/{idRol}")
	public String deleteUser(@PathVariable("idRol") int idRol, RedirectAttributes ra, HttpServletRequest request) {

		List<UsuarioRol> usuarioRolList = usuarioRolService.findByIdRol(idRol);
		if (usuarioRolList == null || usuarioRolList.isEmpty()) {
			rolService.delete(idRol, request);
			ra.addFlashAttribute("rol_eliminado", "rol_eliminado");
		} else {
			ra.addFlashAttribute("rol_asociado", "rol_asociado");
		}
		return "redirect:/rol";

	}

}
