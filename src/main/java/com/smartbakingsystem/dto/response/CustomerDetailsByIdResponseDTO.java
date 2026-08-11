package com.smartbakingsystem.dto.response;

import java.time.LocalDate;

import com.smartbakingsystem.Enumeration.CustomerStatus;
import com.smartbakingsystem.Enumeration.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDetailsByIdResponseDTO {
	
	private Long customerId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String phoneNumber;
	
	private LocalDate DateOfBirth;
	
	private Gender gender;
	
	private CustomerStatus customerStatus;

}
