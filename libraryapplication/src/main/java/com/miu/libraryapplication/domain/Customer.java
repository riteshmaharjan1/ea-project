package com.miu.libraryapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "customerSeq", sequenceName = "customer_seq")
@Data
@NoArgsConstructor

public class Customer {
    @Id
    @GeneratedValue(generator = "customerSeq")
    private Long customerNumber;
    private String name;
    private String email;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
