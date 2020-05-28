package com.damian.utils;

import java.util.Date;

public class Hora {
	
	public static String ahora() {
		return (new Date()).toString() + " - ";
	}

}
