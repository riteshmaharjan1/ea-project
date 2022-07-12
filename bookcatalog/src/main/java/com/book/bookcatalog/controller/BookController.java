package com.book.bookcatalog.controller;

import com.book.bookcatalog.service.BookService;
import com.book.bookcatalog.service.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/books")
    public ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.addBook(bookDTO), HttpStatus.OK);
    }


}
