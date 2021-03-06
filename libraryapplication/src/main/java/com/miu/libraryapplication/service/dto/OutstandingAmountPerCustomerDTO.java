package com.miu.libraryapplication.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutstandingAmountPerCustomerDTO {
    private Number customerNumber;
    private String name;
    private Double outstandingFee;
}
