package com.book.bookcatalog.controller;

import com.book.bookcatalog.service.BookService;
import com.book.bookcatalog.service.dto.BookDTO;
import org.bson.codecs.AtomicBooleanCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping()
    public ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.addBook(bookDTO), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> updateBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.updateBook(bookDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
        return new ResponseEntity<>("Deleted Sucessfully", HttpStatus.OK);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<?> getBook(@PathVariable String isbn) {
        BookDTO bookDTO = bookService.getBook(isbn);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBook() {
        Collection<BookDTO> bookDTOS = bookService.getAllBook();
        return new ResponseEntity<>(bookDTOS, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getBookByTitle(@RequestParam String searchBy, @RequestParam String keyword) {
        Collection<BookDTO> bookDTOList = new ArrayList<>();
        if (searchBy.equals("title")) {
            bookDTOList = bookService.searchBookByTitle(keyword);
        } else if (searchBy.equals("authorName")) {
            bookDTOList = bookService.searchBookByAuthorName(keyword);
        } else if (searchBy.equals("isbn")) {
            bookDTOList = bookService.searchBookByIsbn(keyword);
        }
//        else if (searchBy.equals("scanCode")) {
//            bookDTOList = bookService.searchBookByScanCode(keyword);
//        }

        return new ResponseEntity<>(bookDTOList, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getBookByScanCode(@RequestParam String searchBy, @RequestParam String keyword) {
        BookDTO bookDTO = bookService.searchBookByScanCode(keyword);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }
}
