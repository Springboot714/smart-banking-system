package com.smartbakingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartbakingsystem.dto.request.CustomerCreationRequestDTO;
import com.smartbakingsystem.dto.response.ApiResponseDTO;
import com.smartbakingsystem.dto.response.CustomerDetailsByIdResponseDTO;
import com.smartbakingsystem.service.CustomerSerivce;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerSerivce customerSerivce;
	
	
	@PostMapping("/add")
	public ApiResponseDTO<Long> customerCreation(@RequestBody CustomerCreationRequestDTO creationRequestDTO){
		
		System.out.println(creationRequestDTO);
		
		
		return customerSerivce.CustomerSave(creationRequestDTO);
		
	}
	
	@GetMapping("{customerId}")
	public ApiResponseDTO<CustomerDetailsByIdResponseDTO> findCustomerById(@PathVariable Long customerId){
		
		return customerSerivce.findCustomerById(customerId);
		
	}

}
