package com.damian.utils;

import javax.servlet.http.HttpServletRequest;

import com.damian.dao.UsuarioDAO;
import com.damian.dao.UsuarioOrdenDAO;
import com.damian.dao.model.ModelUsuarioOrden;
import com.damian.pojo.Usuario;
import com.mysql.jdbc.StringUtils;

public class Utils {

	public static String validateColumnAndOrder(String column, String table, String defaultColumn, String defaultOrder,
			String columnOrder, HttpServletRequest request, UsuarioDAO usuarioDAO, UsuarioOrdenDAO usuarioOrdenDAO) {

		String dataOut = "";
		Usuario usuario = getLoggedUser(request, usuarioDAO);
		ModelUsuarioOrden uo = null;
		if (usuario != null) {
			uo = usuarioOrdenDAO.findByIdUsrTabla(usuario.getIdUsr(), table);
		}

		if (StringUtils.isNullOrEmpty(column) || column.equals("null")) {
			if (usuario == null && uo == null) {
				dataOut = defaultColumn.concat(" ").concat(defaultOrder);
			} else if (uo == null) {
				uo = new ModelUsuarioOrden(0, usuario.getIdUsr(), table, defaultColumn, defaultOrder);
				usuarioOrdenDAO.save(uo);
				dataOut = columnOrder;
			} else {
				dataOut = uo.getColumna().concat(" ").concat(defineOrder(uo, column, defaultOrder));
			}
		} else {
			if (uo == null) {
				uo = new ModelUsuarioOrden(0, usuario.getIdUsr(), table, column, defaultOrder);
				usuarioOrdenDAO.save(uo);
				dataOut = column + " " + defaultOrder;
			} else {
				String order = defineOrder(uo, column, defaultOrder);
				uo.setColumna(column);
				uo.setOrden(order);
				usuarioOrdenDAO.update(uo);
				dataOut = uo.getColumna().concat(" ").concat(uo.getOrden());
			}
		}
		return dataOut;

	}

	private static String defineOrder(ModelUsuarioOrden uo, String column, String defaultOrder) {

		String order;
		if (uo != null && uo.getColumna().equals(column)) {
			if (uo.getOrden().equals("ASC")) {
				order = "DESC";
			} else {
				order = "ASC";
			}
		} else {
			order = defaultOrder;
		}
		return order;
	}

	public static Usuario getLoggedUser(HttpServletRequest request, UsuarioDAO usuarioDAO) {

		org.springframework.security.core.context.SecurityContextImpl context = null;
		if (request != null) {
			context = (org.springframework.security.core.context.SecurityContextImpl) request.getSession()
					.getAttribute("SPRING_SECURITY_CONTEXT");
		}
		String creador;
		if (context != null && context.getAuthentication() != null && context.getAuthentication().getPrincipal() != null
				&& !context.getAuthentication().getPrincipal().toString().isEmpty()) {
			creador = context.getAuthentication().getPrincipal().toString();
		} else {
			creador = "OWN USER";
		}
		return usuarioDAO.findByUsername(creador);

	}

	// private void ordenarPorImporte(List<FacturaOrdenar> productoEmpresas, boolean
	// ascendente) {
	//
	// if(ascendente) {
	// Collections.sort(productoEmpresas, new Comparator<FacturaOrdenar>() {
	// @Override
	// public int compare(FacturaOrdenar fo1, FacturaOrdenar fo2) {
	// if (fo1.getImporte() < fo2.getImporte()) return -1;
	// if (fo1.getImporte() > fo2.getImporte()) return 1;
	// return 0;
	// }
	// });
	// } else {
	// Collections.sort(productoEmpresas, new Comparator<FacturaOrdenar>() {
	// @Override
	// public int compare(FacturaOrdenar fo1, FacturaOrdenar fo2) {
	// if (fo1.getImporte() > fo2.getImporte()) return -1;
	// if (fo1.getImporte() < fo2.getImporte()) return 1;
	// return 0;
	// }
	// });
	// }
	// }

}
