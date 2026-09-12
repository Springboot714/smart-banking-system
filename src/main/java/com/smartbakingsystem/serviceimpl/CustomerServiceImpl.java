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

import com.smartbakingsystem.Enumeration.CustomerStatus;
import com.smartbakingsystem.dto.request.CustomerCreationRequestDTO;
import com.smartbakingsystem.dto.request.CustomerDetailsUpdateDTO;
import com.smartbakingsystem.dto.response.ApiResponseDTO;
import com.smartbakingsystem.dto.response.ApiResponseWithPaginationResponseDTO;
import com.smartbakingsystem.dto.response.CustomerDetailsByIdResponseDTO;
import com.smartbakingsystem.dto.response.PaginationResponseDTO;
import com.smartbakingsystem.exception.CustomerIdNotFoundException;
import com.smartbakingsystem.exception.EmailAlreadyExistImpl;
import com.smartbakingsystem.service.CustomerSerivce;

@Service
public class CustomerServiceImpl implements CustomerSerivce{

	@Autowired
	CustomerRepository customerRepository;

	

	@Override
	public ApiResponseDTO<Long> CustomerSave(CustomerCreationRequestDTO creationRequestDTO) {
		Customer customercreation = CustomerCreationMapper.customercreation(creationRequestDTO);
		
		EmailAlreadyExistImpl.emailAlreadyExistmethod(customerRepository.existsByEmail(creationRequestDTO.getEmail()));
		
		
		Customer save = customerRepository.save(customercreation);
		
		
		ApiResponseDTO<Long> apiResponseDTO = new ApiResponseDTO<Long>();
		
		apiResponseDTO.setDateTime(LocalDateTime.now());
		apiResponseDTO.setDetails(save.getCustomerId());
		apiResponseDTO.setMessage("Customer created successfully");
		apiResponseDTO.setSuccess(true);
											  
		
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
		apiResponseDTO.setSuccess(true);
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

	@Override
	public ApiResponseWithPaginationResponseDTO<?> findAllCustomerWithFilters(String keyWord, int page, int size,
			String sortBy, String direction) {
		
		
		if(keyWord.equals(null)) {
			CustomerServiceImpl customerServiceImpl= new CustomerServiceImpl();
			ApiResponseWithPaginationResponseDTO<?> allCustomerWithFilters = customerServiceImpl.findAllCustomerWithFilters(page, size, sortBy, direction);
			
			return allCustomerWithFilters;
		}
		else {
			System.out.println("The Keyword Value is "+keyWord);
			Page<Customer> searchCustomers = customerRepository.searchCustomers(keyWord, CustomerCreationMapper.findTheCustomerWithFilter(page, size, sortBy, direction));
			
			List<Customer> content = searchCustomers.getContent();
			
			ApiResponseWithPaginationResponseDTO<List<CustomerDetailsByIdResponseDTO>> apiResponseWithPaginationResponseDTO=new ApiResponseWithPaginationResponseDTO<List<CustomerDetailsByIdResponseDTO>>();
			
			List<CustomerDetailsByIdResponseDTO> byIdResponseDTOs= new ArrayList<CustomerDetailsByIdResponseDTO>();
			
			for(Customer customer:content) {
				
				CustomerDetailsByIdResponseDTO byIdResponseDTO=CustomerDetailsByIdResponseDTO.builder()
																.customerId(customer.getCustomerId())
																.customerStatus(customer.getCustomerStatus())
																.DateOfBirth(customer.getDateOfBirth())
																.email(customer.getEmail())
																.firstName(customer.getFirstName())
																.gender(customer.getGender())
																.lastName(customer.getLastName())
																.phoneNumber(customer.getPhoneNumber())
																.build();
				byIdResponseDTOs.add(byIdResponseDTO);
				
			}
			
			PaginationResponseDTO paginationResponseDTO =  PaginationResponseDTO.builder()
														   .first(searchCustomers.isFirst())
														   .last(searchCustomers.isLast())
														   .page(searchCustomers.getNumber())
														   .size(searchCustomers.getSize())
														   .totalElements(searchCustomers.getTotalElements())
														   .totalPages(searchCustomers.getTotalPages())
														   .build();
														  
			apiResponseWithPaginationResponseDTO.setDateTime(LocalDateTime.now());
			apiResponseWithPaginationResponseDTO.setDetails(byIdResponseDTOs);
			apiResponseWithPaginationResponseDTO.setPaginationResponseDTO(paginationResponseDTO);
			apiResponseWithPaginationResponseDTO.setMessage("Customers retrieved successfully");
			apiResponseWithPaginationResponseDTO.setSuccess("true");
			
			return apiResponseWithPaginationResponseDTO;
			
		}
		
	}

	@Override
	public ApiResponseDTO<CustomerDetailsByIdResponseDTO> customerDetailsUpdate(Long customerId , CustomerDetailsUpdateDTO customerDetailsUpdateDTO) {
		
		Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new CustomerIdNotFoundException("Customer Id Not Found"));
		
		customer.setFirstName(customerDetailsUpdateDTO.getFirstName());
		customer.setLastName(customerDetailsUpdateDTO.getLastName());
		customer.setDateOfBirth(customerDetailsUpdateDTO.getDateOfBirth());
		customer.setGender(customerDetailsUpdateDTO.getGender());
		customerRepository.save(customer);
		
		CustomerDetailsByIdResponseDTO byIdResponseDTO= CustomerDetailsByIdResponseDTO.builder()
														.customerId(customerId)
														.customerStatus(customer.getCustomerStatus())
														.DateOfBirth(customer.getDateOfBirth())
														.email(customer.getEmail())
														.firstName(customer.getFirstName())
														.gender(customer.getGender())
														.lastName(customer.getLastName())
														.phoneNumber(customer.getPhoneNumber())
														.build();
		ApiResponseDTO<CustomerDetailsByIdResponseDTO> apiResponseDTO =new  ApiResponseDTO<CustomerDetailsByIdResponseDTO>();
		apiResponseDTO.setDateTime(LocalDateTime.now());
		apiResponseDTO.setDetails(byIdResponseDTO);
		apiResponseDTO.setMessage("Customers retrieved successfully");
		apiResponseDTO.setSuccess(true);
	
		
		return apiResponseDTO;
	}

