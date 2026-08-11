package com.smartbakingsystem.serviceimpl;

import com.smartbakingsystem.mapper.CustomerCreationMapper;
import com.smartbakingsystem.model.Customer;
import com.smartbakingsystem.repository.CustomerRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartbakingsystem.dto.request.CustomerCreationRequestDTO;
import com.smartbakingsystem.dto.response.ApiResponseDTO;
import com.smartbakingsystem.dto.response.CustomerDetailsByIdResponseDTO;
import com.smartbakingsystem.exception.CustomerIdNotFoundException;
import com.smartbakingsystem.service.CustomerSerivce;

@Service
public class CustomerServiceImpl implements CustomerSerivce{

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public ApiResponseDTO<Long> CustomerSave(CustomerCreationRequestDTO creationRequestDTO) {
		Customer customercreation = CustomerCreationMapper.customercreation(creationRequestDTO);
		
		Customer save = customerRepository.save(customercreation);
		
		
		ApiResponseDTO<Long> apiResponseDTO = new ApiResponseDTO<Long>();
		
		apiResponseDTO.setDateTime(LocalDateTime.now());
		apiResponseDTO.setDetails(save.getCustomerId());
		apiResponseDTO.setMessage("Customer created successfully");
		apiResponseDTO.setSuccess("true");
											  
		
		return apiResponseDTO;
	}

	@Override
	public ApiResponseDTO<CustomerDetailsByIdResponseDTO> findCustomerById(Long customerId) {
		
		Customer byId = customerRepository.findById(customerId).orElseThrow(() -> new CustomerIdNotFoundException("Customer Not Found with Customer Id : "+customerId));
		
		CustomerDetailsByIdResponseDTO byIdResponseDTO=  CustomerDetailsByIdResponseDTO
		.builder().customerId(byId.getCustomerId()).customerStatus(byId.getCustomerStatus())
		.DateOfBirth(byId.getDateOfBirth()).email(byId.getEmail()).firstName(byId.getFirstName())
		.lastName(byId.getLastName()).gender(byId.getGender()).phoneNumber(byId.getPhoneNumber())
		.build();
		
		ApiResponseDTO<CustomerDetailsByIdResponseDTO> apiResponseDTO=  new ApiResponseDTO<CustomerDetailsByIdResponseDTO>();
		apiResponseDTO.setSuccess("true");
		apiResponseDTO.setMessage("Customer retrieved successfully");
		apiResponseDTO.setDetails(byIdResponseDTO);
		apiResponseDTO.setDateTime(LocalDateTime.now());
		return apiResponseDTO;
	}

}

