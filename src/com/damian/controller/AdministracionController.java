package com.damian.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damian.pojo.AdministracionOfertas;
import com.damian.pojo.Constantes;
import com.damian.pojo.Producto;
import com.damian.pojo.front.FrontAdministrarConfiguracion;
import com.damian.service.AdministracionOfertasService;
import com.damian.service.ConstantesService;
import com.damian.service.ProductoService;
import com.damian.utils.ConstantesLocales;
import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

@Controller
public class AdministracionController {

	@Autowired
	private AdministracionOfertasService administracionOfertasService;

	@Autowired
	private ConstantesService constantesService;

	@Autowired
	private ProductoService productoService;

	@RequestMapping("/administrar/configuracion/find/{idUsr}")
	public ModelAndView getConfiguration(ModelAndView modelAndView, @PathVariable("idUsr") int idUsr) {

		FrontAdministrarConfiguracion frontAdministrarConfiguracion = constantesService.findAdminConfiguration();
		frontAdministrarConfiguracion.setIdUsr(idUsr);
		modelAndView.addObject("frontAdministrarConfiguracion", frontAdministrarConfiguracion);
		modelAndView.setViewName("administracionConfiguraciones");
		return modelAndView;
	}

	@RequestMapping(value = { "/administrar/configuracion/save" }, method = RequestMethod.POST)
	public String saveAdministrarConfiguracion(
			@ModelAttribute("frontAdministrarConfiguracion") FrontAdministrarConfiguracion frontAdministrarConfiguracion,
			BindingResult result, Model model, RedirectAttributes ra, HttpServletRequest request) {
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			// return "categoria";
		}
		List<Constantes> constantesList = new ArrayList<>();
		Constantes constantes = null;
		int contador = 0;
		if (frontAdministrarConfiguracion.getIvaEnvio() != 0.0) {
			constantes = new Constantes();
			constantes.setClave(ConstantesLocales.IVA_ENVIO);
			constantes.setValorDouble(frontAdministrarConfiguracion.getIvaEnvio());
			constantesList.add(constantes);
		}
		if (!StringUtils.isNullOrEmpty(frontAdministrarConfiguracion.getSpeech())) {
			constantes = new Constantes();
			constantes.setClave(ConstantesLocales.SPEECH);
			constantes.setValorText(frontAdministrarConfiguracion.getSpeech());
			constantesList.add(constantes);
		}
		try {
			if (constantesList.size() > 0) {
				for (Constantes cons : constantesList) {
					contador += constantesService.update(cons, request);
				}
				if (contador == constantesList.size()) {
					ra.addFlashAttribute("adminConfig_save", "exito");
				} else {
					ra.addFlashAttribute("adminConfig_no_save", "no_save");
				}
			}
		} catch (DuplicateKeyException dpe) {
			ra.addFlashAttribute("adminConfig_no_save", "duplicate");
		}

