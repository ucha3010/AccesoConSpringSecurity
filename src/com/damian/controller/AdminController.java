package com.damian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damian.pojo.Admin;
import com.damian.service.impl.AdminServiceImpl;

@Controller
@SessionAttributes({"resultado","nombre","valor","estoy"}) //los atributos que pueden mantenerse en sesión y verse en distintas páginas
public class AdminController {

	@Autowired
	private AdminServiceImpl adminService;

	@RequestMapping("/admin")
	public ModelAndView showAdmin(ModelAndView model, @ModelAttribute("resultado") String resultado) {

		List<Admin> admins = adminService.findAll();
		model.addObject("admin", new Admin());
		model.addObject("resultado", resultado);
		model.addObject("nombre", "En admin meto nombre");
		model.addObject("admins", admins);
		model.addObject("estoy", "admin");
		model.setViewName("admin");

		return model;
	}

	@RequestMapping(value = "/admin/save", method = RequestMethod.POST)
	public String handleAdmin(@ModelAttribute("admin") Admin adminForm, Model model, RedirectAttributes ra) {

		adminService.saveOrUpdate(adminForm);
		ra.addFlashAttribute("resultado", "Cambios realizados con éxito");

		return "redirect:/admin";
	}

	@RequestMapping("/admin/{idAd}/update")
	public String showUpdate(Model model, @PathVariable("idAd") int id) {
		
		Admin admin = adminService.findById(id);
		model.addAttribute("admin",admin);

		return "admin";
	}

	@RequestMapping("/admin/{idAd}/delete")
	public String delete(@PathVariable("idAd") int idAd, RedirectAttributes ra) {
		
		adminService.delete(idAd);
		ra.addFlashAttribute("resultado", "Cambios realizados con éxito");
		
		return "redirect:/admin";

	}

}
