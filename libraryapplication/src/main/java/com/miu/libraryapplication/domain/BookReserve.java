package com.miu.libraryapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class BookReserve {
    @Id
    @GeneratedValue
    private long reserveId;

    //    @ManyToOne
//    @JoinColumn(name = "customerNumber")
//    private Customer customer;
    private String scanCode;
    private LocalDate reserveDate;
    private Boolean closed = false;

    public BookReserve(String scanCode, LocalDate reserveDate, Boolean closed) {
        this.scanCode = scanCode;
        this.reserveDate = reserveDate;
        this.closed = closed;
    }
}
