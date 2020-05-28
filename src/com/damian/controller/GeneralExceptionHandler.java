package com.damian.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.jasper.JasperException;
import org.apache.jasper.el.JspPropertyNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.damian.utils.LocalLogger;

@ControllerAdvice
public class GeneralExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex, HttpServletRequest request) {
		return mensaje(ex, request);
	}

	@ExceptionHandler(IOException.class)
	public ModelAndView handleIOException(IOException ex, HttpServletRequest request) {
		return mensaje(ex, request);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ModelAndView handlerDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
		return mensaje(ex, request);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ModelAndView handlerConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
		return mensaje(ex, request);
	}

	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ModelAndView handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex, HttpServletRequest request) {
		return mensaje(ex, request);
	}

	@ExceptionHandler(JasperException.class)
	public ModelAndView handleJasperException(JasperException ex, HttpServletRequest request) {
		return mensaje(ex, request);
	}

	@ExceptionHandler(JspPropertyNotFoundException.class)
	public ModelAndView handleJspPropertyNotFoundException(JspPropertyNotFoundException ex, HttpServletRequest request) {
		return mensaje(ex, request);
	}

	private ModelAndView mensaje(Exception ex, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		LocalLogger.logError(ex.toString(), request);
		model.addObject("errorLog", ex.toString());
		model.setViewName("error");
		return model;

	}

}
