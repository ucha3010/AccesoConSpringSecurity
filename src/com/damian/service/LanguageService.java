package com.damian.service;

import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

public interface LanguageService {
	
	String getMessage(String key, Locale locale, HttpServletRequest request);
	
	Properties getMap(Locale locale, HttpServletRequest request);

}
