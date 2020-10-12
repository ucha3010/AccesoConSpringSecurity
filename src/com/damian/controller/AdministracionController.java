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

import com.damian.pojo.Constantes;
import com.damian.pojo.front.FrontAdministrarConfiguracion;
import com.damian.service.ConstantesService;
import com.damian.utils.ConstantesLocales;
import com.mysql.jdbc.StringUtils;

@Controller
public class AdministracionController {

	@Autowired
	private ConstantesService constantesService;

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

}
