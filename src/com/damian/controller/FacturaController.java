package com.damian.controller;

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
import com.damian.pojo.Factura;
import com.damian.service.FacturaService;

@Controller
public class FacturaController {

	@Autowired
	private FacturaService facturaService;

	@RequestMapping("/factura")
	public ModelAndView getAll(ModelAndView modelAndView) {
		modelAndView.addObject("facturas", facturaService.findAll());
		modelAndView.setViewName("facturas");
		return modelAndView;
	}

	@RequestMapping("/factura/{idFac}")
	public ModelAndView getFactura(ModelAndView modelAndView, @PathVariable("idFac") int idFac) {
		Factura factura = new Factura();
		if (idFac > 0) {
			factura = facturaService.findById(idFac);
		}
		modelAndView.addObject("factura", factura);
		modelAndView.setViewName("factura");
		return modelAndView;
	}

	@RequestMapping(value = { "/factura/save" }, method = RequestMethod.POST)
	public String saveFactura(@ModelAttribute("factura") Factura factura, BindingResult result, Model model,
			RedirectAttributes ra) {
		if (result.hasErrors()) {
			// System.out.println(result.getAllErrors());
			// return "factura";
		}
		boolean nueva = false;
		if (factura.getIdFac() == 0) {
			nueva = true;
		}
		facturaService.save(factura);
		if (nueva) {
			ra.addFlashAttribute("factura_agregado", "factura_agregado");
		}
		return "redirect:/factura";
	}

	@RequestMapping("/factura/delete/{idFac}")
	public String deleteUser(@PathVariable("idFac") int idFac, RedirectAttributes ra) {

		try {
			facturaService.delete(idFac);
			ra.addFlashAttribute("factura_eliminado", "factura_eliminado");
		} catch (NotEmptyException e) {
			ra.addFlashAttribute("factura_asociado", "factura_asociado");
		}
		return "redirect:/factura";

	}

}
