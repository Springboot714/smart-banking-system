package com.smartbakingsystem.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.smartbakingsystem.Enumeration.AddressStatus;
import com.smartbakingsystem.Enumeration.CustomerStatus;
import com.smartbakingsystem.Enumeration.KycStatus;
import com.smartbakingsystem.dto.request.AddressCreationRequestDTO;
import com.smartbakingsystem.dto.request.CustomerCreationRequestDTO;
import com.smartbakingsystem.model.Address;
import com.smartbakingsystem.model.Customer;
import com.smartbakingsystem.model.CustomerKyc;

@Component
public class CustomerCreationMapper {
	
	
	public static Customer customercreation(CustomerCreationRequestDTO requestDTO) {
		
		System.out.println(requestDTO);
		
		List<AddressCreationRequestDTO> addressCreationRequestDTOs = requestDTO.getAddressCreationRequestDTOs();
		
		
		
		
		
								 
		
		Customer customer= Customer.builder().firstName(requestDTO.getFirstName())
						   .lastName(requestDTO.getLastName())
						   .dateOfBirth(requestDTO.getDateOfBirth())
						   .email(requestDTO.getEmail())
						   .gender(requestDTO.getGender())
						   .phoneNumber(requestDTO.getPhoneNumber())
						   .password(requestDTO.getPassword())
						   .customerStatus(CustomerStatus.ACTIVE).build();
		List<Address> addresses= new ArrayList<Address>();
		
		for(AddressCreationRequestDTO add: addressCreationRequestDTOs) {
			
			Address address= Address.builder().addressLine1(add.getAddressLine1())
							.addressLine2(add.getAddressLine2())
							.addressStatus(AddressStatus.ACTIVE)
							.addressType(add.getAddressType())
							.city(add.getCity())
							.country(add.getCountry())
							.district(add.getDistrict())
							.landmark(add.getLandmark())
							.postalCode(add.getPostalCode())
							.state(add.getState())
							.customer(customer)
							.build();
			addresses.add(address);
		}
		CustomerKyc customerKyc= CustomerKyc.builder()
				 .aadhaarNumber(requestDTO.getCustomerKycCreationRequestDTO().getAadhaarNumber())
				 .kycStatus(KycStatus.PENDING)
				 .kycType(requestDTO.getCustomerKycCreationRequestDTO().getKycType())
				 .panNumber(requestDTO.getCustomerKycCreationRequestDTO().getPanNumber())
				 .customer(customer)
				 .build();
		customer.setAddresses(addresses);
		
		customer.setCustomerKyc(customerKyc);
		return customer;
						   
		
	}

}
