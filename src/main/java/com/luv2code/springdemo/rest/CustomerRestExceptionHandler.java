package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//global exception handler
@ControllerAdvice
public class CustomerRestExceptionHandler {

	// add an exception handler for customerNotFoundException
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponce>  handleCustomerNotFoundException(CustomerNotFoundException exception){
		
		// create customeErrorResponce
		CustomerErrorResponce error = new CustomerErrorResponce();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		// return ResponseEntity
		return new ResponseEntity<CustomerErrorResponce>(error, HttpStatus.NOT_FOUND);
	}
	
	
	// add an exception handler for any other exceptions
	@ExceptionHandler 
	public ResponseEntity<CustomerErrorResponce> handleAnyOtherException(Exception exception) {

		// create customeErrorResponce
		CustomerErrorResponce error = new CustomerErrorResponce();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		// return ResponseEntity
		return new ResponseEntity<CustomerErrorResponce>(error, HttpStatus.BAD_REQUEST);
		
	}
}
