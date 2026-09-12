package com.smartbakingsystem.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartbakingsystem.Enumeration.AddressStatus;
import com.smartbakingsystem.dto.request.AddressCreationRequestDTO;
import com.smartbakingsystem.dto.request.AddressUpdateRequestDTO;
import com.smartbakingsystem.dto.response.AddressResponseDTO;
import com.smartbakingsystem.dto.response.ApiResponseDTO;
import com.smartbakingsystem.exception.AddressAlreadyInactiveExceptionImpl;
import com.smartbakingsystem.exception.AddressAndcustomerNotMatchExpectionImpl;
import com.smartbakingsystem.exception.AddressNotFoundException;
import com.smartbakingsystem.exception.CustomerIdNotFoundException;
import com.smartbakingsystem.model.Address;
import com.smartbakingsystem.model.Customer;
import com.smartbakingsystem.repository.AddressRepository;
import com.smartbakingsystem.repository.CustomerRepository;
import com.smartbakingsystem.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{
	
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AddressRepository addressRepository;

	@Override
	public ApiResponseDTO<?> findAddressByCustomerId(Long customerId) {
		
		 Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new CustomerIdNotFoundException("Customer Id Not Found"));
		
		 List<Address> byCustomer = addressRepository.findByCustomer(customer);
		 
		 
		 ApiResponseDTO<List<AddressResponseDTO>> addressResponseDTO= new ApiResponseDTO<List<AddressResponseDTO>>();
		 
		 List<AddressResponseDTO> addressResponseDTOs= new ArrayList<AddressResponseDTO>();
		 
		 for(Address address:byCustomer) {
			 
			 AddressResponseDTO response=
					 AddressResponseDTO.builder()
					 .addressId(address.getAddressId())
					 .addressLine1(address.getAddressLine1())
					 .addressLine2(address.getAddressLine2())
					 .addressStatus(address.getAddressStatus())
					 .addressType(address.getAddressType())
					 .city(address.getCity())
					 .country(address.getCountry())
					 .district(address.getDistrict())
					 .landmark(address.getLandmark())
					 .postalCode(address.getPostalCode())
					 .state(address.getState())
					 .build();
			 
			 addressResponseDTOs.add(response);
			 
		 }
		 
		 addressResponseDTO.setDetails(addressResponseDTOs);
		 addressResponseDTO.setDateTime(LocalDateTime.now());
		 addressResponseDTO.setMessage("Customer Address Retived Succcesfully");
		 addressResponseDTO.setSuccess(true);
		
		
		return addressResponseDTO;
	}

	@Override
	public ApiResponseDTO<?> updateAddressByCustomerId(Long customerId, Long addressId , AddressUpdateRequestDTO addressUpdateRequestDTO) {
		 Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new CustomerIdNotFoundException("Customer Id Not Found"));
		
		 List<Address> byCustomer = addressRepository.findByCustomer(customer);
		 
		 
		  Address address = addressRepository.findById(addressId).orElseThrow(() -> new AddressNotFoundException("Address Not Found"));
		  
		  boolean isCustomerAddressmatched=false;
		  
		  for(Address addressInd: byCustomer) {
			  
			  if(addressInd.getCustomer()==customer) {
				  isCustomerAddressmatched=true;
			  }
		  }
		  
		  AddressAndcustomerNotMatchExpectionImpl.addressAndcustomerNotMatchExpectionMethod(isCustomerAddressmatched);
		 
         if(addressUpdateRequestDTO.getAddressLine1()!=null) {
        	 address.setAddressLine1(addressUpdateRequestDTO.getAddressLine1());
         }
         if(addressUpdateRequestDTO.getAddressLine2()!= null) {
        	 address.setAddressLine2(addressUpdateRequestDTO.getAddressLine2());
         }
         if(addressUpdateRequestDTO.getCity()!=null) {
        	 address.setCity(addressUpdateRequestDTO.getCity());
         }
         if(addressUpdateRequestDTO.getCountry()!=null) {
        	 address.setCountry(addressUpdateRequestDTO.getCountry());
         }
         if(addressUpdateRequestDTO.getDistrict()!=null) {
        	 address.setDistrict(addressUpdateRequestDTO.getDistrict());
         }
         if(addressUpdateRequestDTO.getLandmark()!=null) {
        	 address.setLandmark(addressUpdateRequestDTO.getLandmark());
         }
         if(addressUpdateRequestDTO.getPostalCode()!=null) {
        	 address.setPostalCode(addressUpdateRequestDTO.getPostalCode());
         }
         if(addressUpdateRequestDTO.getState()!=null) {
        	 address.setState(addressUpdateRequestDTO.getState());
         }
         
         addressRepository.save(address);
         
         ApiResponseDTO<AddressResponseDTO> apiResponseDTO= new ApiResponseDTO<AddressResponseDTO>();
         
         apiResponseDTO.setDateTime(LocalDateTime.now());
         AddressResponseDTO addressResponseDTO= AddressResponseDTO.builder()
        		 								.addressId(address.getAddressId())
        		 								.addressLine1(address.getAddressLine1())
        		 								.addressLine2(address.getAddressLine2())
        		 								.addressStatus(address.getAddressStatus())
        		 								.addressType(address.getAddressType())
        		 								.city(address.getCity())
        		 								.country(address.getCountry())
        		 								.district(address.getDistrict())
        		 								.landmark(address.getLandmark())
        		 								.postalCode(address.getPostalCode())
        		 								.state(address.getState())
        		 								.build();
         apiResponseDTO.setDateTime(LocalDateTime.now());
         apiResponseDTO.setDetails(addressResponseDTO);
         apiResponseDTO.setMessage("The Address has been Update Sucessfully");
         apiResponseDTO.setSuccess(true);
		 
		 
		return apiResponseDTO;
	}

	@Override
	public ApiResponseDTO<?> addAddressByCustomerId(Long customerId , AddressCreationRequestDTO addressCreationRequestDTO) {
		
		Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new CustomerIdNotFoundException("Customer Id Not Found"));
		
		List<Address> AddressList = addressRepository.findByCustomer(customer);
		
		System.out.println(AddressList);
		
		Address address= Address.builder()
						.addressLine1(addressCreationRequestDTO.getAddressLine1())
						.addressLine2(addressCreationRequestDTO.getAddressLine2())
						.addressStatus(AddressStatus.ACTIVE)
						.addressType(addressCreationRequestDTO.getAddressType())
						.city(addressCreationRequestDTO.getCity())
						.country(addressCreationRequestDTO.getCountry())
						.state(addressCreationRequestDTO.getState())
						.district(addressCreationRequestDTO.getDistrict())
						.landmark(addressCreationRequestDTO.getLandmark())
						.postalCode(addressCreationRequestDTO.getPostalCode())
						.customer(customer)
						.build();
		
		AddressList.add(address);
		
		customer.setAddresses(AddressList);
		
		Address save = addressRepository.save(address);
		
		AddressResponseDTO addressResponseDTO= new AddressResponseDTO();
		
		addressResponseDTO.setAddressId(save.getAddressId());
		addressResponseDTO.setAddressLine1(save.getAddressLine1());
		addressResponseDTO.setAddressLine2(save.getAddressLine2());
		addressResponseDTO.setAddressStatus(save.getAddressStatus());
		addressResponseDTO.setAddressType(save.getAddressType());
		addressResponseDTO.setCity(save.getCity());
		addressResponseDTO.setCountry(save.getCountry());
		addressResponseDTO.setDistrict(save.getDistrict());
		addressResponseDTO.setLandmark(save.getLandmark());
		addressResponseDTO.setPostalCode(save.getPostalCode());
		addressResponseDTO.setState(save.getState());
		
		
		ApiResponseDTO<AddressResponseDTO> apiResponseDTO= new ApiResponseDTO<AddressResponseDTO>();
		
		apiResponseDTO.setDetails(addressResponseDTO);
		apiResponseDTO.setDateTime(LocalDateTime.now());
		apiResponseDTO.setMessage( "Address added successfully");
		apiResponseDTO.setSuccess(true);
		
		
		
		return apiResponseDTO;
	}

	@Override
	public ApiResponseDTO<?> deactivationOfAddress(Long customerId, Long addressId) {
		
		Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new CustomerIdNotFoundException("Customer Id Not Found"));
		
		
		List<Address> byCustomer = addressRepository.findByCustomer(customer);
		
		System.out.println(byCustomer);
		 
		 
		  Address address = addressRepository.findById(addressId).orElseThrow(() -> new AddressNotFoundException("Address Not Found"));
		  
		  boolean isCustomerAddressmatched=true;
		  
		  for(Address addressInd: byCustomer) {
			  
			  if(addressInd.getAddressId()==addressId) {
				  isCustomerAddressmatched=false;
			  }
		  }
		  
		  AddressAndcustomerNotMatchExpectionImpl.addressAndcustomerNotMatchExpectionMethod(isCustomerAddressmatched);
		  
		  AddressAlreadyInactiveExceptionImpl.AddressAlreadyInactiveExceptionmethod(address.getAddressStatus());
		  
		  address.setAddressStatus(AddressStatus.INACTIVE);
		  
		  Address save = addressRepository.save(address);
		  
		  AddressResponseDTO addressResponseDTO= new AddressResponseDTO();
		  
		  addressResponseDTO.setAddressId(save.getAddressId());
		  addressResponseDTO.setAddressLine1(save.getAddressLine1());
		  addressResponseDTO.setAddressLine2(save.getAddressLine2());
		  addressResponseDTO.setAddressStatus(save.getAddressStatus());
		  addressResponseDTO.setAddressType(save.getAddressType());
		  addressResponseDTO.setCity(save.getCity());
		  addressResponseDTO.setCountry(save.getCountry());
		  addressResponseDTO.setDistrict(save.getDistrict());
		  addressResponseDTO.setLandmark(save.getLandmark());
		  addressResponseDTO.setPostalCode(save.getPostalCode());
		  addressResponseDTO.setState(save.getState());
		  
		  
		  ApiResponseDTO<AddressResponseDTO> apiResponseDTO= new ApiResponseDTO<AddressResponseDTO>();
		  
		  apiResponseDTO.setDateTime(LocalDateTime.now());
		  apiResponseDTO.setDetails(addressResponseDTO);
		  apiResponseDTO.setMessage(" The Address has been Deactivated");
		  apiResponseDTO.setSuccess(true);
		
		
		
		return apiResponseDTO;
	}

}
