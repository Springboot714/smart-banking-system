package com.smartbakingsystem.dto.request;

import java.time.LocalDate;
import java.util.List;
import com.smartbakingsystem.Enumeration.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerCreationRequestDTO {
	
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private String phoneNumber;
	
	private LocalDate dateOfBirth;
	
	private Gender gender;
	
	
	private List<AddressCreationRequestDTO> addressCreationRequestDTOs;
	
	private CustomerKycCreationRequestDTO customerKycCreationRequestDTO;
	
	

}
