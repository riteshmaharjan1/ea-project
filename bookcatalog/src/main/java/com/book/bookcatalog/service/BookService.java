package com.book.bookcatalog.service;

import com.book.bookcatalog.domain.Book;
import com.book.bookcatalog.domain.BookCopy;
import com.book.bookcatalog.repository.IBookRepository;
import com.book.bookcatalog.service.adapter.BookAdapter;
import com.book.bookcatalog.service.dto.BookDTO;
import com.book.bookcatalog.service.dto.BookDTOs;
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

    public BookDTOs getAllBook() {
        return bookDTOObject(bookRepository.findAll());
    }

    public void deleteBook(String isbn) {
        Book book = bookRepository.findBookByIsbn(isbn);
        bookRepository.delete(book);
    }

    public BookDTOs searchBookByTitle(String title) {
        return bookDTOObject(bookRepository.findBookByTitle(title));
    }

    public BookDTOs searchBookByAuthorName(String name) {
        return bookDTOObject(bookRepository.findBookByAuthorName(name));
    }

    public BookDTOs searchBookByIsbn(String isbn) {
        return bookDTOObject(bookRepository.findBookListByIsbn(isbn));
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

    BookDTOs bookDTOObject(Collection<Book> bookList) {
        Collection<BookDTO> allBookDTO = BookAdapter.getAllBookDTOList(bookList);
        BookDTOs bookDTOs = new BookDTOs();
        bookDTOs.setBookDTOS(allBookDTO);
        return bookDTOs;
    }
}
