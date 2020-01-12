package com.damian.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
import com.damian.service.PaisService;
import com.damian.service.RolService;
import com.damian.service.impl.UsuarioServiceImpl;

@Controller
@SessionAttributes({ "resultado", "estoy", "roles" }) // los atributos que pueden mantenerse en sesión y verse en
														// distintas páginas
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
		modelAndView.addObject("roles", rolService.findAll());
		modelAndView.addObject("estoy", "usuario");
		modelAndView.setViewName("usuarios");
		return modelAndView;
	}

	@RequestMapping("/usuario/filtered/{idUsr}")
	public ModelAndView getFiltered(ModelAndView modelAndView, @PathVariable("idUsr") int idUsr) {
		modelAndView.addObject("usuarios", usuarioService.findByIdList(idUsr));
		modelAndView.addObject("roles", rolService.findAll());
		modelAndView.addObject("estoy", "usuario");
		modelAndView.setViewName("usuarios");
		return modelAndView;
	}

	@RequestMapping("/usuario/{idUsr}")
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
		modelAndView.addObject("estoy", "usuario");
		modelAndView.setViewName("usuario");
		return modelAndView;
	}

	@RequestMapping("/usuario/logged/{idUsr}")
	public ModelAndView getLoggedUser(ModelAndView modelAndView, @PathVariable("idUsr") int idUsr) {
		fillLoggedUser(modelAndView, idUsr);
		modelAndView.setViewName("usuarioLogged");
		return modelAndView;
	}

	@RequestMapping("/usuario/logged/changePass/{idUsr}")
	public ModelAndView getChangePassword(ModelAndView modelAndView, @PathVariable("idUsr") int idUsr) {
		fillLoggedUser(modelAndView, idUsr);
		modelAndView.setViewName("usuarioChangePassword");
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
			return "redirect:/usuario/0";
		}
		if (nueva) {
			ra.addFlashAttribute("usuario_agregado", "usuario_agregado");
		}
		return "redirect:/usuario";
	}

	@RequestMapping(value = "/usuario/nuevo", method = RequestMethod.GET)
	public ModelAndView getUser(ModelAndView modelAndView, @ModelAttribute("usuario") Usuario usuario,
			final BindingResult br, HttpServletRequest request) {
		if (usuario.getUsuario() == null) {
			usuarioService.fillNewUser(usuario);
		}
		modelAndView.addObject("paises", paisService.findAll());
		modelAndView.addObject("usuario", usuario);
		modelAndView.addObject("estoy", "index");
		modelAndView.setViewName("usuarioNuevo");
		return modelAndView;
	}

	@RequestMapping(value = { "/usuario/nuevo/save" }, method = RequestMethod.POST)
	public ModelAndView saveNewUser(@ModelAttribute("usuario") Usuario usuario, final BindingResult br,
			final ModelMap model, HttpServletRequest request, final RedirectAttributes ra) {

		String[] usuarioRol = new String[1];
		usuarioRol[0] = "1";
		try {
			usuarioService.save(usuario, usuarioRol, request);
		} catch (RepeatedUsernameException e) {
			ra.addFlashAttribute("usuario", usuario);
			ra.addFlashAttribute("username_existente", "username_existente");
			return new ModelAndView("redirect:/usuario/nuevo");
		}
		ra.addFlashAttribute("usuario_creado", "usuario_creado");
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = { "/usuario/logged/save" }, method = RequestMethod.POST)
	public String saveLoggedUser(@ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model,
			@RequestParam(value = "usuarioRol", required = false) String[] usuarioRol, HttpServletRequest request) {

		if (usuarioRol == null) {
			usuarioRol = new String[1];
			usuarioRol[0] = "1";
		}
		try {
			usuarioService.save(usuario, usuarioRol, request);
		} catch (RepeatedUsernameException e) {
		}
		return "redirect:/";
	}

	@RequestMapping(value = { "/usuario/logged/changePass/save" }, method = RequestMethod.POST)
	public String saveChangeUserPass(@ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model,
			RedirectAttributes ra) {

		usuarioService.saveChangePassword(usuario);
		ra.addFlashAttribute("passChanged", "label.Passwrod.change.success");
		return "redirect:/usuario/logged/" + usuario.getIdUsr();
	}

	@RequestMapping("/usuario/{idUsr}/delete")
	public String deleteUser(@PathVariable("idUsr") int idUsr, RedirectAttributes ra) {

		usuarioService.delete(idUsr);
		ra.addFlashAttribute("usuario_eliminado", "usuario_eliminado");

		return "redirect:/usuario";

	}

	@RequestMapping("/usuario/cliente")
	public ModelAndView getCustomers(ModelAndView modelAndView) {
		modelAndView.addObject("roles", rolService.findAll());
		modelAndView.addObject("usuarios", usuarioService.findCustomers());
		modelAndView.addObject("estoy", "usuario");
		modelAndView.setViewName("usuarios");
		return modelAndView;
	}

	@RequestMapping("/usuario/cliente/filtered/{idUsr}")
	public ModelAndView getFilteredCustomers(ModelAndView modelAndView, @PathVariable("idUsr") int idUsr) {
		modelAndView.addObject("roles", rolService.findAll());
		modelAndView.addObject("usuarios", usuarioService.findFilteredCustomers(idUsr));
		modelAndView.addObject("estoy", "usuario");
		modelAndView.setViewName("usuarios");
		return modelAndView;
	}

	@RequestMapping("/usuario/available/{idUsr}")
	public String changeAvailable(ModelAndView modelAndView, @PathVariable("idUsr") int idUsr) {
		Usuario usuario = new Usuario();
		usuario = usuarioService.findById(idUsr);
		usuario.setHabilitado(!usuario.isHabilitado());
		usuarioService.update(usuario);
		return "redirect:/usuario";
	}

	@RequestMapping("/usuario/reset/{idUsr}")
	public String passwordReset(ModelAndView modelAndView, @PathVariable("idUsr") int idUsr) {
		usuarioService.reset(idUsr);
		return "redirect:/usuario";
	}

	private void fillLoggedUser(ModelAndView modelAndView, int idUsr) {
		Usuario usuario = new Usuario();
		if (idUsr > 0) {
			usuario = usuarioService.findById(idUsr);
			usuarioService.fillExistingUser(usuario);
		} else {
			modelAndView.setViewName("index");
			return;
		}
		modelAndView.addObject("roles", rolService.findAll());
		modelAndView.addObject("paises", paisService.findAll());
		modelAndView.addObject("usuario", usuario);
		modelAndView.addObject("estoy", "usuario");
	}

}
