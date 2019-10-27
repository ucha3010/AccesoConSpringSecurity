package com.damian.exceptions;

public class RepeatedUsernameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RepeatedUsernameException(String msg) {
		super(msg);
	}

}
