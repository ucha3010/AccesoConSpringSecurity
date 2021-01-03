package com.damian.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.damian.pojo.Favorito;
import com.damian.service.FavoritoService;
import com.damian.service.IndexService;

@Controller
@SessionAttributes({ "resultado", "estoy", "errorUsuario", "idUsrLogged", "nameUsrLogged", "prinPicUsr", "prefUsr" })
public class FavoritoController {

	@Autowired
	private FavoritoService favoritoService;

	@Autowired
	private IndexService indexService;

	@RequestMapping("/favorito/save/{idPro}/{page}")
	public String saveFavorito(ModelAndView modelAndView, @PathVariable("idPro") int idPro,
			@PathVariable("page") String page, HttpServletRequest request) {
		int idUsr = indexService.idUserLogged(modelAndView);
		if (idUsr > 0) {
			favoritoService.save(new Favorito(idUsr, idPro, null), request);
		}
		return "redirect:/" + page.replace("-", "/");
	}

	@RequestMapping("/favorito/delete/{idPro}/{page}")
	public String deleteFavorito(ModelAndView modelAndView, @PathVariable("idPro") int idPro,
			@PathVariable("page") String page, HttpServletRequest request) {
		int idUsr = indexService.idUserLogged(modelAndView);
		if (idUsr > 0) {
			favoritoService.delete(idUsr, idPro, request);
		}
		return "redirect:/" + page.replace("-", "/");
	}

}
