package com.yodlee.support.microservice.exception;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RequestAttributeInvalidException.class)
	public final ResponseEntity<ErrorDetails> handleInvalidAttributeException(RequestAttributeInvalidException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handle(Exception ex, WebRequest request, HttpServletResponse response) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(true));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<Object> handles(NumberFormatException ex, WebRequest request, HttpServletResponse response) {

		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Wrong Input" + ex,
				request.getDescription(true));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

	}

}
