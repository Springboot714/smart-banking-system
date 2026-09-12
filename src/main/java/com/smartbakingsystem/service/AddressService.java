package com.smartbakingsystem.service;


import org.springframework.stereotype.Service;

import com.smartbakingsystem.dto.request.AddressCreationRequestDTO;
import com.smartbakingsystem.dto.request.AddressUpdateRequestDTO;
import com.smartbakingsystem.dto.response.ApiResponseDTO;

@Service
public interface AddressService {
	
	public ApiResponseDTO<?> findAddressByCustomerId(Long customerId);
	
	public ApiResponseDTO<?> updateAddressByCustomerId(Long customerId, Long addressId , AddressUpdateRequestDTO addressUpdateRequestDTO);
	
	public ApiResponseDTO<?> addAddressByCustomerId(Long customerId ,AddressCreationRequestDTO addressCreationRequestDTO);
	
	public ApiResponseDTO<?> deactivationOfAddress(Long customerId, Long addressId);
	

}