		return "redirect:/administrar/configuracion/find/" + frontAdministrarConfiguracion.getIdUsr();
	}

	/******************************************************************
	 * OFERTAS
	 ******************************************************************/

	@RequestMapping("/administrar/ofertas")
	public ModelAndView getOfertas(ModelAndView modelAndView) {

		modelAndView.addObject("administracionOfertas", new AdministracionOfertas());
		List<AdministracionOfertas> ofertasList = administracionOfertasService.findByOfertas(0);
		List<AdministracionOfertas> campaniaList = administracionOfertasService.findByCampania(0, 0);
		modelAndView.addObject("ofertas", ofertasList);
		List<Producto> productos = productoService.findSearchAll();
		modelAndView.addObject("productos", productoService.findProductosSinOferta(productos, ofertasList, campaniaList));
		String jproductos = new Gson().toJson(productos);
		modelAndView.addObject("jproductos", jproductos);
		modelAndView.addObject("listadoSelect", administracionOfertasService.listadoSelect("oferta"));
		modelAndView.setViewName("configuracionOfertas");
		return modelAndView;
	}

	@RequestMapping(value = { "/administrar/ofertas/save" }, method = RequestMethod.POST)
	public String saveOferta(@ModelAttribute("administracionOferta") AdministracionOfertas administracionOfertas,
			BindingResult result, Model model, RedirectAttributes ra, HttpServletRequest request) {

		if (administracionOfertasService.saveOfertas(administracionOfertas, request) > 0) {
			Producto producto = productoService.findByIdModel(administracionOfertas.getIdPro());
			producto.setPrecioVenta(administracionOfertas.getPrecioConOferta());
			productoService.update(producto, request);
			ra.addFlashAttribute("saveOferta", "exito");
		} else {
			ra.addFlashAttribute("noSaveOferta", "no_save");
		}

		return "redirect:/administrar/ofertas";
	}

	@RequestMapping("/administrar/ofertas/delete/{idPro}")
	public String removeOferta(ModelAndView modelAndView, @PathVariable("idPro") int idPro, RedirectAttributes ra,
			HttpServletRequest request) {

		int realizado = 0;
		AdministracionOfertas administracionOfertas = administracionOfertasService.findById(idPro);
		if (administracionOfertas != null) {
			if (!administracionOfertas.isBooleanNovedades() && !administracionOfertas.isBooleanPopular()
					&& administracionOfertas.getIdCam() == 0) {
				realizado = administracionOfertasService.delete(idPro, request);
			} else {
				administracionOfertas.setBooleanOferta(false);
				realizado = administracionOfertasService.updateOfertas(administracionOfertas, request);
			}

			if (realizado > 0) {
				Producto producto = productoService.findByIdModel(idPro);
				producto.setPrecioVenta(administracionOfertas.getPrecioSinOferta());
				productoService.update(producto, request);
				administracionOfertasService.orderByOrdenOferta(request);
				ra.addFlashAttribute("removeOferta", "exito");
			} else {
				ra.addFlashAttribute("noRemoveOferta", "no_remove");
			}
		}

		return "redirect:/administrar/ofertas";

	}

	@RequestMapping("/administrar/ofertas/order/{idPro}/{ordenOferta}")
	public String orderOferta(@PathVariable("idPro") int idPro, @PathVariable("ordenOferta") int ordenOferta,
			RedirectAttributes ra, HttpServletRequest request) {

		administracionOfertasService.orderOferta(idPro, ordenOferta, request);
		return "redirect:/administrar/ofertas";

	}

	/******************************************************************
	 * PRODUCTOS MÁS POPULARES
	 ******************************************************************/

	@RequestMapping("/administrar/populares/{cantMax}")
	public ModelAndView getPopulares(ModelAndView modelAndView, @PathVariable("cantMax") int cantMax) {

		modelAndView.addObject("administracionOfertas", new AdministracionOfertas());
		List<AdministracionOfertas> popularesList = administracionOfertasService.findByProductosPopulares(cantMax);
		modelAndView.addObject("populares", popularesList);
		List<Producto> productos = productoService.findSearchAll();
		modelAndView.addObject("productos", productoService.findProductosSinPopulares(productos, popularesList));
		modelAndView.addObject("listadoSelect", administracionOfertasService.listadoSelect("popular"));
		modelAndView.setViewName("configuracionPopulares");
		return modelAndView;
	}

	@RequestMapping(value = { "/administrar/populares/save" }, method = RequestMethod.POST)
	public String savePopulares(@ModelAttribute("administracionOferta") AdministracionOfertas administracionOfertas,
			BindingResult result, Model model, RedirectAttributes ra, HttpServletRequest request) {

		if (administracionOfertasService.savePopulares(administracionOfertas, request) > 0) {
			ra.addFlashAttribute("saveOferta", "exito");
		} else {
			ra.addFlashAttribute("noSaveOferta", "no_save");
		}

		return "redirect:/administrar/populares/0";
	}

	@RequestMapping("/administrar/populares/delete/{idPro}")
	public String removePopulares(ModelAndView modelAndView, @PathVariable("idPro") int idPro, RedirectAttributes ra,
			HttpServletRequest request) {

		int realizado = 0;
		AdministracionOfertas administracionOfertas = administracionOfertasService.findById(idPro);
		if (administracionOfertas != null) {
			if (!administracionOfertas.isBooleanNovedades() && !administracionOfertas.isBooleanOferta()
					&& administracionOfertas.getIdCam() == 0) {
				realizado = administracionOfertasService.delete(idPro, request);
			} else {
				administracionOfertas.setBooleanPopular(false);
				realizado = administracionOfertasService.updatePopulares(administracionOfertas, request);
			}

			if (realizado > 0) {
				administracionOfertasService.orderByOrdenPopular(request);
				ra.addFlashAttribute("removeOferta", "exito");
			} else {
				ra.addFlashAttribute("noRemoveOferta", "no_remove");
			}
		}

		return "redirect:/administrar/populares/0";

	}

	@RequestMapping("/administrar/populares/order/{idPro}/{ordenPopular}")
	public String orderPopulares(@PathVariable("idPro") int idPro, @PathVariable("ordenPopular") int ordenPopular,
			RedirectAttributes ra, HttpServletRequest request) {

		administracionOfertasService.orderPopular(idPro, ordenPopular, request);
		return "redirect:/administrar/populares/0";

	}

}