	@Override
	public ApiResponseDTO<CustomerDetailsByIdResponseDTO> customerSpecificDetailsUpdate(
	        Long customerId,
	        CustomerDetailsUpdateDTO customerDetailsUpdateDTO) {
		
	    Customer customer = customerRepository.findById(customerId)
	            .orElseThrow(() ->
	                    new CustomerIdNotFoundException("Customer Id Not Found"));
	    

	    if (customerDetailsUpdateDTO.getDateOfBirth() != null) {
	    	customer.setDateOfBirth(
	                customerDetailsUpdateDTO.getDateOfBirth()
	        );
	    }
	    

	    if (customerDetailsUpdateDTO.getFirstName() != null) {
	    	customer.setFirstName(
	                customerDetailsUpdateDTO.getFirstName()
	        );
	    }
	    

	    if (customerDetailsUpdateDTO.getGender() != null) {
	    	customer.setGender(
	                customerDetailsUpdateDTO.getGender()
	        );
	    }
	    

	    if (customerDetailsUpdateDTO.getLastName() != null) {
	    	customer.setLastName(
	                customerDetailsUpdateDTO.getLastName()
	        );
	    }
	    
	    customerRepository.save(customer);

	    CustomerDetailsByIdResponseDTO byIdResponseDTO =
	            CustomerDetailsByIdResponseDTO.builder()
	                    .customerId(customerId)
	                    .customerStatus(customer.getCustomerStatus())
	                    .DateOfBirth(customer.getDateOfBirth())
	                    .email(customer.getEmail())
	                    .firstName(customer.getFirstName())
	                    .gender(customer.getGender())
	                    .lastName(customer.getLastName())
	                    .phoneNumber(customer.getPhoneNumber())
	                    .build();

	  
	    ApiResponseDTO<CustomerDetailsByIdResponseDTO> apiResponseDTO =
	            new ApiResponseDTO<>();

	    apiResponseDTO.setDateTime(LocalDateTime.now());
	    apiResponseDTO.setDetails(byIdResponseDTO);
	    apiResponseDTO.setMessage("Customer Details Updated successfully");
	    apiResponseDTO.setSuccess(true);

	    return apiResponseDTO;
	}

	@Override
	public ApiResponseDTO<String> CustomerDeactivate(Long customerId) {
		
	    Customer customer = customerRepository.findById(customerId)
	            .orElseThrow(() ->
	                    new CustomerIdNotFoundException("Customer Id Not Found"));
	    String response;
	    
	    if(customer.getCustomerStatus()==CustomerStatus.INACTIVE) {
	    	response="Customer is already inactive";
	    }
	    
	    else {
	    	customer.setCustomerStatus(CustomerStatus.INACTIVE);
	    	customerRepository.save(customer);
	    	response="Customer has been Inactive" ;
	    }
	    
	    ApiResponseDTO<String> apiResponseDTO= new ApiResponseDTO<String>();
	    
	    apiResponseDTO.setDateTime(LocalDateTime.now());
	    apiResponseDTO.setDetails(response);
	    apiResponseDTO.setMessage("Customer retrieved successfully");
	    apiResponseDTO.setSuccess(true);
		return apiResponseDTO;
	}

}

