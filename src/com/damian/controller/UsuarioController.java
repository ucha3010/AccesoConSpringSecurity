package com.damian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.damian.pojo.Usuario;
import com.damian.service.UsuarioService;
import com.damian.valid.SpringFormGroup;


@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping("/usuario")
	public String showForm(Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("usuarios", usuarioService.findAll());
		return"usuario";
	}
	
	@RequestMapping("/usuario/save")
	public String registrar(@ModelAttribute("usuario") @Validated(value=SpringFormGroup.class) Usuario usuario, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return"usuario";
		}
		
		usuarioService.save(usuario);
		
		return "redirect:/usuario";
	}

	

}
