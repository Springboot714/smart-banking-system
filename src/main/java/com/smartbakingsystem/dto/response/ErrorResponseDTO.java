package com.smartbakingsystem.dto.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.smartbakingsystem.Enumeration.ApplicationStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseDTO {
	
    private boolean success;
    private String message;
    private ApplicationStatus errorCode;
    private Object details;
    private LocalDateTime dateTime;

}
