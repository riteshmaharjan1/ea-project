package com.miu.bookcategoryclient.bookClient.book;

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