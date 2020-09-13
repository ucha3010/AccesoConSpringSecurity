package com.damian.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.damian.dao.ConstantesDAO;
import com.damian.pojo.Constantes;
import com.damian.pojo.front.FrontAdministrarConfiguracion;
import com.damian.service.ConstantesService;
import com.damian.utils.ConstantesLocales;
import com.damian.utils.Utils;

@Service
public class ConstantesServiceImpl implements ConstantesService {

	@Autowired
	private ConstantesDAO constantesDAO;

	@Override
	public List<Constantes> findAll() {
		return constantesDAO.findAll();
	}

	@Override
	public Constantes findByClave(String clave) {
		return constantesDAO.findByClave(clave);
	}

	@Override
	public List<Constantes> findValorString(String valor) {
		return constantesDAO.findValorString(valor);
	}

	@Override
	public List<Constantes> findValorDouble(double valor) {
		return constantesDAO.findValorDouble(valor);
	}

	@Override
	public int save(Constantes constantes, HttpServletRequest request) throws DuplicateKeyException {

		constantes.setModificadoPor(Utils.getLoggedUser(request));
		constantes.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
		return constantesDAO.save(constantes, request);
	}

	@Override
	public int update(Constantes constantes, HttpServletRequest request) {

		constantes.setModificadoPor(Utils.getLoggedUser(request));
		constantes.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
		return constantesDAO.update(constantes, request);
	}

	@Override
	public int delete(String clave, HttpServletRequest request) {
		return constantesDAO.delete(clave, request);
	}

	@Override
	public FrontAdministrarConfiguracion findAdminConfiguration() {

		FrontAdministrarConfiguracion frontAdministrarConfiguracion = new FrontAdministrarConfiguracion();
		Constantes constante = findByClave(ConstantesLocales.IVA_ENVIO);
		frontAdministrarConfiguracion.setIvaEnvio(constante.getValorDouble());

		return frontAdministrarConfiguracion;
	}

}
