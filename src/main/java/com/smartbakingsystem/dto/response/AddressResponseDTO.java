package com.smartbakingsystem.dto.response;

import com.smartbakingsystem.Enumeration.AddressStatus;
import com.smartbakingsystem.Enumeration.AddressType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressResponseDTO {
	
	private Long addressId;
	
	private AddressType addressType;
	
	private String addressLine1;
	
	private String addressLine2;
	
	
	private String city;
	
	private String landmark;
	
	private String district;
	
	private String state;
	
	private String country;
	
	private String postalCode;
	
	private AddressStatus addressStatus;

}
