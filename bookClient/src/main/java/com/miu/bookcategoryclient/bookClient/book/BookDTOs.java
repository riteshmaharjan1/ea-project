package com.miu.bookcategoryclient.bookClient.book;

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
