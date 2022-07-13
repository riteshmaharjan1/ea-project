package com.miu.libraryapplication.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity

@Data
@NoArgsConstructor

public class BookCheckout {
    @Id
    @GeneratedValue
    private long bookCheckoutId;
    private String scanCode;
    private LocalDate checkoutDate;
    private LocalDate checkinDate;
    private LocalDate dueDate;

    public BookCheckout(String scanCode, LocalDate checkoutDate, LocalDate checkinDate, LocalDate dueDate) {
        this.scanCode = scanCode;
        this.checkoutDate = checkoutDate;
        this.checkinDate = checkinDate;
    }

}
