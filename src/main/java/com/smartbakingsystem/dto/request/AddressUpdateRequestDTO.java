package com.smartbakingsystem.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressUpdateRequestDTO {
	
	
	
	private String addressLine1;
	
	private String addressLine2;
	
	private String landmark;
	
	
	private String city;
	

	private String district;
	
	
	private String state;
	
	
	private String country;
	
	
	private String postalCode;

}
