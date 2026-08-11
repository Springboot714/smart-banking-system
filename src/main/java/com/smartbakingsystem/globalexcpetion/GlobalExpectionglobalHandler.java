package com.smartbakingsystem.globalexcpetion;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.smartbakingsystem.exception.CustomerIdNotFoundException;

@RestControllerAdvice
public class GlobalExpectionglobalHandler {
	
	@ExceptionHandler(CustomerIdNotFoundException.class)
	public ResponseEntity<String> customerIdnotFoundHandler(CustomerIdNotFoundException customerIdNotFoundException){
		
		return new ResponseEntity<String>(customerIdNotFoundException.getMessage(),HttpStatus.ALREADY_REPORTED);
	}

}
