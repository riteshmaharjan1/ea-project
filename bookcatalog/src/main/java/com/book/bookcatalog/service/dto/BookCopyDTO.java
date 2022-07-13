package com.book.bookcatalog.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCopyDTO {
    private String scanCode;
    private Boolean isAvailable;
}
