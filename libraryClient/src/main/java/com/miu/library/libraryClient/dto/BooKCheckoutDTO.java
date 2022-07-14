package com.miu.library.libraryClient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooKCheckoutDTO {
    private long bookCheckoutId;
    private long customerNumber;
    private String scanCode;
    private LocalDate checkoutDate;
    private LocalDate checkinDate;
    private LocalDate dueDate;
}
