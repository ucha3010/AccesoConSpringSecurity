package com.damian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.damian.pojo.Foto;
import com.damian.pojo.Usuario;
import com.damian.service.FotoService;
import com.damian.service.IndexService;
import com.damian.service.UsuarioService;

@Service
public class IndexServiceImpl implements IndexService {
	
	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public ModelAndView manageIndex(ModelAndView model) throws Exception {

		idUserLogged(model);
		model.addObject("estoy", "index");
		model.setViewName("index");
		return model;
	}

	@Override
	public ModelAndView manageAbout(ModelAndView model, String estoy) throws Exception {

		idUserLogged(model);
		model.addObject("estoy", "about");
		model.setViewName("about");
		return model;
	}
	
	private void idUserLogged(ModelAndView model) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!authentication.getName().equals("anonymousUser")) {
			Usuario usuario = usuarioService.findByUsername(authentication.getName());
			model.addObject("idUsrLogged", usuario.getIdUsr());
			model.addObject("nameUsrLogged", usuario.getUsuario());
			List<Foto> fotos = fotoService.findByIdUsr(usuario.getIdUsr());
			for (Foto f : fotos) {
				if (f.isPrincipal()) {
					model.addObject("prinPicUsr", f.getNombre());
					break;
				}
			}
		}
	}

}
