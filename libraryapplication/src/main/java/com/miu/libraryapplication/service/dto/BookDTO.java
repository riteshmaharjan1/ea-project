package com.miu.libraryapplication.service.dto;

import java.util.ArrayList;
import java.util.List;

public class BookDTO {
    private String isbn;
    private String title;
    private List<AuthorDTO> authorName = new ArrayList<>();
    private List<BookCopyDTO> bookCopies = new ArrayList<>();
}
