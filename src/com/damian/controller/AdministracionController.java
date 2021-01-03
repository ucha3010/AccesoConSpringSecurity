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
import com.damian.pojo.Campania;
import com.damian.pojo.Constantes;
import com.damian.pojo.Producto;
import com.damian.pojo.front.FrontAdministrarConfiguracion;
import com.damian.service.AdministracionOfertasService;
import com.damian.service.CampaniaService;
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
	private CampaniaService campaniaService;

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
		modelAndView.addObject("productos",
				productoService.findProductosSinOferta(productos, ofertasList, campaniaList));
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

	@RequestMapping("/administrar/populares")
	public ModelAndView getPopulares(ModelAndView modelAndView) {

		modelAndView.addObject("administracionOfertas", new AdministracionOfertas());
		List<AdministracionOfertas> popularesList = administracionOfertasService.findByProductosPopulares(0);
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

		return "redirect:/administrar/populares";
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

		return "redirect:/administrar/populares";

	}

	@RequestMapping("/administrar/populares/order/{idPro}/{ordenPopular}")
	public String orderPopulares(@PathVariable("idPro") int idPro, @PathVariable("ordenPopular") int ordenPopular,
			RedirectAttributes ra, HttpServletRequest request) {

		administracionOfertasService.orderPopular(idPro, ordenPopular, request);
		return "redirect:/administrar/populares";

	}

	/******************************************************************
	 * NOVEDADES
	 ******************************************************************/

	@RequestMapping("/administrar/novedades")
	public ModelAndView getNovedades(ModelAndView modelAndView) {

		modelAndView.addObject("administracionOfertas", new AdministracionOfertas());
		List<AdministracionOfertas> novedadesList = administracionOfertasService.findByNovedades(0);
		modelAndView.addObject("novedades", novedadesList);
		List<Producto> productos = productoService.findSearchAll();
		modelAndView.addObject("productos", productoService.findProductosSinNovedades(productos, novedadesList));
		modelAndView.addObject("listadoSelect", administracionOfertasService.listadoSelect("novedades"));
		modelAndView.setViewName("configuracionNovedades");
		return modelAndView;
	}

	@RequestMapping(value = { "/administrar/novedades/save" }, method = RequestMethod.POST)
	public String saveNovedades(@ModelAttribute("administracionOferta") AdministracionOfertas administracionOfertas,
			BindingResult result, Model model, RedirectAttributes ra, HttpServletRequest request) {

		if (administracionOfertasService.saveNovedades(administracionOfertas, request) > 0) {
			ra.addFlashAttribute("saveOferta", "exito");
		} else {
			ra.addFlashAttribute("noSaveOferta", "no_save");
		}

		return "redirect:/administrar/novedades";
	}

	@RequestMapping("/administrar/novedades/delete/{idPro}")
	public String removeNovedades(ModelAndView modelAndView, @PathVariable("idPro") int idPro, RedirectAttributes ra,
			HttpServletRequest request) {

		int realizado = 0;
		AdministracionOfertas administracionOfertas = administracionOfertasService.findById(idPro);
		if (administracionOfertas != null) {
			if (!administracionOfertas.isBooleanNovedades() && !administracionOfertas.isBooleanOferta()
					&& administracionOfertas.getIdCam() == 0) {
				realizado = administracionOfertasService.delete(idPro, request);
			} else {
				administracionOfertas.setBooleanNovedades(false);
				realizado = administracionOfertasService.updateNovedades(administracionOfertas, request);
			}

			if (realizado > 0) {
				administracionOfertasService.orderByOrdenNovedades(request);
				ra.addFlashAttribute("removeOferta", "exito");
			} else {
				ra.addFlashAttribute("noRemoveOferta", "no_remove");
			}
		}

		return "redirect:/administrar/novedades";

	}

	@RequestMapping("/administrar/novedades/order/{idPro}/{ordenNovedades}")
	public String orderNovedades(@PathVariable("idPro") int idPro, @PathVariable("ordenNovedades") int ordenNovedades,
			RedirectAttributes ra, HttpServletRequest request) {

		administracionOfertasService.orderNovedades(idPro, ordenNovedades, request);
		return "redirect:/administrar/novedades";

	}

	/******************************************************************
	 * CAMPAÑAS
	 ******************************************************************/

	@RequestMapping("/administrar/campanias/{idCam}")
	public ModelAndView getCampanias(ModelAndView modelAndView, @PathVariable("idCam") int idCam) {

		modelAndView.addObject("campanias", campaniaService.findAll());
		if (idCam == 0) {
			idCam = campaniaService.getMaxId();
		}
		modelAndView.addObject("campaniaSelect", campaniaService.findById(idCam));
		modelAndView.addObject("campania", new Campania());
		List<AdministracionOfertas> productosCampaniaList = administracionOfertasService.findByCampania(idCam, 0);
		modelAndView.addObject("productosCampaniaList", productosCampaniaList);
		List<Producto> productos = productoService.findSearchAll();
		modelAndView.addObject("productos", productoService.findProductosSinCampania(productos, productosCampaniaList));
		modelAndView.setViewName("configuracionCampanias");
		return modelAndView;
	}

	@RequestMapping(value = { "/administrar/campanias/save" }, method = RequestMethod.POST)
	public String saveCampanias(@ModelAttribute("campania") Campania campania, BindingResult result, Model model,
			RedirectAttributes ra, HttpServletRequest request) {

		if (campaniaService.save(campania, request) > 0) {
			ra.addFlashAttribute("saveOferta", "exito");
		} else {
			ra.addFlashAttribute("noSaveOferta", "no_save");
		}

		return "redirect:/administrar/campanias/" + campaniaService.getMaxId();
	}

	@RequestMapping("/administrar/campanias/edit/{idCam}")
	public ModelAndView editCampanias(ModelAndView modelAndView, @PathVariable("idCam") int idCam) {

		modelAndView.addObject("campania", campaniaService.findById(idCam));
		modelAndView.setViewName("campania");
		return modelAndView;

	}

	@RequestMapping(value = { "/administrar/campanias/edit/save" }, method = RequestMethod.POST)
	public String editSaveCampanias(@ModelAttribute("campania") Campania campania, BindingResult result, Model model,
			RedirectAttributes ra, HttpServletRequest request) {

		if (campaniaService.save(campania, request) > 0) {
			ra.addFlashAttribute("saveOferta", "exito");
		} else {
			ra.addFlashAttribute("noSaveOferta", "no_save");
		}

		return "redirect:/administrar/campanias/" + campania.getIdCam();
	}

	@RequestMapping("/administrar/campanias/delete/{idCam}")
	public String removeCampanias(ModelAndView modelAndView, @PathVariable("idCam") int idCam, RedirectAttributes ra,
			HttpServletRequest request) {

		Campania campania = campaniaService.findById(idCam);
		if (campania != null) {
			List<AdministracionOfertas> productosList = administracionOfertasService.findByCampania(idCam, 0);
			for (AdministracionOfertas ao : productosList) {
				ao.setIdCam(0);
				administracionOfertasService.updateCampania(ao, request);
			}

			if (campaniaService.delete(idCam, request) > 0) {
				ra.addFlashAttribute("removeOferta", "exito");
			} else {
				ra.addFlashAttribute("noRemoveOferta", "no_remove");
			}
		}

		return "redirect:/administrar/campanias/" + campaniaService.getMaxId();

	}

	@RequestMapping("/administrar/campanias/addProducto/{idCam}/{idPro}")
	public String addProductoToCampania(@PathVariable("idCam") int idCam, @PathVariable("idPro") int idPro,
			RedirectAttributes ra, HttpServletRequest request) {

		return addOrDeleteProducto(idCam, idPro, ra, request, true);

	}

	@RequestMapping("/administrar/campanias/deleteProducto/{idCam}/{idPro}")
	public String deleteProductoToCampania(@PathVariable("idCam") int idCam, @PathVariable("idPro") int idPro,
			RedirectAttributes ra, HttpServletRequest request) {

		return addOrDeleteProducto(idCam, idPro, ra, request, false);

	}

	private String addOrDeleteProducto(int idCam, int idPro, RedirectAttributes ra, HttpServletRequest request,
			boolean add) {

		int realizado = 0;
		AdministracionOfertas administracionOfertas = administracionOfertasService.findById(idPro);
		int finalIdCam = 0;
		if (add) {
			finalIdCam = idCam;
		}
		if(administracionOfertas == null) {
			administracionOfertas = new AdministracionOfertas();
			administracionOfertas.setIdPro(idPro);
			administracionOfertas.setIdCam(finalIdCam);
			realizado = administracionOfertasService.saveCampania(administracionOfertas, request);
		} else {
			administracionOfertas.setIdCam(finalIdCam);
			realizado = administracionOfertasService.updateCampania(administracionOfertas, request);
		}
		if (realizado > 0) {
			ra.addFlashAttribute("saveOferta", "exito");
		} else {
			ra.addFlashAttribute("noSaveOferta", "no_save");
		}

		return "redirect:/administrar/campanias/" + idCam;
	}

}
