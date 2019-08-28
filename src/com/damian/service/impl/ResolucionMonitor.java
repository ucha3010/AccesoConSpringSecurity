package com.damian.service.impl;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.springframework.stereotype.Service;

@Service
public class ResolucionMonitor {

	private int ancho;
	private int alto;
	
	public ResolucionMonitor() {
		Toolkit monitor = Toolkit.getDefaultToolkit();
		Dimension screenSize = monitor.getScreenSize();
		ancho = screenSize.width;
		alto = screenSize.height;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}
	
}
