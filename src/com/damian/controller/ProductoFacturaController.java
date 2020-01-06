package com.damian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damian.converter.ConverterProductoFactura;
import com.damian.dao.model.ModelProductoFactura;
import com.damian.pojo.ProductoFactura;
import com.damian.service.FacturaService;
import com.damian.service.ProductoFacturaService;
import com.damian.service.ProductoService;

@Controller
@SessionAttributes({ "resultado" }) // los atributos que pueden mantenerse en sesión y verse en distintas
									// páginas
public class ProductoFacturaController {

	@Autowired
	private ProductoService productoService;

	@Autowired
	private FacturaService facturaService;

	@Autowired
	private ProductoFacturaService productoFacturaService;

	@Autowired
	private ConverterProductoFactura converterProductoFactura;

	@RequestMapping("/productoFactura/producto/{idPro}")
	public ModelAndView getProductos(ModelAndView modelAndView, @PathVariable("idPro") int idPro) {
		modelAndView.addObject("producto", productoService.findById(idPro));
		modelAndView.addObject("productoFacturas", productoFacturaService.findByIdPro(idPro));
		modelAndView.setViewName("productoFactura");
		return modelAndView;
	}

	@RequestMapping("/productoFactura/factura/{idFac}")
	public ModelAndView getFacturas(ModelAndView modelAndView, @PathVariable("idFac") int idFac) {
		modelAndView.addObject("factura", facturaService.findById(idFac));
		modelAndView.addObject("productoFacturas", productoFacturaService.findByIdFac(idFac));
		modelAndView.setViewName("facturaProducto");
		return modelAndView;
	}

	@RequestMapping("/productoFactura/producto/save")
	public String saveProducto(@ModelAttribute("productoFactura") ModelProductoFactura modelProductoFactura,
			RedirectAttributes ra) {

		ProductoFactura productoFactura = converterProductoFactura.convert(modelProductoFactura);
		productoFacturaService.save(productoFactura);

		return "redirect:/productoFactura/producto/" + modelProductoFactura.getIdPro();
	}

	@RequestMapping("/productoFactura/factura/save/{idFac}")
	public String saveFactura(@ModelAttribute("productoFactura") ModelProductoFactura modelProductoFactura,
			RedirectAttributes ra) {

		ProductoFactura productoFactura = converterProductoFactura.convert(modelProductoFactura);
		productoFacturaService.save(productoFactura);

		return "redirect:/productoFactura/factura/" + modelProductoFactura.getIdFac();
	}

	@RequestMapping("/productoFactura/producto/delete/{idPro}/{idFac}")
	public String deleteProducto(@PathVariable("idPro") int idPro, @PathVariable("idFac") int idFac,
			RedirectAttributes ra) {

		productoFacturaService.delete(idPro, idFac);
		ra.addFlashAttribute("resultado", "Cambios realizados con éxito");

		return "redirect:/productoFactura/producto/" + idPro;

	}

	@RequestMapping("/productoFactura/factura/delete/{idPro}/{idFac}")
	public String deleteFactura(@PathVariable("idPro") int idPro, @PathVariable("idFac") int idFac,
			RedirectAttributes ra) {

		productoFacturaService.delete(idPro, idFac);
		ra.addFlashAttribute("resultado", "Cambios realizados con éxito");

		return "redirect:/productoFactura/factura/" + idFac;

	}

}
