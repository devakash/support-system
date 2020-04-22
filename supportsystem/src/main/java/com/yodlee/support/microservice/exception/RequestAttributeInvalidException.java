package com.yodlee.support.microservice.exception;

public class RequestAttributeInvalidException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4437665169820152626L;

	public RequestAttributeInvalidException(String exception) {
		super(exception);
	}
}
