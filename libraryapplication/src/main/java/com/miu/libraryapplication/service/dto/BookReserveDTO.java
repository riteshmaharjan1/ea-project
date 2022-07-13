package com.miu.libraryapplication.service.dto;

import com.miu.libraryapplication.domain.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class BookReserveDTO {
    private long reserveId;
    private long customerNumber;
    private String scanCode;
    private LocalDate reserveDate;
    private Boolean closed;

    public BookReserveDTO(long customerNumber, String scanCode, LocalDate reserveDate, Boolean closed) {
        this.customerNumber = customerNumber;
        this.scanCode = scanCode;
        this.reserveDate = reserveDate;
        this.closed = closed;
    }
}
