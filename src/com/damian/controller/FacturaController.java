package com.damian.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Factura;
import com.damian.service.EstadoService;
import com.damian.service.FacturaService;

@Controller
public class FacturaController {

	@Autowired
	private FacturaService facturaService;

	@Autowired
	private EstadoService estadoService;

	@RequestMapping("/factura/all/{column}")
	public ModelAndView getAll(ModelAndView modelAndView, @PathVariable("column") String column,
			HttpServletRequest request) {
		List<Factura> facturas = facturaService.findAll(column, request);
		modelAndView.addObject("facturas", facturas);
		List<Factura> vencen = facturaService.selectExpire(facturas);
		modelAndView.addObject("vencen", vencen);
		modelAndView.addObject("estados", estadoService.findAll());
		modelAndView.addObject("estoy", "factura");
		modelAndView.setViewName("facturas");
		return modelAndView;
	}

	@RequestMapping("/factura/filteredEstado/{idEst}/{column}")
	public ModelAndView getFilteredEstado(ModelAndView modelAndView, @PathVariable("idEst") int idEst,
			@PathVariable("column") String column, HttpServletRequest request) {
		modelAndView.addObject("facturas", facturaService.findByIdEstList(idEst, column, request));
		modelAndView.addObject("estados", estadoService.findAll());
		modelAndView.addObject("idEst", idEst);
		modelAndView.addObject("estoy", "filteredEstado");
		modelAndView.setViewName("facturasFilteredEstado");
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

	@RequestMapping("/factura/delete/{idFac}")
	public String deleteFactura(@PathVariable("idFac") int idFac, RedirectAttributes ra) {

		try {
			int cantidad = facturaService.delete(idFac);
			if(cantidad == 0) {
				ra.addFlashAttribute("factura_stock_negativo", "factura_stock_negativo");				
			} else {
				ra.addFlashAttribute("factura_eliminado", "factura_eliminado");
			}
		} catch (NotEmptyException e) {
			ra.addFlashAttribute("factura_asociado", "factura_asociado");
		}
		return "redirect:/factura/all/null";

	}

	@RequestMapping("/factura/status/{idFac}/{idEst}/{column}")
	public ModelAndView changeStatus(ModelAndView modelAndView, @PathVariable("idFac") int idFac,
			@PathVariable("idEst") int idEst, @PathVariable("column") String column, HttpServletRequest request) {
		Factura factura = new Factura();
		factura = facturaService.findById(idFac);
		factura.getEstado().setIdEst(idEst);
		facturaService.update(factura, request);
		return getAll(modelAndView, column, request);
	}

}
