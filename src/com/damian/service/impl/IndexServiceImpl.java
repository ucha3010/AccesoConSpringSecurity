package com.damian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.damian.pojo.AdministracionOfertas;
import com.damian.pojo.Foto;
import com.damian.pojo.Usuario;
import com.damian.service.AdministracionOfertasService;
import com.damian.service.ConstantesService;
import com.damian.service.FotoService;
import com.damian.service.IndexService;
import com.damian.service.PreferenciaUsuarioService;
import com.damian.service.UsuarioService;
import com.damian.utils.ConstantesLocales;

@Service
public class IndexServiceImpl implements IndexService {

	@Autowired
	private AdministracionOfertasService administracionOfertasService;

	@Autowired
	private ConstantesService constantesService;

	@Autowired
	private FotoService fotoService;

	@Autowired
	private PreferenciaUsuarioService preferenciaUsuarioService;

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public ModelAndView manageIndex(ModelAndView model) {

		idUserLogged(model);
		model.addObject(ConstantesLocales.SPEECH, constantesService.findByClave(ConstantesLocales.SPEECH));
		model.addObject("estoy", "index");

		List<AdministracionOfertas> aoList = administracionOfertasService.findByOfertas(3);
		for (AdministracionOfertas ao : aoList) {
			List<Foto> fotos = fotoService.findByIdPro(ao.getProducto().getIdPro());
			for(Foto f:fotos) {
				if(f.isPrincipal()) {
					ao.getProducto().setNombreFotoPrincipal(f.getNombre());
				}
			}
		}
		model.addObject("ofertas", aoList);

		model.setViewName("index");
		return model;
	}

	@Override
	public ModelAndView manageAbout(ModelAndView model, String estoy) {

		idUserLogged(model);
		model.addObject("estoy", "about");
		model.setViewName("about");
		return model;
	}

	@Override
	public int idUserLogged(ModelAndView model) {
		Usuario usuario = new Usuario();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!authentication.getName().equals("anonymousUser")) {
			usuario = usuarioService.findByUsername(authentication.getName());
			model.addObject("idUsrLogged", usuario.getIdUsr());
			model.addObject("nameUsrLogged", usuario.getUsuario());
			List<Foto> fotos = fotoService.findByIdUsr(usuario.getIdUsr());
			for (Foto f : fotos) {
				if (f.isPrincipal()) {
					model.addObject("prinPicUsr", f.getNombre());
					break;
				}
			}
			model.addObject("prefUsr", preferenciaUsuarioService.findById(usuario.getIdUsr()));
		}
		return usuario.getIdUsr();
	}

}
