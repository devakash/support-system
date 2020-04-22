package com.yodlee.support.microservice.responseutils;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class SuccessResponeEntity {

	public SuccessResponeEntity(String message, HttpStatus statusCode, Date timestamp) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.timestamp = timestamp;
	}

	private String message;
	private HttpStatus statusCode;
	private Date timestamp;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

}
