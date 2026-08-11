package com.smartbakingsystem.dto.request;


import com.smartbakingsystem.Enumeration.KycType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerKycCreationRequestDTO {

    private KycType kycType;

    private Long aadhaarNumber;

    private Long panNumber;
}
