package com.smartbakingsystem.service;

import com.smartbakingsystem.dto.request.CustomerCreationRequestDTO;
import com.smartbakingsystem.dto.request.CustomerDetailsUpdateDTO;
import com.smartbakingsystem.dto.response.ApiResponseDTO;
import com.smartbakingsystem.dto.response.ApiResponseWithPaginationResponseDTO;
import com.smartbakingsystem.dto.response.CustomerDetailsByIdResponseDTO;
import com.smartbakingsystem.model.Customer;

public interface CustomerSerivce {
	
	
	
	
	public ApiResponseDTO<Long> CustomerSave(CustomerCreationRequestDTO creationRequestDTO);
	
	public ApiResponseDTO<CustomerDetailsByIdResponseDTO> findCustomerById(Long customerId);
	
	public ApiResponseWithPaginationResponseDTO<?> findAllCustomerWithFilters(int page, int size, String sortBy, String direction);
	
	public ApiResponseWithPaginationResponseDTO<?> findAllCustomerWithFilters(String keyWord,int page, int size, String sortBy, String direction);
	
	public ApiResponseDTO<CustomerDetailsByIdResponseDTO> customerDetailsUpdate(Long customerId , CustomerDetailsUpdateDTO customerDetailsUpdateDTO);
	
	public ApiResponseDTO<CustomerDetailsByIdResponseDTO> customerSpecificDetailsUpdate(Long customerId , CustomerDetailsUpdateDTO customerDetailsUpdateDTO);
	
	public ApiResponseDTO<String>  CustomerDeactivate(Long customerId);
	
}
