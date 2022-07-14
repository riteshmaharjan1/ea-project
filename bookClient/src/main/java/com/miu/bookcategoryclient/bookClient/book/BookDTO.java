package com.miu.bookcategoryclient.bookClient.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
