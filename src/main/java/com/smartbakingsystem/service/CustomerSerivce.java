package com.smartbakingsystem.service;

import com.smartbakingsystem.dto.request.CustomerCreationRequestDTO;
import com.smartbakingsystem.dto.response.ApiResponseDTO;
import com.smartbakingsystem.dto.response.CustomerDetailsByIdResponseDTO;

public interface CustomerSerivce {
	
	
	
	
	public ApiResponseDTO<Long> CustomerSave(CustomerCreationRequestDTO creationRequestDTO);
	
	public ApiResponseDTO<CustomerDetailsByIdResponseDTO> findCustomerById(Long customerId);
}
