package com.miu.libraryapplication.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutstandingAmountPerCustomerDTO {
    private String customerNumber;
    private String name;
    private double outstandingFee;
}
