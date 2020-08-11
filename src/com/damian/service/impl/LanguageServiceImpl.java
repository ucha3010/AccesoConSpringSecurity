package com.damian.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.damian.service.LanguageService;
import com.damian.utils.LocalLogger;
import com.damian.utils.Utils;

@Service
public class LanguageServiceImpl implements LanguageService {

	private String MULTILANGUAGE = "multilanguage";
	private String PROPERTIES = ".properties";
	private String SEPARATOR = System.getProperty("file.separator");
	private String LOCAL_PACKAGE = "damian"; // TODO DAMIAN ANOTACION nombre a modificar según cliente

	@Override
	public String getMessage(String key, Locale locale, HttpServletRequest request) {

		Properties p = getMap(locale, request);

		return p.getProperty(key);
	}

	@Override
	public Properties getMap(Locale locale, HttpServletRequest request) {

		String filename = obtainFilename(locale);
		Properties p = new Properties();
		try {
			p.load(new FileReader(Utils.rutaHastaProyecto(request).concat(SEPARATOR).concat("src").concat(SEPARATOR)
					.concat("com").concat(SEPARATOR).concat(LOCAL_PACKAGE).concat(SEPARATOR).concat("utils")
					.concat(SEPARATOR).concat(filename)));
		} catch (FileNotFoundException e) {
			LocalLogger.logError(e.toString(), request);
		} catch (IOException e) {
			LocalLogger.logError(e.toString(), request);
		}
		return p;
	}

	private String obtainFilename(Locale locale) {

		String[] params;

		if (locale != null) {
			params = locale.toString().split("_");
		} else {
			params = new String[2];
			params[0] = "es";
			params[1] = "ES";
		}
		if (params.length == 1) {
			return MULTILANGUAGE.concat("_").concat(params[0].toUpperCase()).concat(PROPERTIES);
		} else {
			return MULTILANGUAGE.concat("_").concat(params[0].toLowerCase()).concat("_").concat(params[1].toUpperCase())
					.concat(PROPERTIES);
		}
	}

}
