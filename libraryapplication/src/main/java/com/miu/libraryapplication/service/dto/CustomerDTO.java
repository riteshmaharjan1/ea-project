package com.miu.libraryapplication.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDTO {
    private long customerNumber;
    private String name;
    private String email;

    public CustomerDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
