package com.damian.controller;

import java.io.IOException;

import org.apache.jasper.JasperException;
import org.apache.jasper.el.JspPropertyNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GeneralExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex) {
		return mensaje(ex);
	}

	@ExceptionHandler(IOException.class)
	public ModelAndView handleIOException(IOException ex) {
		return mensaje(ex);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ModelAndView handlerDataIntegrityViolationException(DataIntegrityViolationException ex) {
		return mensaje(ex);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ModelAndView handlerConstraintViolationException(ConstraintViolationException ex) {
		return mensaje(ex);
	}

	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ModelAndView handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex) {
		return mensaje(ex);
	}

	@ExceptionHandler(JasperException.class)
	public ModelAndView handleJasperException(JasperException ex) {
		return mensaje(ex);
	}

	@ExceptionHandler(JspPropertyNotFoundException.class)
	public ModelAndView handleJspPropertyNotFoundException(JspPropertyNotFoundException ex) {
		return mensaje(ex);
	}

	private ModelAndView mensaje(Exception ex) {
		ModelAndView model = new ModelAndView();
		model.addObject("errorLog", ex.toString());
		model.setViewName("error");
		return model;

	}

}
