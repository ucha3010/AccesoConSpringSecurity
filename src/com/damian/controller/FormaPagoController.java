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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.FormaPago;
import com.damian.service.FormaPagoService;

@Controller
public class FormaPagoController {

	@Autowired
	private FormaPagoService formaPagoService;

	@RequestMapping("/formaPago")
	public ModelAndView getAll(ModelAndView modelAndView) {
		modelAndView.addObject("formaPagos", formaPagoService.findAll());
		modelAndView.setViewName("formasPago");
		return modelAndView;
	}

	@RequestMapping("/formaPago/{idFor}")
	public ModelAndView getFormaPago(ModelAndView modelAndView, @PathVariable("idFor") int idFor) {
		FormaPago formaPago = new FormaPago();
		if (idFor > 0) {
			formaPago = formaPagoService.findById(idFor);
		}
		modelAndView.addObject("formaPago", formaPago);
		modelAndView.setViewName("formaPago");
		return modelAndView;
	}

	@RequestMapping(value = { "/formaPago/save" }, method = RequestMethod.POST)
	public String saveFormaPago(@ModelAttribute("formaPago") FormaPago formaPago, BindingResult result, Model model,
			RedirectAttributes ra, HttpServletRequest request) {
		if (result.hasErrors()) {
			// System.out.println(result.getAllErrors());
			// return "formaPago";
		}
		boolean nueva = false;
		if (formaPago.getIdFor() == 0) {
			nueva = true;
		}
		formaPagoService.save(formaPago, request);
		if (nueva) {
			ra.addFlashAttribute("formaPago_agregado", "formaPago_agregado");
		}
		return "redirect:/formaPago";
	}

	@RequestMapping("/formaPago/delete/{idFor}")
	public String deleteUser(@PathVariable("idFor") int idFor, RedirectAttributes ra) {

		try {
			formaPagoService.delete(idFor);
			ra.addFlashAttribute("formaPago_eliminado", "formaPago_eliminado");
		} catch (NotEmptyException e) {
			ra.addFlashAttribute("formaPago_asociado", "formaPago_asociado");
		}
		return "redirect:/formaPago";

	}

}
