package com.damian.utils;

import javax.servlet.http.HttpServletRequest;

public class LocalLogger {

	public static void log(String clase, String mensaje, HttpServletRequest request) {
		ArchivoTexto.escribirEnArchivoLog(
				Hora.ahora() + "LOG [" + Utils.getLoggedUser(request) + "] (" + clase + ") - " + mensaje, request);
	}

	public static void save(String tabla, Object object, HttpServletRequest request) {
		ArchivoTexto.escribirEnArchivoLog(
				Hora.ahora() + "SAVE [" + Utils.getLoggedUser(request) + "] - table:" + tabla + " - " + object,
				request);
	}

	public static void update(String tabla, Object object, HttpServletRequest request) {
		ArchivoTexto.escribirEnArchivoLog(
				Hora.ahora() + "UPDATE [" + Utils.getLoggedUser(request) + "] - table:" + tabla + " - " + object,
				request);
	}

	public static void delete(String tabla, Object object, HttpServletRequest request) {
		ArchivoTexto.escribirEnArchivoLog(
				Hora.ahora() + "DELETE [" + Utils.getLoggedUser(request) + "] - table:" + tabla + " - " + object,
				request);
	}

	public static void logInUser(String principal, HttpServletRequest request) {
		ArchivoTexto.escribirEnArchivoLog(Hora.ahora() + "LOGIN [" + Utils.getLoggedUser(request) + "] - " + principal,
				request);
	}

	public static void logError(String error, HttpServletRequest request) {
		ArchivoTexto.escribirEnArchivoLog(Hora.ahora() + "ERROR [" + Utils.getLoggedUser(request) + "] - " + error,
				request);
	}

}
