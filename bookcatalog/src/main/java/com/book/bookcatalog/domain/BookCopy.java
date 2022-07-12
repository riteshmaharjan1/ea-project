package com.book.bookcatalog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCopy {
    private String ScanCode;
    private boolean isAvailable;
}
