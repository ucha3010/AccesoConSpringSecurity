package com.damian.controller;

import java.util.List;

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

import com.damian.pojo.FacturaEstado;
import com.damian.service.EstadoService;
import com.damian.service.FacturaEstadoService;

@Controller
public class FacturaEstadoController {

	@Autowired
	private FacturaEstadoService facturaEstadoService;

	@Autowired
	private EstadoService estadoService;

	@RequestMapping("/facturaEstado/factura/{idFac}")
	public ModelAndView getFacturas(ModelAndView modelAndView, @PathVariable("idFac") int idFac) {
		List<FacturaEstado> facturaEstados = facturaEstadoService.findByIdFac(idFac);
		modelAndView.addObject("facturaEstados", facturaEstados);
		modelAndView.setViewName("facturaEstados");
		return modelAndView;
	}

	@RequestMapping("/facturaEstado/estado/{idEst}")
	public ModelAndView getEstados(ModelAndView modelAndView, @PathVariable("idEst") int idEst) {
		List<FacturaEstado> facturasEstado = facturaEstadoService.findByIdEst(idEst);
		modelAndView.addObject("estado", estadoService.findByIdModel(idEst));
		modelAndView.addObject("facturasEstado", facturasEstado);
		modelAndView.setViewName("facturasEstado");
		return modelAndView;
	}

	@RequestMapping(value = { "/facturaEstado/update" }, method = RequestMethod.POST)
	public String updateFactura(@ModelAttribute("facturaEstado") FacturaEstado facturaEstado, BindingResult result,
			Model model, HttpServletRequest request, RedirectAttributes ra) {

		facturaEstadoService.update(facturaEstado, request);
		return "redirect:/factura";
	}

}
