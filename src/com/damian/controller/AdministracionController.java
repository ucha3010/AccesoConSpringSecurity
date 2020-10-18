package com.damian.controller;

import java.sql.Timestamp;
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

	@RequestMapping("/administrar/ofertas/{cantMax}")
	public ModelAndView getOfertas(ModelAndView modelAndView, @PathVariable("cantMax") int cantMax) {

		modelAndView.addObject("administracionOfertas", new AdministracionOfertas());
		List<AdministracionOfertas> ofertasList = administracionOfertasService.findByOfertas(cantMax);
		modelAndView.addObject("ofertas", ofertasList);
		List<Producto> productos = productoService.findSearchAll();
		productoService.findProductosSinOferta(productos, ofertasList);
		modelAndView.addObject("productos", productos);
		String jproductos = new Gson().toJson(productos);
		modelAndView.addObject("jproductos", jproductos);
		modelAndView.addObject("listadoSelect", administracionOfertasService.listadoSelect());
		modelAndView.setViewName("configuracionOfertas");
		return modelAndView;
	}

	@RequestMapping("/administrar/ofertas/edit/{idPro}")
	public ModelAndView editOferta(ModelAndView modelAndView, @PathVariable("idPro") int idPro) {

		modelAndView.addObject("administracionOferta", administracionOfertasService.findById(idPro));
		modelAndView.setViewName("configuracionOferta");
		return modelAndView;

	}

	@RequestMapping(value = { "/administrar/ofertas/save" }, method = RequestMethod.POST)
	public String saveOferta(@ModelAttribute("administracionOferta") AdministracionOfertas administracionOfertas,
			BindingResult result, Model model, RedirectAttributes ra, HttpServletRequest request) {

		if (administracionOfertasService.save(administracionOfertas, request) > 0) {
			ra.addFlashAttribute("saveOferta", "exito");
		} else {
			ra.addFlashAttribute("noSaveOferta", "no_save");
		}

		return "redirect:/administrar/ofertas/0";
	}

	@RequestMapping(value = { "/administrar/ofertas/update" }, method = RequestMethod.POST)
	public String updateOferta(@ModelAttribute("administracionOferta") AdministracionOfertas administracionOfertas,
			BindingResult result, Model model, RedirectAttributes ra, HttpServletRequest request) {

		administracionOfertas.setFecha(new Timestamp(System.currentTimeMillis()));
		if (administracionOfertasService.update(administracionOfertas, request) > 0) {
			administracionOfertasService.orderByOrdenOfertas(request);
			ra.addFlashAttribute("updateOferta", "exito");
		} else {
			ra.addFlashAttribute("noUpdateOferta", "no_update");
		}

		return "redirect:/administrar/ofertas/0";
	}

	@RequestMapping("/administrar/ofertas/delete/{idPro}")
	public String removeOferta(ModelAndView modelAndView, @PathVariable("idPro") int idPro, RedirectAttributes ra,
			HttpServletRequest request) {

		int realizado = 0;
		AdministracionOfertas administracionOfertas = administracionOfertasService.findById(idPro);
		if (administracionOfertas != null) {
			if (!administracionOfertas.isBooleanNovedades() && !administracionOfertas.isBooleanPopular()) {
				realizado = administracionOfertasService.delete(idPro, request);
			} else {
				administracionOfertas.setBooleanOferta(false);
				realizado = administracionOfertasService.update(administracionOfertas, request);
			}

			if (realizado > 0) {
				administracionOfertasService.orderByOrdenOfertas(request);
				ra.addFlashAttribute("removeOferta", "exito");
			} else {
				ra.addFlashAttribute("noRemoveOferta", "no_remove");
			}
		}

		return "redirect:/administrar/ofertas/0";

	}

	@RequestMapping("/administrar/ofertas/order/{idPro}/{ordenOferta}")
	public String orderOferta(@PathVariable("idPro") int idPro, @PathVariable("ordenOferta") int ordenOferta,
			RedirectAttributes ra, HttpServletRequest request) {

		administracionOfertasService.orderOfertas(idPro, ordenOferta, request);
		return "redirect:/administrar/ofertas/0";

	}

}
