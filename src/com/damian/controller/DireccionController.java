package com.damian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damian.pojo.DatosPersonales;
import com.damian.pojo.Direccion;
import com.damian.pojo.Usuario;
import com.damian.service.DatosPersonalesService;
import com.damian.service.DireccionService;
import com.damian.service.UsuarioService;

@Controller
@SessionAttributes("admin")
public class DireccionController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private DireccionService direccionService;

	@Autowired
	private DatosPersonalesService datosPersonalesService;

	@RequestMapping("/direccion/{idUsr}")
	public String getAll(Model model, @PathVariable("idUsr") int idUsr) {

		model.addAttribute("usuario", usuarioService.findById(idUsr));
		model.addAttribute("direcciones", direccionService.findListFromUsuario(idUsr));

		return "direcciones";
	}

	@RequestMapping("/direccion/formulario/{idDir}")
	public ModelAndView getUser(ModelAndView modelAndView, @PathVariable("idDir") int idDir) {

		Direccion direccion = direccionService.findById(idDir);
		modelAndView.addObject("direccion", direccion);
		modelAndView.addObject("estoy", "direccion");
		modelAndView.setViewName("direccion");
		return modelAndView;
	}

	@RequestMapping("/direccion/save")
	public String save(Model model, RedirectAttributes ra, @ModelAttribute("direccion") Direccion direccion,
			@ModelAttribute("usuario") Usuario usuario) {

		DatosPersonales datosPersonales = datosPersonalesService.findByUsrId(usuario.getIdUsr());
		direccionService.save(datosPersonales, direccion);
		ra.addFlashAttribute("resultado", "Cambios realizados con éxito");

		return "redirect:/direccion/" + usuario.getIdUsr();
	}
}
