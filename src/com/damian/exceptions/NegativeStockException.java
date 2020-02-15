package com.damian.exceptions;

public class NegativeStockException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NegativeStockException(String msg) {
		super(msg);
	}

}
