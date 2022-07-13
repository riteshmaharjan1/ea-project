package com.book.bookcatalog.domain;

import lombok.*;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Book {
    @Id
    private String isbn;
    private String title;
    private List<Author> authorName = new ArrayList<>();
    private List<BookCopy> bookCopies = new ArrayList<>();


    public void updateCheckedOutBook(String scanCode){
        BookCopy bookCopy = bookCopies.stream().filter(book -> book.getScanCode().equals(scanCode)).findFirst().get();
        bookCopy.setIsAvailable(!bookCopy.getIsAvailable());
    }
}
