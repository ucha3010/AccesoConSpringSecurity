package com.damian.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damian.dao.model.ModelCuotaDetalle;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.Factura;
import com.damian.pojo.front.ImpresionFactura;
import com.damian.service.CuotaDetalleService;
import com.damian.service.EstadoService;
import com.damian.service.FacturaService;
import com.damian.service.IndexService;
import com.damian.service.PaginacionService;

@Controller
@SessionAttributes({ "resultado", "estoy", "errorUsuario", "idUsrLogged", "nameUsrLogged", "prinPicUsr", "prefUsr" })
public class FacturaController {

	@Autowired
	private FacturaService facturaService;

	@Autowired
	private CuotaDetalleService cuotaDetalleService;

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private IndexService indexService;

	@Autowired
	private PaginacionService paginacionService;

	@RequestMapping("/factura/all/{column}/{paginaInicio}/{totalPaginas}")
	public ModelAndView getAll(ModelAndView modelAndView, @PathVariable("column") String column,
			@PathVariable("paginaInicio") int paginaInicio, @PathVariable("totalPaginas") int totalPaginas,
			HttpServletRequest request) {
		indexService.idUserLogged(modelAndView);
		List<Factura> facturas = facturaService.findSearchAll();
		modelAndView.addObject("facturas", facturaService.findAll(column, paginaInicio, totalPaginas, request));
		modelAndView.addObject("paginacion", paginacionService.pagination(paginaInicio, totalPaginas, "factura"));
		modelAndView.addObject("buscarfacturas", facturas);
		List<Factura> vencen = facturaService.selectExpire(facturas);
		modelAndView.addObject("vencen", vencen);
		modelAndView.addObject("estados", estadoService.findAll());
		modelAndView.addObject("estoy", "factura");
		modelAndView.setViewName("facturas");
		return modelAndView;
	}

	@RequestMapping("/factura/filtered/{idFac}")
	public ModelAndView getFiltered(ModelAndView modelAndView, @PathVariable("idFac") int idFac) {
		modelAndView.addObject("facturas", facturaService.findByIdList(idFac));
		modelAndView.addObject("paginacion", paginacionService.pagination(0, 0, "factura"));
		modelAndView.addObject("buscarfacturas", facturaService.findSearchAll());
		modelAndView.addObject("estados", estadoService.findAll());
		modelAndView.addObject("estoy", "factura");
		modelAndView.setViewName("facturas");
		return modelAndView;
	}

	@RequestMapping("/factura/filteredEstado/{idEst}/{column}/{paginaInicio}/{totalPaginas}")
	public ModelAndView getFilteredEstado(ModelAndView modelAndView, @PathVariable("idEst") int idEst,
			@PathVariable("column") String column, @PathVariable("paginaInicio") int paginaInicio,
			@PathVariable("totalPaginas") int totalPaginas, HttpServletRequest request) {
		modelAndView.addObject("facturas", facturaService.findByIdEstList(idEst, column, request));
		modelAndView.addObject("paginacion", paginacionService.pagination(paginaInicio, totalPaginas, "factura"));
		modelAndView.addObject("estados", estadoService.findAll());
		modelAndView.addObject("estadoActual", estadoService.findByIdModel(idEst));
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
	public String deleteFactura(@PathVariable("idFac") int idFac, RedirectAttributes ra, HttpServletRequest request) {

		try {
			int cantidad = facturaService.delete(idFac, request);
			if (cantidad == 0) {
				ra.addFlashAttribute("factura_stock_negativo", "factura_stock_negativo");
			} else {
				ra.addFlashAttribute("factura_eliminado", "factura_eliminado");
			}
		} catch (NotEmptyException e) {
			ra.addFlashAttribute("factura_asociado", "factura_asociado");
		}
		return "redirect:/factura/all/null/0/100";

	}

	@RequestMapping("/factura/delete/filteredEstado/{idFac}/{idEst}/{column}/{paginaInicio}/{totalPaginas}")
	public String deleteFacturaFilteredEstado(ModelAndView modelAndView, @PathVariable("idFac") int idFac, @PathVariable("idEst") int idEst,
			@PathVariable("column") String column, @PathVariable("paginaInicio") int paginaInicio,
			@PathVariable("totalPaginas") int totalPaginas, RedirectAttributes ra, HttpServletRequest request) {

		try {
			int cantidad = facturaService.delete(idFac, request);
			if (cantidad == 0) {
				ra.addFlashAttribute("factura_stock_negativo", "factura_stock_negativo");
			} else {
				ra.addFlashAttribute("factura_eliminado", "factura_eliminado");
			}
		} catch (NotEmptyException e) {
			ra.addFlashAttribute("factura_asociado", "factura_asociado");
		}
		return "redirect:/factura/filteredEstado/" + idEst + "/" + column + "/" + paginaInicio + "/" + totalPaginas;

	}

	@RequestMapping("/factura/status/{idFac}/{idEst}/{column}/{paginaInicio}/{totalPaginas}/{from}/{idEstView}")
	public ModelAndView changeStatus(ModelAndView modelAndView, @PathVariable("idFac") int idFac,
			@PathVariable("idEst") int idEst, @PathVariable("column") String column,
			@PathVariable("paginaInicio") int paginaInicio, @PathVariable("totalPaginas") int totalPaginas,
			@PathVariable("from") String from, @PathVariable("idEstView") int idEstView, HttpServletRequest request) {
		Factura factura = new Factura();
		factura = facturaService.findByIdModel(idFac);
		factura.getEstado().setIdEst(idEst);
		facturaService.update(factura, request);
		if (from != null && from.equalsIgnoreCase("facturasFilteredEstado")) {
			return getFilteredEstado(modelAndView, idEstView, column, paginaInicio, totalPaginas, request);
		} else {
			return getAll(modelAndView, column, paginaInicio, totalPaginas, request);
		}

	}

	@RequestMapping("/factura/pdf/{idFac}")
	public String getPdf(ModelMap modelAndView, @PathVariable("idFac") int idFac, HttpServletRequest request) {
		ImpresionFactura factura = new ImpresionFactura();
		List<ModelCuotaDetalle> cuotaDetalleList = new ArrayList<>();
		if (idFac > 0) {
			factura = facturaService.findImpresionFacturaById(idFac, request);
			if (factura.isHayCuotas()) {
				cuotaDetalleList = cuotaDetalleService.findModelByIdCuo(factura.getIdCuo());
			}
		}
		facturaService.defineJrxml(factura, cuotaDetalleList);
		modelAndView.put("cuotaDetalleList", cuotaDetalleList);
		modelAndView.put("factura", factura);

		return "facturaPDF";
	}

}
