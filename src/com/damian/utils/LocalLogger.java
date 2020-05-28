package com.damian.utils;

import javax.servlet.http.HttpServletRequest;

public class LocalLogger {

	public static void log(String clase, String usuario, String mensaje, HttpServletRequest request) {
		ArchivoTexto.escribirEnArchivoLog(Hora.ahora() + "LOG [" + usuario + "] (" + clase + ") - " + mensaje, request);
	}

	public static void save(String tabla, Object object, HttpServletRequest request) {
		ArchivoTexto.escribirEnArchivoLog(Hora.ahora() + "SAVE - table:" + tabla + " - " + object, request);
	}

	public static void update(String tabla, Object object, HttpServletRequest request) {
		ArchivoTexto.escribirEnArchivoLog(Hora.ahora() + "UPDATE - table:" + tabla + " - " + object, request);
	}

	public static void delete(String tabla, Object object, HttpServletRequest request) {
		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		ArchivoTexto.escribirEnArchivoLog(Hora.ahora() + "DELETE - [user deleting: "
				+ context.getAuthentication().getPrincipal().toString() + "] - table:" + tabla + " - " + object,
				request);
	}

	public static void logInUser(String principal, HttpServletRequest request) {
		ArchivoTexto.escribirEnArchivoLog(Hora.ahora() + "LOGIN - " + principal, request);
	}

	public static void logError(String error, HttpServletRequest request) {
		ArchivoTexto.escribirEnArchivoLog(Hora.ahora() + "ERROR - " + error, request);
	}

}
