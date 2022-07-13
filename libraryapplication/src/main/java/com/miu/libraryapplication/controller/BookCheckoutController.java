package com.miu.libraryapplication.controller;

import com.miu.libraryapplication.service.BookCheckoutService;
import com.miu.libraryapplication.service.CustomerService;
import com.miu.libraryapplication.service.dto.BookCheckoutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookCheckoutController {

    @Autowired
    BookCheckoutService bookCheckoutService;

    @PostMapping("/checkout")
    private ResponseEntity<?> checkoutBook(@RequestBody BookCheckoutDTO bookCheckoutDTO) {
        bookCheckoutService.checkoutBook(bookCheckoutDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
