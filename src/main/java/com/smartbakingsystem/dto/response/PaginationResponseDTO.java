package com.smartbakingsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationResponseDTO {
	
	    private int page;

	    private int size;

	    private long totalElements;

	    private int totalPages;

	    private boolean first;

	    private boolean last;

}
