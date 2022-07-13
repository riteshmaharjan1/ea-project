package com.miu.libraryapplication.controller;

import com.miu.libraryapplication.domain.value.CustomMsg;
import com.miu.libraryapplication.service.BookReserveService;
import com.miu.libraryapplication.service.dto.BookReserveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class BookReserveController {
//    @Autowired
//    BookReserveService bookReserveService;
//
//    @PostMapping("/reserveBook")
//    public ResponseEntity<?> reserveBook(@RequestBody BookReserveDTO bookReserveDTO) {
//        bookReserveDTO.setReserveDate(new Date());
//        bookReserveService.reserveBook(bookReserveDTO);
//        return new ResponseEntity<>(new CustomMsg("Reserved Successfully"), HttpStatus.OK);
//    }

}
