package com.miu.libraryapplication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.miu.libraryapplication.domain.value.CustomMsg;
import com.miu.libraryapplication.service.BookCheckoutService;
import com.miu.libraryapplication.service.BookReserveService;
import com.miu.libraryapplication.service.CustomerService;
import com.miu.libraryapplication.service.dto.BookCheckoutDTO;
import com.miu.libraryapplication.service.dto.BookReserveDTO;
import com.miu.libraryapplication.service.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    BookCheckoutService bookCheckoutService;

    @Autowired
    BookReserveService bookReserveService;

    @GetMapping("/{customerNumber}")
    public ResponseEntity<?> getCustomer(@PathVariable long customerNumber) {
        CustomerDTO customerDTO = customerService.getCustomer(customerNumber);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO customerDTOs = customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(customerDTOs, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.updateCustomer(customerDTO), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteCustomer(@PathVariable long customerNumber) {
        customerService.deleteCustomer(customerNumber);
        return new ResponseEntity<>("Deleted Succesfully", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomer() {
        Collection<CustomerDTO> customerDTOS = customerService.getAllCustomer();
        return new ResponseEntity<>(customerDTOS, HttpStatus.OK);
    }

    @PostMapping("/checkout")
    private ResponseEntity<?> checkoutBook(@RequestBody BookCheckoutDTO bookCheckoutDTO) throws JsonProcessingException {
        customerService.checkoutBook(bookCheckoutDTO);
        return new ResponseEntity<>("Checkout Successfully", HttpStatus.OK);
    }

    @PostMapping("/checkin")
    private ResponseEntity<?> checkinBook(@RequestBody BookCheckoutDTO bookCheckoutDTO) {
        bookCheckoutDTO.setCheckinDate(LocalDate.now());
        customerService.checkinBook(bookCheckoutDTO);
        return new ResponseEntity<>(new CustomMsg("Checkin Successfully"), HttpStatus.OK);
    }

    @PostMapping("/reserveBook")
    public ResponseEntity<?> reserveBook(@RequestBody BookReserveDTO bookReserveDTO) {
        customerService.reserveBook(bookReserveDTO);
        return new ResponseEntity<>(new CustomMsg("Reserved Successfully"), HttpStatus.OK);
    }
}
