package com.simple.generator.exceptions;

public class ContvertException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContvertException(String s) {
        super(s, null);  //  Disallow initCause
    }
}
