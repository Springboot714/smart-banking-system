package com.smartbakingsystem.dto.request;

import java.time.LocalDate;

import com.smartbakingsystem.Enumeration.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDetailsUpdateDTO {
	
	private String firstName;
	
	private String lastName;
	
	private LocalDate dateOfBirth;
	
	private Gender gender;

}
