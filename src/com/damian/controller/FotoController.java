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

import com.damian.pojo.EmpresaPropia;
import com.damian.pojo.Foto;
import com.damian.pojo.Marca;
import com.damian.pojo.Producto;
import com.damian.pojo.Usuario;
import com.damian.service.EmpresaPropiaService;
import com.damian.service.FotoService;
import com.damian.service.IndexService;
import com.damian.service.MarcaService;
import com.damian.service.ProductoService;
import com.damian.service.UsuarioService;

@Controller
@SessionAttributes({ "resultado", "estoy", "roles", "prinPicUsr" })

public class FotoController {

	@Autowired
	private EmpresaPropiaService empresaPropiaService;

	@Autowired
	private FotoService fotoService;

	@Autowired
	private IndexService indexService;

	@Autowired
	private MarcaService marcaService;

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

	@RequestMapping("/foto/slide")
	public ModelAndView getSlideFotos(ModelAndView modelAndView) {

		int idUsr = indexService.idUserLogged(modelAndView);
		if (idUsr != 0) {
			Usuario usuario = new Usuario();
			usuario.setIdUsr(idUsr);
			modelAndView.addObject("usuario", usuario);
			modelAndView.addObject("fotos", fotoService.findBySlide());
			Foto foto = new Foto();
			foto.setSlide(true);
			modelAndView.addObject("foto", foto);
			modelAndView.setViewName("administrarFotosIndex");
		}
		return modelAndView;

	}

	@RequestMapping("/foto/empresaPropia/{idPropia}")
	public ModelAndView getEmpresaPropiaFoto(ModelAndView modelAndView, @PathVariable("idPropia") int idPropia) {
		EmpresaPropia empresaPropia = empresaPropiaService.findById(idPropia);
		modelAndView.addObject("empresaPropia", empresaPropia);
		modelAndView.addObject("fotos", fotoService.findByIdPropia(idPropia));
		Foto foto = new Foto();
		foto.setEmpresaPropia(empresaPropia);
		modelAndView.addObject("foto", foto);
		modelAndView.addObject("estoy", "empresaPropiaFotoUbicacion");
		modelAndView.setViewName("empresaPropiaFotoUbicacion");
		return modelAndView;
	}

	@RequestMapping("/foto/marca/{idMar}")
	public ModelAndView getMarcaFoto(ModelAndView modelAndView, @PathVariable("idMar") int idMar) {
		Marca marca = marcaService.findById(idMar);
		modelAndView.addObject("marca", marca);
		modelAndView.addObject("fotos", fotoService.findByIdMar(idMar));
		Foto foto = new Foto();
		foto.setMarca(marca);
		modelAndView.addObject("foto", foto);
		modelAndView.addObject("estoy", "marcaFoto");
		modelAndView.setViewName("marcaFoto");
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

	@RequestMapping(value = { "/foto/slide/save" }, method = RequestMethod.POST)
	public String saveSlide(@RequestParam("file") MultipartFile file, @ModelAttribute("foto") Foto foto,
			RedirectAttributes ra, HttpServletRequest request) {

		foto.setSlide(true);
		saveFoto(foto, file, request, ra);
		return "redirect:/foto/slide";
	}

	@RequestMapping(value = { "/foto/empresaPropia/save" }, method = RequestMethod.POST)
	public String saveFotoEmpresaPropia(@RequestParam("file") MultipartFile file, @ModelAttribute("foto") Foto foto,
			RedirectAttributes ra, HttpServletRequest request) {

		saveFoto(foto, file, request, ra);
		return "redirect:/foto/empresaPropia/" + foto.getEmpresaPropia().getIdPropia();
	}

	@RequestMapping(value = { "/foto/marca/save" }, method = RequestMethod.POST)
	public String saveFotoMarca(@RequestParam("file") MultipartFile file, @ModelAttribute("foto") Foto foto,
			RedirectAttributes ra, HttpServletRequest request) {

		saveFoto(foto, file, request, ra);
		return "redirect:/foto/marca/" + foto.getMarca().getIdMar();
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

	@RequestMapping("/foto/slide/delete/{idFot}")
	public String deleteSlide(@PathVariable("idFot") int idFot, RedirectAttributes ra, HttpServletRequest request) {

		fotoService.delete(idFot, request);
		ra.addFlashAttribute("foto_eliminada", "foto_eliminada");
		return "redirect:/foto/slide";

	}

	@RequestMapping("/foto/empresaPropia/delete/{idFot}")
	public String deleteEmpresaPropia(@PathVariable("idFot") int idFot, RedirectAttributes ra,
			HttpServletRequest request) {

		Foto foto = fotoService.delete(idFot, request);
		ra.addFlashAttribute("foto_eliminada", "foto_eliminada");
		return "redirect:/foto/empresaPropia/" + foto.getEmpresaPropia().getIdPropia();

	}

	@RequestMapping("/foto/marca/delete/{idFot}")
	public String deleteMarca(@PathVariable("idFot") int idFot, RedirectAttributes ra, HttpServletRequest request) {

		Foto foto = fotoService.delete(idFot, request);
		ra.addFlashAttribute("foto_eliminada", "foto_eliminada");
		return "redirect:/foto/marca/" + foto.getMarca().getIdMar();

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
