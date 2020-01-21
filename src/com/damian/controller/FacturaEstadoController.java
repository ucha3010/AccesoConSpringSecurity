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
import com.damian.service.FacturaService;

@Controller
public class FacturaEstadoController {

	@Autowired
	private FacturaEstadoService facturaEstadoService;

	@Autowired
	private FacturaService facturaService;

	@Autowired
	private EstadoService estadoService;

	@RequestMapping("/facturaEstado/factura/{idFac}")
	public ModelAndView getFacturas(ModelAndView modelAndView, @PathVariable("idFac") int idFac) {
		modelAndView.addObject("factura", facturaService.findByIdModel(idFac));
		modelAndView.addObject("facturaEstado", new FacturaEstado());
		modelAndView.addObject("facturaEstados", facturaEstadoService.findByIdFac(idFac));
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
	public String updateFacturaEstado(@ModelAttribute("facturaEstado") FacturaEstado facturaEstado,
			BindingResult result, Model model, HttpServletRequest request, RedirectAttributes ra) {

		facturaEstadoService.update(facturaEstado, request);
		return "redirect:/factura";
	}

	@RequestMapping(value = { "/facturaEstado/update/observaciones" }, method = RequestMethod.POST)
	public String updateFacturaEstadoObservaciones(@ModelAttribute("facturaEstado") FacturaEstado facturaEstado,
			BindingResult result, Model model, HttpServletRequest request, RedirectAttributes ra) {

		String observaciones = facturaEstado.getObservaciones();
		facturaEstado = facturaEstadoService.findById(facturaEstado.getId());
		facturaEstado.setObservaciones(observaciones);
		facturaEstadoService.update(facturaEstado, request);
		ra.addFlashAttribute("facturaEstado_id_observaciones", facturaEstado.getId());
		return "redirect:/facturaEstado/factura/" + facturaEstado.getFactura().getIdFac();
	}

}
