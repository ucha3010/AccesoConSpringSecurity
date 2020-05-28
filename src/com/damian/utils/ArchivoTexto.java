package com.damian.utils;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class ArchivoTexto {

	public static void escribirEnArchivoLog(String mensaje, HttpServletRequest request) {
		File archivo = null;
		FileWriter fr = null;
		try {
			archivo = new File(Utils.rutaDentroResources(request, "logs"),
					"log" + Utils.getDate(new Date().getTime(), "yyyyMMdd") + ".txt");
			fr = new FileWriter(archivo, true);
			fr.write(mensaje);
			fr.write("\r\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr)
					fr.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
