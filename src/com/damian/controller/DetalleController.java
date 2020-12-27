package com.damian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.damian.pojo.Producto;
import com.damian.service.DescripcionProductoService;
import com.damian.service.IndexService;
import com.damian.service.ProductoService;

@Controller
@SessionAttributes({ "resultado", "estoy", "errorUsuario", "idUsrLogged", "nameUsrLogged", "prinPicUsr", "prefUsr" })
public class DetalleController {

	@Autowired
	private IndexService indexService;

	@Autowired
	private ProductoService productoService;

	@Autowired
	private DescripcionProductoService descripcionProductoService;

	@RequestMapping("/detalle/producto/{idPro}")
	public ModelAndView getDetalleProducto(ModelAndView modelAndView, @PathVariable("idPro") int idPro) {
		indexService.idUserLogged(modelAndView);
		Producto producto = productoService.findByIdConFotos(idPro);
		modelAndView.addObject("producto", producto);
		modelAndView.addObject("descripcion", descripcionProductoService.findById(idPro));
		modelAndView.addObject("estoy", "detalle/producto/" + idPro);
		modelAndView.setViewName("frontdetalleproducto");
		return modelAndView;
	}

	@RequestMapping("/private/detalle/producto/{idPro}")
	public ModelAndView getPrivateDetalleProducto(ModelAndView modelAndView, @PathVariable("idPro") int idPro) {
		return getDetalleProducto(modelAndView, idPro);
	}

}
