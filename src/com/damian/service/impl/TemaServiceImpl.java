package com.damian.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.dao.TemaDAO;
import com.damian.exceptions.NotEmptyException;
import com.damian.pojo.PreferenciaUsuario;
import com.damian.pojo.Tema;
import com.damian.service.PreferenciaUsuarioService;
import com.damian.service.TemaService;

@Service
public class TemaServiceImpl implements TemaService {

	@Autowired
	private TemaDAO temaDAO;

	@Autowired
	private PreferenciaUsuarioService preferenciaUsuarioService;

	@Override
	public List<Tema> findAll() {
		return temaDAO.findAll();
	}

	@Override
	public Tema findById(int id) {
		return temaDAO.findById(id);
	}

	@Override
	public int save(Tema tema, HttpServletRequest request) {
		return temaDAO.save(tema, request);
	}

	@Override
	public int update(Tema tema, HttpServletRequest request) {
		return temaDAO.update(tema, request);
	}

	@Override
	public int delete(int id, HttpServletRequest request) throws NotEmptyException {
		if(id == 1) {
			throw new NotEmptyException("Tema por defecto. No se debe eliminar");
		}
		List<PreferenciaUsuario> preferenciaUsuarioList = preferenciaUsuarioService.findAll();
		Tema t = findById(id);
		if (preferenciaUsuarioList != null && !preferenciaUsuarioList.isEmpty()) {
			for (PreferenciaUsuario pu : preferenciaUsuarioList) {
				if (pu.getTema().equals(t.getNombre())) {
					throw new NotEmptyException("Tiene asociado usuario");
				}
			}
		}
		return temaDAO.delete(id, request);
	}

	@Override
	public int getMaxId() {
		return temaDAO.getMaxId();
	}

}
