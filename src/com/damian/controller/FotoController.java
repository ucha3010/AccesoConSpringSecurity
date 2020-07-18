package com.damian.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damian.pojo.Foto;
import com.damian.pojo.Producto;
import com.damian.pojo.Usuario;
import com.damian.service.FotoService;
import com.damian.service.ProductoService;
import com.damian.service.UsuarioService;

@Controller
@SessionAttributes({ "resultado", "estoy", "roles", "prinPicUsr" })

public class FotoController {

	@Autowired
	private FotoService fotoService;

	@Autowired
	private ProductoService productoService;

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
		List<Foto> fotos = fotoService.findByIdUsr(idUsr);
		modelAndView.addObject("fotos", fotos);
		modelAndView.addObject("prinPicUsr", fotoService.principalPictureName(fotos));
		Foto foto = new Foto();
		Usuario usuario = new Usuario();
		foto.setUsuario(usuario);
		modelAndView.addObject("foto", foto);
		modelAndView.setViewName("usuarioFotos");
		return modelAndView;

	}

	@RequestMapping("/foto/producto/{idPro}")
	public ModelAndView getProductoFotos(ModelAndView modelAndView, @PathVariable("idPro") int idPro) {

		modelAndView.addObject("producto", productoService.findById(idPro));
		modelAndView.addObject("fotos", fotoService.findByIdPro(idPro));
		Foto foto = new Foto();
		Producto producto = new Producto();
		foto.setProducto(producto);
		modelAndView.addObject("foto", foto);
		modelAndView.setViewName("productoFotos");
		return modelAndView;

	}

	@RequestMapping(value = { "/foto/usuarioLogged/save" }, method = RequestMethod.POST)
	public String saveUsuario(@RequestParam("file") MultipartFile file, @ModelAttribute("foto") Foto foto,
			RedirectAttributes ra, HttpServletRequest request) {

		saveFoto(foto, file, request, ra);
		return "redirect:/foto/usuarioLogged/" + foto.getUsuario().getIdUsr();
	}

	@RequestMapping(value = { "/foto/producto/save" }, method = RequestMethod.POST)
	public String saveProducto(@RequestParam("file") MultipartFile file, @ModelAttribute("foto") Foto foto,
			RedirectAttributes ra, HttpServletRequest request) {

		saveFoto(foto, file, request, ra);
		return "redirect:/foto/producto/" + foto.getProducto().getIdPro();
	}

	@RequestMapping("/foto/usuarioLogged/delete/{idFot}")
	public String deleteUsuario(@PathVariable("idFot") int idFot, RedirectAttributes ra, HttpServletRequest request) {

		Foto foto = fotoService.delete(idFot, request);
		ra.addFlashAttribute("foto_eliminada", "foto_eliminada");
		return "redirect:/foto/usuarioLogged/" + foto.getUsuario().getIdUsr();

	}

	@RequestMapping("/foto/producto/delete/{idFot}")
	public String deleteProducto(@PathVariable("idFot") int idFot, RedirectAttributes ra, HttpServletRequest request) {

		Foto foto = fotoService.delete(idFot, request);
		ra.addFlashAttribute("foto_eliminada", "foto_eliminada");
		return "redirect:/foto/producto/" + foto.getProducto().getIdPro();

	}

	@RequestMapping("/foto/usuarioLogged/principal/{idFot}")
	public String doPrincipalUsuario(ModelAndView modelAndView, @PathVariable("idFot") int idFot, RedirectAttributes ra,
			HttpServletRequest request) {

		Foto foto = fotoService.doPrincipal(idFot, request);
		return "redirect:/foto/usuarioLogged/" + foto.getUsuario().getIdUsr();

	}

	@RequestMapping("/foto/producto/principal/{idFot}")
	public String doPrincipalProducto(ModelAndView modelAndView, @PathVariable("idFot") int idFot,
			HttpServletRequest request) {

		Foto foto = fotoService.doPrincipal(idFot, request);
		return "redirect:/foto/producto/" + foto.getProducto().getIdPro();

	}
	
	private void saveFoto(Foto foto, MultipartFile file, HttpServletRequest request, RedirectAttributes ra) {

		int agregada = 0;
		if (!file.isEmpty()) {
			agregada = fotoService.save(foto, file, request);
		}
		if (agregada > 0) {
			ra.addFlashAttribute("foto_agregada", true);
		} else {
			ra.addFlashAttribute("foto_agregada", false);
		}
		
	}

}
