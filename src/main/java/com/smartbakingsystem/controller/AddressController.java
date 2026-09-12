package com.smartbakingsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartbakingsystem.dto.request.AddressCreationRequestDTO;
import com.smartbakingsystem.dto.request.AddressUpdateRequestDTO;
import com.smartbakingsystem.dto.response.ApiResponseDTO;
import com.smartbakingsystem.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	@GetMapping("/{customerId}")
	public ApiResponseDTO<?> findAddressByCustomerId(@PathVariable Long customerId){
		
		return addressService.findAddressByCustomerId(customerId);
		
		
	}
	
	@PatchMapping("/{customerId}/{addressId}")
	public ApiResponseDTO<?> updateAddressByCustomerId(@PathVariable Long customerId, @PathVariable Long addressId ,@RequestBody  AddressUpdateRequestDTO addressUpdateRequestDTO){
		
		return addressService.updateAddressByCustomerId(customerId, addressId, addressUpdateRequestDTO);
	}
	
	@PostMapping("/add/{customerId}")
	public ApiResponseDTO<?> addAddressByCustomerId(@PathVariable Long customerId ,@RequestBody AddressCreationRequestDTO addressCreationRequestDTO){
		
		return addressService.addAddressByCustomerId(customerId, addressCreationRequestDTO);
		
	}
	
	@PutMapping("/deactivate/{customerId}/{addressId}")
	public ApiResponseDTO<?> deactivationOfAddress(@PathVariable Long customerId,@PathVariable Long addressId){
		
		return addressService.deactivationOfAddress(customerId, addressId);
		
	}


}
