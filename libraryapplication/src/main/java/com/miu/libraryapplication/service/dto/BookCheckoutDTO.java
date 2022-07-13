package com.miu.libraryapplication.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class BookCheckoutDTO {
    private long bookCheckoutId;
    private long customerNumber;
    private String scanCode;
    private LocalDate checkoutDate;
    private LocalDate checkinDate;
    private LocalDate dueDate;

    public BookCheckoutDTO(long customerNumber, String scanCode, LocalDate checkoutDate, LocalDate checkinDate, LocalDate dueDate) {
        this.customerNumber = customerNumber;
        this.scanCode = scanCode;
        this.checkoutDate = checkoutDate;
        this.checkinDate = checkinDate;
        this.dueDate = dueDate;
    }
}
