package com.smartbakingsystem.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseDTO<T> {
	
	private boolean success;
	
	private String message;
	
	private T details;
	
	private LocalDateTime dateTime;
	
	
	

}
