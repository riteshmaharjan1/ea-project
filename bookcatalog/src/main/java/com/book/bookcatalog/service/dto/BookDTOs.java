package com.book.bookcatalog.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTOs {
    private Collection<BookDTO> bookDTOS;

}
