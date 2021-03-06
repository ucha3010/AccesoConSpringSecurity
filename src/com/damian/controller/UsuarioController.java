package com.damian.controller;

import java.util.List;

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
import com.damian.pojo.Foto;
import com.damian.pojo.Usuario;
import com.damian.service.FotoService;
import com.damian.service.IndexService;
import com.damian.service.PaginacionService;
import com.damian.service.PaisService;
import com.damian.service.RolService;
import com.damian.service.impl.UsuarioServiceImpl;

@Controller
@SessionAttributes({ "resultado", "estoy", "roles", "errorUsuario", "idUsrLogged", "nameUsrLogged",  "prinPicUsr", "prefUsr" })
public class UsuarioController {

	@Autowired
	private FotoService fotoService;

	@Autowired
	private IndexService indexService;

	@Autowired
	private PaginacionService paginacionService;

	@Autowired
	private PaisService paisService;

	@Autowired
	private RolService rolService;

	@Autowired
	private UsuarioServiceImpl usuarioService;

	@RequestMapping("/usuario/all/{column}/{order}/{paginaInicio}/{totalPaginas}")
	public ModelAndView getAll(ModelAndView modelAndView, @PathVariable("column") String column,
			@PathVariable("order") String order, @PathVariable("paginaInicio") int paginaInicio,
			@PathVariable("totalPaginas") int totalPaginas, HttpServletRequest request) {
		indexService.idUserLogged(modelAndView);
		modelAndView.addObject("usuarios", usuarioService.findAll(column, order, paginaInicio, totalPaginas, request));
		modelAndView.addObject("buscarusuarios", usuarioService.findSearchAll(false));
		modelAndView.addObject("paginacion", paginacionService.pagination(paginaInicio, totalPaginas, "usuario"));
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
		modelAndView.addObject("paginacion", paginacionService.pagination(0, 0, "usuario"));
		modelAndView.addObject("buscarusuarios", usuarioService.findSearchAll(false));
		modelAndView.setViewName("usuarios");
		return modelAndView;
	}

	@RequestMapping("/usuario/idUsr/{idUsr}")
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

	@RequestMapping("/usuario/logged")
	public ModelAndView getLogged(ModelAndView modelAndView) {
		int idUsr = indexService.idUserLogged(modelAndView);
		return getLoggedUser(modelAndView, idUsr);
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
			@RequestParam(value = "usuarioRol", required = false) String[] usuarioRol,
			@RequestParam(value = "anda", required = false) String[] anda, HttpServletRequest request,
			RedirectAttributes ra) {
		if (result.hasErrors()) {
			// System.out.println(result.getAllErrors());
			// return "usuario";
		}
		if (usuarioRol == null && anda == null) {
			usuarioRol = new String[1];
			usuarioRol[0] = "1";
		} else if (anda != null) {
			usuarioRol = anda;
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
		return "redirect:/usuario/all/" + usuarioService.getColumn(request) + "/0/100";
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
			@RequestParam(value = "anda", required = false) String[] anda, HttpServletRequest request) {

		if (anda == null) {
			anda = new String[1];
			anda[0] = "1";
		}
		try {
			usuarioService.save(usuario, anda, request);
		} catch (RepeatedUsernameException e) {
		}
		return "redirect:/";
	}

	@RequestMapping(value = { "/usuario/logged/changePass/save" }, method = RequestMethod.POST)
	public String saveChangeUserPass(@ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model,
			RedirectAttributes ra, HttpServletRequest request) {

		usuarioService.saveChangePassword(usuario, request);
		ra.addFlashAttribute("passChanged", "label.Passwrod.change.success");
		return "redirect:/usuario/logged/" + usuario.getIdUsr();
	}

	@RequestMapping("/usuario/delete/{idUsr}")
	public String deleteUser(@PathVariable("idUsr") int idUsr, RedirectAttributes ra, HttpServletRequest request) {

		usuarioService.delete(idUsr, request);
		ra.addFlashAttribute("usuario_eliminado", "usuario_eliminado");

		return "redirect:/usuario/all/" + usuarioService.getColumn(request) + "/0/100";

	}

	@RequestMapping("/usuario/deleteUsr/{idUsr}")
	public String deleteUserByUser(@PathVariable("idUsr") int idUsr, RedirectAttributes ra,
			HttpServletRequest request) {

		usuarioService.delete(idUsr, request);
		ra.addFlashAttribute("usuario_eliminado", "usuario_eliminado");

		return "redirect:/";

	}

	@RequestMapping("/usuario/available/{idUsr}")
	public String changeAvailable(ModelAndView modelAndView, @PathVariable("idUsr") int idUsr,
			HttpServletRequest request) {
		Usuario usuario = new Usuario();
		usuario = usuarioService.findById(idUsr);
		usuario.setHabilitado(!usuario.isHabilitado());
		usuarioService.update(usuario, request);
		return "redirect:/usuario/all/" + usuarioService.getColumn(request) + "/0/100";
	}

	@RequestMapping("/usuario/reset/{idUsr}")
	public String passwordReset(ModelAndView modelAndView, @PathVariable("idUsr") int idUsr,
			HttpServletRequest request) {
		usuarioService.reset(idUsr, request);
		return "redirect:/usuario/all/" + usuarioService.getColumn(request) + "/0/100";
	}

	@RequestMapping("/usuario/publicity/{receive}")
	public ModelAndView getPublicity(ModelAndView modelAndView, @PathVariable("receive") boolean receive,
			HttpServletRequest request) {

		modelAndView.addObject("usuarios", usuarioService.findByPublicity(receive));
		modelAndView.addObject("quieren_publicidad", receive);
		modelAndView.addObject("estoy", "usuariosRecibirPublicidad");
		modelAndView.setViewName("usuariosRecibirPublicidad");
		return modelAndView;
	}

	private void fillLoggedUser(ModelAndView modelAndView, int idUsr) {
		Usuario usuario = new Usuario();
		if (idUsr > 0) {
			usuario = usuarioService.findById(idUsr);
			usuarioService.fillExistingUser(usuario);
			List<Foto> fotos = fotoService.findByIdUsr(idUsr);
			modelAndView.addObject("prinPicUsr", fotoService.principalPictureName(fotos));
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
