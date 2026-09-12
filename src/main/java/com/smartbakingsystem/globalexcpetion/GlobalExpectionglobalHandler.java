package com.smartbakingsystem.globalexcpetion;


import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.smartbakingsystem.Enumeration.ApplicationStatus;
import com.smartbakingsystem.dto.response.ErrorResponseDTO;
import com.smartbakingsystem.exception.AddressAlreadyInactiveException;
import com.smartbakingsystem.exception.AddressAndcustomerNotMatchExpection;
import com.smartbakingsystem.exception.AddressNotFoundException;
import com.smartbakingsystem.exception.CustomerIdNotFoundException;
import com.smartbakingsystem.exception.EmailAlreadyExistException;

@RestControllerAdvice
public class GlobalExpectionglobalHandler {
	
	@ExceptionHandler(CustomerIdNotFoundException.class)
	public ResponseEntity<ErrorResponseDTO> customerIdnotFoundHandler(CustomerIdNotFoundException customerIdNotFoundException){
		
		ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
				.dateTime(LocalDateTime.now())
				.errorCode(ApplicationStatus.CUSTOMER_ID_NOT_FOUND)
				.message(customerIdNotFoundException.getMessage())
				.success(false).build();
		return new ResponseEntity<ErrorResponseDTO>(errorResponseDTO,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmailAlreadyExistException.class)
	public ResponseEntity<ErrorResponseDTO> emailAlreadyExistHandler(EmailAlreadyExistException alreadyExistException){
		
		ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
		.dateTime(LocalDateTime.now())
		.errorCode(ApplicationStatus.EMAIL_ALREADY_EXISTS)
		.message(alreadyExistException.getMessage())
		.success(false).build();
		
		return new ResponseEntity<ErrorResponseDTO>(errorResponseDTO, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<ErrorResponseDTO> addressNotFoundHandler(AddressNotFoundException addressNotFoundException){
		
		ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
				.dateTime(LocalDateTime.now())
				.errorCode(ApplicationStatus.ADDRESS_NOT_FOUND)
				.message(addressNotFoundException.getMessage())
				.success(false).build();
		return new ResponseEntity<ErrorResponseDTO>(errorResponseDTO, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AddressAndcustomerNotMatchExpection.class)
	public ResponseEntity<ErrorResponseDTO> addressAndCustomerNotMatchHandler(AddressAndcustomerNotMatchExpection addressAndcustomerNotMatchExpection){
		ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
				.dateTime(LocalDateTime.now())
				.errorCode(ApplicationStatus.ADDRESS_NOT_BELONG_TO_CUSTOMER)
				.message(addressAndcustomerNotMatchExpection.getMessage())
				.success(false).build();
		return new ResponseEntity<ErrorResponseDTO>(errorResponseDTO, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(AddressAlreadyInactiveException.class)
	public ResponseEntity<ErrorResponseDTO> addressAlreadyInactiveHandler(AddressAlreadyInactiveException addressAlreadyInactiveException){
		
		ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
				.dateTime(LocalDateTime.now())
				.errorCode(ApplicationStatus.ADDRESS_ALREADY_INACTIVE)
				.message(addressAlreadyInactiveException.getMessage())
				.success(false).build();
		return new ResponseEntity<ErrorResponseDTO>(errorResponseDTO, HttpStatus.CONFLICT);
	}
	
	

}
