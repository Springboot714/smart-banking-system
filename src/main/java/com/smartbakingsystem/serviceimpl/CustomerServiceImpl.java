package com.smartbakingsystem.serviceimpl;

import com.smartbakingsystem.mapper.CustomerCreationMapper;

import com.smartbakingsystem.model.Customer;
import com.smartbakingsystem.repository.CustomerRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.smartbakingsystem.dto.request.CustomerCreationRequestDTO;
import com.smartbakingsystem.dto.response.ApiResponseDTO;
import com.smartbakingsystem.dto.response.ApiResponseWithPaginationResponseDTO;
import com.smartbakingsystem.dto.response.CustomerDetailsByIdResponseDTO;
import com.smartbakingsystem.dto.response.PaginationResponseDTO;
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

	@Override
	public ApiResponseWithPaginationResponseDTO<?> findAllCustomerWithFilters(int page, int size, String sortBy, String direction) {
		
		Pageable theCustomerWithFilter = CustomerCreationMapper.findTheCustomerWithFilter(page, size, sortBy, direction);
		
		Page<Customer> allCustomer = customerRepository.findAll(theCustomerWithFilter);
		
		PaginationResponseDTO paginationResponseDTO = PaginationResponseDTO.builder()
													  .first(allCustomer.isFirst())
													  .last(allCustomer.isLast())
													  .page(allCustomer.getNumber())
													  .size(allCustomer.getSize())
													  .totalElements(allCustomer.getTotalElements())
													  .totalPages(allCustomer.getTotalPages())
													  .build();
		
		ApiResponseWithPaginationResponseDTO<List<CustomerDetailsByIdResponseDTO>> apiResponseDTO= new ApiResponseWithPaginationResponseDTO<List<CustomerDetailsByIdResponseDTO>>();
		
		List<Customer> customers= allCustomer.getContent();
		
		List<CustomerDetailsByIdResponseDTO> customerDetailsByIdResponseDTOs= new ArrayList<CustomerDetailsByIdResponseDTO>();
		
		for(Customer customer: customers) {
			
			CustomerDetailsByIdResponseDTO byIdResponseDTO= CustomerDetailsByIdResponseDTO.builder()
															.customerId(customer.getCustomerId())
															.customerStatus(customer.getCustomerStatus())
															.DateOfBirth(customer.getDateOfBirth())
															.email(customer.getEmail())
															.firstName(customer.getFirstName())
															.gender(customer.getGender())
															.lastName(customer.getLastName())
															.phoneNumber(customer.getPhoneNumber()).build();
			customerDetailsByIdResponseDTOs.add(byIdResponseDTO);
			
		}
		apiResponseDTO.setDateTime(LocalDateTime.now());
		apiResponseDTO.setMessage("Customers retrieved successfully");
		apiResponseDTO.setDetails(customerDetailsByIdResponseDTOs);
		apiResponseDTO.setPaginationResponseDTO(paginationResponseDTO);
		apiResponseDTO.setSuccess("true");
		return apiResponseDTO;
	}

}

