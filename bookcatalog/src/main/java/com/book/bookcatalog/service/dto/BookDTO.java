package com.book.bookcatalog.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BookDTO {
    private String isbn;
    private String title;
    private List<AuthorDTO> authorName = new ArrayList<>();
    private List<BookCopyDTO> bookCopies = new ArrayList<>();

}