package com.damian.controller;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damian.pojo.Rol;
import com.damian.pojo.Usuario;
import com.damian.pojo.UsuarioRol;
import com.damian.pojo.UsuarioRolId;
import com.damian.service.PaisService;
import com.damian.service.RolService;
import com.damian.service.impl.UsuarioServiceImpl;
import com.damian.valid.SpringFormGroup;

@Controller
@SessionAttributes({ "resultado", "estoy", "roles" }) // los atributos que pueden mantenerse en sesi�n y verse en distintas
												// p�ginas
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl usuarioService;

	@Autowired
	private PaisService paisService;

	@Autowired
	private RolService rolService;

	@RequestMapping("/usuario")
	public ModelAndView getAll(ModelAndView modelAndView) {
		modelAndView.addObject("usuarios", usuarioService.findAll());
//		***********Roles*********** 
		modelAndView.addObject("roles", rolService.findAll());
//		**********************
		modelAndView.addObject("estoy", "usuario");
		modelAndView.setViewName("usuarios");
		return modelAndView;
	}

	@RequestMapping("/usuario/{idUsr}")
	public ModelAndView getUser(ModelAndView modelAndView, @PathVariable("idUsr") int idUsr) {
		Usuario usuario = new Usuario();
		if (idUsr > 0) {
			usuario = usuarioService.findById(idUsr);
			usuarioService.fillExistingUser(usuario);
		} else {
			usuarioService.fillNewUser(usuario);
		}
//		***********Roles*********** 
		modelAndView.addObject("roles", rolService.findAll());
//		**********************
		modelAndView.addObject("paises", paisService.findAll());
		modelAndView.addObject("usuario", usuario);
		modelAndView.addObject("estoy", "usuario");
		modelAndView.setViewName("usuario");
		return modelAndView;
	}

	@RequestMapping("/usuario/username/{username}")
	public ModelAndView getUserByUsername(ModelAndView modelAndView, @PathVariable("username") String username) {
		modelAndView.addObject("usuario", usuarioService.findByUsername(username));
		modelAndView.addObject("estoy", "username/" + username);
		modelAndView.setViewName("usuario");
		return modelAndView;
	}

	@RequestMapping(value = { "/usuario/save" }, method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("usuario") @Validated(value = SpringFormGroup.class) Usuario usuario,
			BindingResult result, Model model,
			@RequestParam("usuarioRol") String[] usuarioRol) {
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
//			return "usuario";
		}
		List<UsuarioRol> roles = new ArrayList<>();
		for(String rolId:usuarioRol) {
			Rol rol = rolService.findById(Integer.parseInt(rolId));
			UsuarioRolId uri = new UsuarioRolId();
			uri.setRol(rol);
			uri.setUsuario(usuario);
			UsuarioRol ur = new UsuarioRol();
			ur.setPk(uri);
			ur.setRol(rol);
			ur.setUsuario(usuario);
			ur.setFechaCreacion(new Date());
			ur.setCreadoPor("DAMI");
			roles.add(ur);
		}
		usuario.setUsuarioRol(roles);
		usuarioService.save(usuario);
		return "redirect:/usuario";
	}

	@RequestMapping("/usuario/{idUsr}/delete")
	public String deleteUser(@PathVariable("idUsr") int idUsr, RedirectAttributes ra) {

		usuarioService.delete(idUsr);
		ra.addFlashAttribute("resultado", "Cambios realizados con �xito");

		return "redirect:/usuario";

	}

	@RequestMapping("/usuario/cliente")
	public ModelAndView getCustomers(ModelAndView modelAndView) {
//		***********Roles*********** 
		modelAndView.addObject("roles", rolService.findAll());
//		**********************
		modelAndView.addObject("usuarios", usuarioService.findCustomers());
		modelAndView.addObject("estoy", "usuario");
		modelAndView.setViewName("usuarios");
		return modelAndView;
	}
	
	@RequestMapping("/usuario/available/{idUsr}")
	public String changeAvailable(ModelAndView modelAndView, @PathVariable("idUsr") int idUsr) {
		Usuario usuario = new Usuario();
		usuario = usuarioService.findById(idUsr);
		usuario.setHabilitado(!usuario.isHabilitado());
		usuarioService.save(usuario);
		return "redirect:/usuario";
	}

}
