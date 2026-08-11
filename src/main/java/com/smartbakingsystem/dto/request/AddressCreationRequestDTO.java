package com.smartbakingsystem.dto.request;

import com.smartbakingsystem.Enumeration.AddressType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressCreationRequestDTO {
	
	
	private AddressType addressType; 
	
	
	private String addressLine1;
	
	private String addressLine2;
	
	private String landmark;
	
	
	private String city;
	

	private String district;
	
	
	private String state;
	
	
	private String country;
	
	
	private String postalCode;
	
	

}
