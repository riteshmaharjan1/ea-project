package com.book.bookcatalog.service;

import com.book.bookcatalog.domain.Book;
import com.book.bookcatalog.repository.IBookRepository;
import com.book.bookcatalog.service.adapter.BookAdapter;
import com.book.bookcatalog.service.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    IBookRepository bookRepository;

    public BookDTO addBook(BookDTO bookDTO) {
        Book book = BookAdapter.bookDTOToBook(bookDTO);
        bookRepository.save(book);
        return BookAdapter.bookToBookDTO(book);
    }
}
