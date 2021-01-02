package com.damian.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.damian.pojo.Producto;
import com.damian.service.ProductoService;

@Controller
@SessionAttributes({ "resultado", "estoy", "errorUsuario", "idUsrLogged", "nameUsrLogged", "prinPicUsr", "prefUsr" })
public class TestController {

	@Autowired
	private ProductoService productoService;

	@RequestMapping(value = "/test/busqueda", method = RequestMethod.GET)
	public ModelAndView getTestBusqueda() {
		ModelAndView modelAndView = new ModelAndView("testBusqueda");
		return modelAndView;
	}

	@RequestMapping(value = "/test/getProductos", method = RequestMethod.GET)
	public @ResponseBody List<Producto> getTags(@RequestParam String tagName, HttpServletRequest request) {

		return simulateSearchResult(tagName);

	}

	@RequestMapping(value = "/test/getOne/{idPro}", method = RequestMethod.GET)
	public ModelAndView getTestProducto(ModelAndView modelAndView, @PathVariable("idPro") int idPro) {
		modelAndView.addObject("productoEncontrado", productoService.findById(idPro));
		modelAndView.setViewName("testBusqueda");
		return modelAndView;
	}

	private List<Producto> simulateSearchResult(String tagName) {

		List<Producto> data = productoService.findAllReducedData();
		List<Producto> result = new ArrayList<Producto>();

		// iterate a list and filter by tagName
		for (Producto tag : data) {
			if (tag.getNombreES().contains(tagName)) {
				result.add(tag);
			}
		}

		return result;
	}

}
