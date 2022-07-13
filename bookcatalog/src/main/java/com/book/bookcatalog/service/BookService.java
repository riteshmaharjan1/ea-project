package com.book.bookcatalog.service;

import com.book.bookcatalog.domain.Book;
import com.book.bookcatalog.domain.BookCopy;
import com.book.bookcatalog.repository.IBookRepository;
import com.book.bookcatalog.service.adapter.BookAdapter;
import com.book.bookcatalog.service.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class BookService {
    @Autowired
    IBookRepository bookRepository;

    public BookDTO addBook(BookDTO bookDTO) {
        Book book = BookAdapter.bookDTOToBook(bookDTO);
        bookRepository.save(book);
        return BookAdapter.bookToBookDTO(book);
    }

    public BookDTO updateBook(BookDTO bookDTO) {
        Book book = BookAdapter.bookDTOToBook(bookDTO);
        bookRepository.save(book);
        return BookAdapter.bookToBookDTO(book);
    }

    public BookDTO getBook(String isbn) {
        Book book = bookRepository.findBookByIsbn(isbn);
        return BookAdapter.bookToBookDTO(book);
    }

    public Collection<BookDTO> getAllBook() {
        List<Book> bookList = bookRepository.findAll();
        return BookAdapter.getAllBookDTOList(bookList);
    }

    public void deleteBook(String isbn) {
        Book book = bookRepository.findBookByIsbn(isbn);
        bookRepository.delete(book);
    }

    public Collection<BookDTO> searchBookByTitle(String title) {
        List<Book> bookList = bookRepository.findBookByTitle(title);
        return BookAdapter.getAllBookDTOList(bookList);
    }

    public Collection<BookDTO> searchBookByAuthorName(String name) {
        List<Book> bookList = bookRepository.findBookByAuthorName(name);
        return BookAdapter.getAllBookDTOList(bookList);
    }

    public Collection<BookDTO> searchBookByIsbn(String isbn) {
        List<Book> bookList = bookRepository.findBookListByIsbn(isbn);
        return BookAdapter.getAllBookDTOList(bookList);
    }

    public BookDTO searchBookByScanCode(String isbn) {
        Book bookList = bookRepository.findBookByScanCode(isbn);
        return BookAdapter.bookToBookDTO(bookList);
    }

    public Collection<BookDTO> searchBookByAvailability(boolean isAvailable) {
        List<Book> bookList = bookRepository.findBookByAvailable(isAvailable);
        return BookAdapter.getAllBookDTOList(bookList);
    }

    public BookDTO updateCheckedOutBook(String scanCode) {
        Book book = bookRepository.findBookByScanCode(scanCode);
        book.updateCheckedOutBook(scanCode);
        bookRepository.save(book);
        return BookAdapter.bookToBookDTO(book);
    }
}
