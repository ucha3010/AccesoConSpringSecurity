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
import com.damian.service.PaisService;
import com.damian.service.UsuarioService;

@Controller
@SessionAttributes({"resultado","estoy"})
public class DireccionController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private DireccionService direccionService;

	@Autowired
	private DatosPersonalesService datosPersonalesService;
	
	@Autowired
	private PaisService paisService;

	@RequestMapping("/direccion/{idUsr}")
	public String getAll(Model model, @PathVariable("idUsr") int idUsr) {

		model.addAttribute("usuario", usuarioService.findById(idUsr));
		model.addAttribute("direcciones", direccionService.findListFromUsuario(idUsr));

		return "direcciones";
	}

	@RequestMapping("/direccion/formulario/{idDir}/{idDatosPers}")
	public ModelAndView getUser(ModelAndView modelAndView, @PathVariable("idDir") int idDir, @PathVariable("idDatosPers") int idDatosPers,
			@ModelAttribute("usuario") Usuario usuario) {

		Direccion direccion;
		if(idDir > 0) {
			direccion = direccionService.findById(idDir);
		} else {
			direccion = new Direccion();
			DatosPersonales datosPersonales = datosPersonalesService.findById(idDatosPers);
			direccion.setDatosPersonales(datosPersonales);
		}
		modelAndView.addObject("direccion", direccion);
		modelAndView.addObject("estoy", "direccion");
		modelAndView.addObject("paises", paisService.findAll());
		modelAndView.setViewName("direccion");
		return modelAndView;
	}

	@RequestMapping("/direccion/save/{idUsr}")
	public String save(Model model, RedirectAttributes ra, @ModelAttribute("direccion") Direccion direccion, @PathVariable("idUsr") int idUsr) {

		DatosPersonales datosPersonales = datosPersonalesService.findByUsrId(idUsr);
		direccionService.save(datosPersonales, direccion);
		ra.addFlashAttribute("resultado", "Cambios realizados con éxito");

		return "redirect:/direccion/" + idUsr;
	}

	@RequestMapping("/direccion/delete/{idDir}")
	public String deleteUser(@PathVariable("idDir") int idDir, RedirectAttributes ra) {
		
		direccionService.delete(idDir);
		ra.addFlashAttribute("resultado", "Cambios realizados con éxito");
		
		return "redirect:/usuario";

	}
}
