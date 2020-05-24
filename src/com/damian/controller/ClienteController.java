package com.damian.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damian.exceptions.RepeatedUsernameException;
import com.damian.pojo.Usuario;
import com.damian.service.PaginacionService;
import com.damian.service.PaisService;
import com.damian.service.RolService;
import com.damian.service.impl.UsuarioServiceImpl;

@Controller
@SessionAttributes({ "resultado", "estoy", "roles" }) // los atributos que pueden mantenerse en sesión y verse en
														// distintas páginas
public class ClienteController {

	@Autowired
	private UsuarioServiceImpl usuarioService;

	@Autowired
	private PaginacionService paginacionService;

	@Autowired
	private PaisService paisService;

	@Autowired
	private RolService rolService;

	@RequestMapping("/cliente/all/{column}/{order}/{paginaInicio}/{totalPaginas}")
	public ModelAndView getAll(ModelAndView modelAndView, @PathVariable("column") String column,
			@PathVariable("order") String order, @PathVariable("paginaInicio") int paginaInicio,
			@PathVariable("totalPaginas") int totalPaginas, HttpServletRequest request) {
		modelAndView.addObject("roles", rolService.findAll());
		modelAndView.addObject("usuarios",
				usuarioService.findCustomers(column, order, paginaInicio, totalPaginas, request));
		modelAndView.addObject("paginacion", paginacionService.pagination(paginaInicio, totalPaginas, "usuario"));
		modelAndView.addObject("buscarusuarios", usuarioService.findSearchAll(true));
		modelAndView.addObject("estoy", "cliente");
		modelAndView.setViewName("clientes");
		return modelAndView;
	}

	@RequestMapping("/cliente/filtered/{idUsr}")
	public ModelAndView getFiltered(ModelAndView modelAndView, @PathVariable("idUsr") int idUsr) {
		modelAndView.addObject("usuarios", usuarioService.findByIdList(idUsr));
		return fillFiltered(modelAndView);
	}

	@RequestMapping("/cliente/cliente/filtered/{idUsr}")
	public ModelAndView getFilteredCustomers(ModelAndView modelAndView, @PathVariable("idUsr") int idUsr) {
		modelAndView.addObject("usuarios", usuarioService.findFilteredCustomers(idUsr));
		return fillFiltered(modelAndView);
	}

	@RequestMapping("/cliente/{idUsr}")
	public ModelAndView getUser(ModelAndView modelAndView, @PathVariable("idUsr") int idUsr,
			@ModelAttribute("usuario") Usuario usuario, final BindingResult br) {

		if (idUsr > 0) {
			usuario = usuarioService.findById(idUsr);
			usuarioService.fillExistingUser(usuario);
		} else if (usuario.getUsuario() == null) {
			usuarioService.fillNewUser(usuario);
		}
		modelAndView.addObject("roles", rolService.findAll());
		modelAndView.addObject("paises", paisService.findAll());
		modelAndView.addObject("usuario", usuario);
		modelAndView.addObject("estoy", "cliente");
		modelAndView.setViewName("cliente");
		return modelAndView;
	}

	@RequestMapping(value = { "/cliente/save" }, method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model,
			@RequestParam(value = "usuarioRol", required = false) String[] usuarioRol, HttpServletRequest request,
			RedirectAttributes ra) {
		if (result.hasErrors()) {
			// System.out.println(result.getAllErrors());
			// return "usuario";
		}
		if (usuarioRol == null) {
			usuarioRol = new String[1];
			usuarioRol[0] = "1";
		}
		boolean nueva = false;
		if (usuario.getIdUsr() == 0) {
			nueva = true;
		}
		try {
			usuarioService.save(usuario, usuarioRol, request);
		} catch (RepeatedUsernameException e) {
			ra.addFlashAttribute("username_existente", "username_existente");
			ra.addFlashAttribute("usuario", usuario);
			return "redirect:/cliente/0";
		}
		if (nueva) {
			ra.addFlashAttribute("usuario_agregado", "usuario_agregado");
		}
		return "redirect:/cliente/all/" + usuarioService.getColumn(request) + "/0/100";
	}

	@RequestMapping("/cliente/{idUsr}/delete")
	public String deleteUser(@PathVariable("idUsr") int idUsr, RedirectAttributes ra, HttpServletRequest request) {

		usuarioService.delete(idUsr);
		ra.addFlashAttribute("usuario_eliminado", "usuario_eliminado");

		return "redirect:/cliente/all/" + usuarioService.getColumn(request) + "/0/100";

	}

	@RequestMapping("/cliente/available/{idUsr}")
	public String changeAvailable(ModelAndView modelAndView, @PathVariable("idUsr") int idUsr,
			HttpServletRequest request) {
		Usuario usuario = new Usuario();
		usuario = usuarioService.findById(idUsr);
		usuario.setHabilitado(!usuario.isHabilitado());
		usuarioService.update(usuario);
		return "redirect:/cliente/all/" + usuarioService.getColumn(request) + "/0/100";
	}

	@RequestMapping("/cliente/reset/{idUsr}")
	public String passwordReset(ModelAndView modelAndView, @PathVariable("idUsr") int idUsr,
			HttpServletRequest request) {
		usuarioService.reset(idUsr);
		return "redirect:/cliente/all/" + usuarioService.getColumn(request) + "/0/100";
	}

	private ModelAndView fillFiltered(ModelAndView modelAndView) {
		modelAndView.addObject("roles", rolService.findAll());
		modelAndView.addObject("paginacion", paginacionService.pagination(0, 0, "usuario"));
		modelAndView.addObject("estoy", "cliente");
		modelAndView.addObject("buscarusuarios", usuarioService.findSearchAll(true));
		modelAndView.setViewName("clientes");
		return modelAndView;

	}

}
