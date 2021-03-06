package com.miu.libraryapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity

@Data
@NoArgsConstructor
public class PaymentEntry {
    @Id
    @GeneratedValue
    private long paymentEntryId;
    private LocalDate paymentDate;
    private double amount;

    public PaymentEntry(LocalDate paymentDate, double amount) {
        this.paymentDate = paymentDate;
        this.amount = amount;
    }
}
