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
public class ApiResponseWithPaginationResponseDTO<T> {

		private String success;
		
		private String message;
		
		private T details;
		
		private PaginationResponseDTO paginationResponseDTO;
		
		private LocalDateTime dateTime;

}
