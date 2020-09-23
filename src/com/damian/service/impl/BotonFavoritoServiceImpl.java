package com.damian.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.BotonFavoritoDAO;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.BotonFavorito;
import com.damian.pojo.PreferenciaUsuario;
import com.damian.pojo.front.FrontBotonFavorito;
import com.damian.service.BotonFavoritoService;
import com.damian.service.PreferenciaUsuarioService;

@Service
public class BotonFavoritoServiceImpl implements BotonFavoritoService {

	@Autowired
	private BotonFavoritoDAO botonFavoritoDAO;

	@Autowired
	private PreferenciaUsuarioService preferenciaUsuarioService;

	@Override
	public List<BotonFavorito> findAll() {
		return botonFavoritoDAO.findAll();
	}

	@Override
	public BotonFavorito findById(int id) {
		return botonFavoritoDAO.findById(id);
	}

	@Override
	public int save(BotonFavorito botonFavorito, HttpServletRequest request) {
		return botonFavoritoDAO.save(botonFavorito, request);
	}

	@Override
	public int update(BotonFavorito botonFavorito, HttpServletRequest request) {
		return botonFavoritoDAO.update(botonFavorito, request);
	}

	@Override
	public int delete(int id, HttpServletRequest request) throws NotEmptyException {
		if(id == 1) {
			throw new NotEmptyException("Tema por defecto. No se debe eliminar");
		}
		List<PreferenciaUsuario> preferenciaUsuarioList = preferenciaUsuarioService.findAll();
		BotonFavorito bf = findById(id);
		if (preferenciaUsuarioList != null && !preferenciaUsuarioList.isEmpty()) {
			for (PreferenciaUsuario pu : preferenciaUsuarioList) {
				if (pu.getBotonFavorito().equals(bf.getNombre())) {
					throw new NotEmptyException("Tiene asociado usuario");
				}
			}
		}
		return botonFavoritoDAO.delete(id, request);
	}

	@Override
	public int getMaxId() {
		return botonFavoritoDAO.getMaxId();
	}

	@Override
	public List<FrontBotonFavorito> findAllFront() {
		List<BotonFavorito> botonFavoritoList = findAll();
		List<FrontBotonFavorito> frontBotonFavoritoList = new ArrayList<FrontBotonFavorito>();
		FrontBotonFavorito fbf = null;
		for(BotonFavorito bf: botonFavoritoList) {
			fbf = new FrontBotonFavorito();
			fbf.setNombre(bf.getNombre());
			fbf.setCodigoHTML("<img src='<c:url value=\"/resources/imgs/favoritos/" + bf.getNombre() + ".png\"/>' class=\"tamanio_imagen_50x50\">");
			frontBotonFavoritoList.add(fbf);
		}
		return frontBotonFavoritoList;
	}

}
