package com.damian.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damian.pojo.Foto;
import com.damian.pojo.Usuario;
import com.damian.service.FotoService;
import com.damian.service.UsuarioService;

@Controller
public class FotoController {

	@Autowired
	private FotoService fotoService;

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping("/foto/{idFot}")
	public ModelAndView getFoto(ModelAndView modelAndView, @PathVariable("idFot") int idFot) {
		Foto foto = new Foto();
		if (idFot > 0) {
			foto = fotoService.findByIdFot(idFot);
		}
		modelAndView.addObject("foto", foto);
		modelAndView.setViewName("");
		return modelAndView;
	}

	@RequestMapping("/foto/usuarioLogged/{idUsr}")
	public ModelAndView getUsuarioFotos(ModelAndView modelAndView, @PathVariable("idUsr") int idUsr) {

		modelAndView.addObject("usuario", usuarioService.findById(idUsr));
		modelAndView.addObject("fotos", fotoService.findByIdUsr(idUsr));
		Foto foto = new Foto();
		Usuario usuario = new Usuario();
		foto.setUsuario(usuario);
		modelAndView.addObject("foto", foto);
		modelAndView.setViewName("usuarioFotos");
		return modelAndView;

	}

	@RequestMapping(value = { "/foto/usuarioLogged/save" }, method = RequestMethod.POST)
	public String save(@RequestParam("file") MultipartFile file, @ModelAttribute("foto") Foto foto,
			RedirectAttributes ra, HttpServletRequest request) {

		if (!file.isEmpty()) {
			fotoService.save(foto, file, request);
			ra.addFlashAttribute("foto_agregada", true);
		} else {
			ra.addFlashAttribute("foto_agregada", false);
		}

		return "redirect:/foto/usuarioLogged/" + foto.getUsuario().getIdUsr();
	}

	@RequestMapping("/foto/usuarioLogged/delete/{idUsr}/{idFot}")
	public String delete(@PathVariable("idUsr") int idUsr, @PathVariable("idFot") int idFot, RedirectAttributes ra,
			HttpServletRequest request) {

		fotoService.delete(idFot, request);
		ra.addFlashAttribute("foto_eliminada", "foto_eliminada");
		return "redirect:/foto/usuarioLogged/" + idUsr;

	}

	@RequestMapping("/foto/usuarioLogged/principal/{idFot}")
	public String doPrincipal(ModelAndView modelAndView, @PathVariable("idFot") int idFot, HttpServletRequest request) {

		return "redirect:/foto/usuarioLogged/" + fotoService.doPrincipal(idFot, request);

	}

}
