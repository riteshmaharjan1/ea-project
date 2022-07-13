package com.miu.libraryapplication.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity

@Data
@NoArgsConstructor

public class BookCheckout {
    @Id
    @GeneratedValue
    private long bookCheckoutId;
    @ManyToOne
    @JoinColumn(name = "customerNumber")
    private Customer customer;
    private String scanCode;
    private Date checkoutDate;
    private Date checkinDate;

    public BookCheckout(String scanCode, Date checkoutDate, Date checkinDate) {
        this.scanCode = scanCode;
        this.checkoutDate = checkoutDate;
        this.checkinDate = checkinDate;
    }
}
