package com.smartbakingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartbakingsystem.dto.request.CustomerCreationRequestDTO;
import com.smartbakingsystem.dto.request.CustomerDetailsUpdateDTO;
import com.smartbakingsystem.dto.response.ApiResponseDTO;
import com.smartbakingsystem.dto.response.ApiResponseWithPaginationResponseDTO;
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
	
	@GetMapping
	public ApiResponseWithPaginationResponseDTO<?> findAllCustomerWithFilters(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "customerId") String sortBy,@RequestParam(defaultValue = "asc") String direction){
		
		return customerSerivce.findAllCustomerWithFilters(page, size, sortBy, direction);
	}
	
	@GetMapping("/search")
	public ApiResponseWithPaginationResponseDTO<?> findAllCustomerWithFilters(@RequestParam String keyWord,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "customerId") String sortBy,@RequestParam(defaultValue = "asc") String direction){
		
		return customerSerivce.findAllCustomerWithFilters(keyWord, page, size, sortBy, direction);
	}
	
	
	@PutMapping("/update/{customerId}")
	public ApiResponseDTO<CustomerDetailsByIdResponseDTO> customerDetailsUpdate(@PathVariable Long customerId , @RequestBody  CustomerDetailsUpdateDTO customerDetailsUpdateDTO){
		
		return customerSerivce.customerDetailsUpdate(customerId, customerDetailsUpdateDTO);
	}
	
	@PatchMapping("/partially/{customerId}")
	public ApiResponseDTO<CustomerDetailsByIdResponseDTO> customerSpecificDetailsUpdate(@PathVariable Long customerId,
		@RequestBody CustomerDetailsUpdateDTO customerDetailsUpdateDTO){
		
		return customerSerivce.customerSpecificDetailsUpdate(customerId, customerDetailsUpdateDTO);
	}
	
	@GetMapping("/status/{customerId}")
	public ApiResponseDTO<String>  CustomerDeactivate( @PathVariable  Long customerId){
		
		return customerSerivce.CustomerDeactivate(customerId);
		
	}

}
