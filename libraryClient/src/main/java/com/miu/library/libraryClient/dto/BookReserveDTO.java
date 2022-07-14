package com.miu.library.libraryClient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookReserveDTO {
    private long reserveId;
    private long customerNumber;
    private String scanCode;
    private LocalDate reserveDate;
    private Boolean closed;
}
