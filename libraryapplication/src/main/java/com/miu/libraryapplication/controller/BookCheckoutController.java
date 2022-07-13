package com.miu.libraryapplication.controller;

import com.miu.libraryapplication.domain.value.CustomMsg;
import com.miu.libraryapplication.service.BookCheckoutService;
import com.miu.libraryapplication.service.CustomerService;
import com.miu.libraryapplication.service.dto.BookCheckoutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class BookCheckoutController {

    @Autowired
    BookCheckoutService bookCheckoutService;

    @PostMapping("/checkout")
    private ResponseEntity<?> checkoutBook(@RequestBody BookCheckoutDTO bookCheckoutDTO) {
        bookCheckoutDTO.setCheckoutDate(new Date());
        bookCheckoutService.checkoutBook(bookCheckoutDTO);
        return new ResponseEntity<>(new CustomMsg("Checkout Successfully"), HttpStatus.OK);
    }

    @PostMapping("/checkin")
    private ResponseEntity<?> checkinBook(@RequestBody BookCheckoutDTO bookCheckoutDTO) {
        bookCheckoutDTO.setCheckinDate(new Date());
        bookCheckoutService.checkinBook(bookCheckoutDTO);
        return new ResponseEntity<>(new CustomMsg("Checkin Successfully"), HttpStatus.OK);
    }

}
