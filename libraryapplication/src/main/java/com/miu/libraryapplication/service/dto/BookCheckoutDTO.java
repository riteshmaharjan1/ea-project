package com.miu.libraryapplication.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class BookCheckoutDTO {
    private long bookCheckoutId;
    private long customerNumber;
    private String scanCode;
    private Date checkoutDate;
    private Date checkinDate;

    public BookCheckoutDTO(long customerNumber, String scanCode, Date checkoutDate, Date checkinDate) {
        this.customerNumber = customerNumber;
        this.scanCode = scanCode;
        this.checkoutDate = checkoutDate;
        this.checkinDate = checkinDate;
    }
}
