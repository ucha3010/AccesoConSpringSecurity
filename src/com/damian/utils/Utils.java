package com.damian.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.User;

import com.damian.dao.UsuarioDAO;
import com.damian.dao.UsuarioOrdenDAO;
import com.damian.dao.model.ModelUsuarioOrden;
import com.damian.pojo.Usuario;
import com.mysql.jdbc.StringUtils;

public class Utils {

	public static String validateColumnAndOrder(String column, String orderObl, String table, String defaultColumn,
			String defaultOrder, String columnOrder, HttpServletRequest request, UsuarioDAO usuarioDAO,
			UsuarioOrdenDAO usuarioOrdenDAO) {

		String dataOut = "";
		Usuario usuario = getLoggedUser(request, usuarioDAO);
		ModelUsuarioOrden uo = null;
		if (usuario != null) {
			uo = usuarioOrdenDAO.findByIdUsrTabla(usuario.getIdUsr(), table);
		} else {
			return null;
		}

		if (StringUtils.isNullOrEmpty(column) || column.equals("null")) {
			if (uo == null) {
				uo = new ModelUsuarioOrden(0, usuario.getIdUsr(), table, defaultColumn, defaultOrder);
				usuarioOrdenDAO.save(uo, request);
				dataOut = columnOrder;
			} else {
				dataOut = uo.getColumna().concat(" ").concat(uo.getOrden() == null ? defaultOrder : uo.getOrden());
			}
		} else {
			if (uo == null) {
				uo = new ModelUsuarioOrden(0, usuario.getIdUsr(), table, column, defaultOrder);
				usuarioOrdenDAO.save(uo, request);
				dataOut = column + " " + defaultOrder;
			} else {
				String order = defineOrder(uo, column, orderObl, defaultOrder);
				uo.setColumna(column);
				uo.setOrden(order);
				usuarioOrdenDAO.update(uo, request);
				dataOut = uo.getColumna().concat(" ").concat(uo.getOrden());
			}
		}
		return dataOut;

	}

	private static String defineOrder(ModelUsuarioOrden uo, String column, String orderObl, String defaultOrder) {

		String order;
		if (orderObl == null || orderObl.equals("null")) {
			if (uo != null && uo.getColumna().equals(column)) {
				if (uo.getOrden().equals("ASC")) {
					order = "DESC";
				} else {
					order = "ASC";
				}
			} else {
				order = defaultOrder;
			}
		} else {
			order = orderObl;
		}
		return order;
	}

	public static Usuario getLoggedUser(HttpServletRequest request, UsuarioDAO usuarioDAO) {

		return usuarioDAO.findByUsername(getLoggedUser(request));
	}

	public static String getDate(long milliSeconds, String dateFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		return formatter.format(calendar.getTime());
	}
	
	public static String rutaHastaProyecto(HttpServletRequest request) {
		
		// TODO DAMIAN por algún motivo el comando System.getProperty("user.dir") me
		// está devolviendo la ruta donde está instalado el Eclipse en lugar
		// de devolver la ruta de workspace (según leí, esa es la ruta que debería
		// devolver). Con lo cual utilizo esto de abajo.
		String rutaWorkspace = System.getProperty("catalina.base");
		int finWorkspace = rutaWorkspace.indexOf(".metadata");
		// Ruta hasta el workspace
		rutaWorkspace = rutaWorkspace.substring(0, finWorkspace);
		// Ruta hasta el proyecto
		rutaWorkspace = rutaWorkspace + request.getContextPath().substring(1);
		// Ruta dentro del proyecto
		return rutaWorkspace;
		
	}

	public static String rutaHastaWebContent(HttpServletRequest request) {

		 return rutaHastaProyecto(request) + System.getProperty("file.separator") + "WebContent";
	}

	public static String rutaDentroResources(HttpServletRequest request, String carpeta) {

		String ruta = rutaHastaWebContent(request) + System.getProperty("file.separator") + "resources"
				+ System.getProperty("file.separator") + carpeta + System.getProperty("file.separator");
		return ruta;
	}

	public static String getLoggedUser(HttpServletRequest request) {

		String loggedUser = "OWN USER";
		org.springframework.security.core.context.SecurityContextImpl context = null;
		if (request != null) {
			context = (org.springframework.security.core.context.SecurityContextImpl) request.getSession()
					.getAttribute("SPRING_SECURITY_CONTEXT");
		} else {
			return loggedUser;
		}
		if (context != null && context.getAuthentication() != null && context.getAuthentication().getName() != null) {
			loggedUser = context.getAuthentication().getName();
		} else if (context != null && context.getAuthentication() != null
				&& context.getAuthentication().getPrincipal() != null
				&& !context.getAuthentication().getPrincipal().toString().isEmpty() && !context.getAuthentication()
						.getPrincipal().toString().startsWith("org.springframework.security.core.userdetails.User")) {
			loggedUser = context.getAuthentication().getPrincipal().toString();
		} else if (context != null && context.getAuthentication() != null
				&& context.getAuthentication().getPrincipal() != null
				&& !context.getAuthentication().getPrincipal().toString().isEmpty() && context.getAuthentication()
						.getPrincipal().toString().startsWith("org.springframework.security.core.userdetails.User")) {
			org.springframework.security.core.userdetails.User usuario = (User) context.getAuthentication()
					.getPrincipal();
			loggedUser = usuario.getUsername();
		}
		return loggedUser;
	}
	
	public static String entradaOVacio(String entrada) {
		return (entrada == null) ? "": entrada;
	}
	
	public static String siHayDatoAgregoEspacio(String entrada) {
		return (entradaOVacio(entrada).length() == 0) ? "" : " ";
	}

}
