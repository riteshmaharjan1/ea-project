package com.miu.libraryapplication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.miu.libraryapplication.domain.value.CustomMsg;
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

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

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
        return new ResponseEntity<>(new CustomMsg("Deleted Succesfully"), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomer() {
        Collection<CustomerDTO> customerDTOS = customerService.getAllCustomer();
        return new ResponseEntity<>(customerDTOS, HttpStatus.OK);
    }

    @PostMapping("/checkout")
    private ResponseEntity<?> checkoutBook(@RequestBody BookCheckoutDTO bookCheckoutDTO) throws JsonProcessingException {
        return new ResponseEntity<>(customerService.checkoutBook(bookCheckoutDTO), HttpStatus.OK);
    }

    @PostMapping("/checkin")
    private ResponseEntity<?> checkinBook(@RequestBody BookCheckoutDTO bookCheckoutDTO) throws JsonProcessingException {
        bookCheckoutDTO.setCheckinDate(LocalDate.now());
        return new ResponseEntity<>(customerService.checkinBook(bookCheckoutDTO), HttpStatus.OK);
    }

    @PostMapping("/reserveBook")
    public ResponseEntity<?> reserveBook(@RequestBody BookReserveDTO bookReserveDTO) {
        return new ResponseEntity<>( customerService.reserveBook(bookReserveDTO), HttpStatus.OK);
    }

    @PostMapping("/payfee")
    public ResponseEntity<?> payFee(@RequestParam long customerNumber, @RequestParam double amount) {
        return new ResponseEntity<>( customerService.payFee(customerNumber, amount), HttpStatus.OK);
    }

    @GetMapping("/reports/getOutstandingAmountPerCustomer/{customerNumber}")
    public ResponseEntity<?> getOutstandingAmountPerCustomer(@PathVariable String customerNumber) {
        return new ResponseEntity<>(customerService.getOutstandingFeePerCustomer(customerNumber), HttpStatus.OK);
    }

    @GetMapping("/reports/getAllBorrowedAndLateReturnedBook")
    public ResponseEntity<?> getAllBorrowedAndLateReturnedBook() {
        return new ResponseEntity<>(customerService.getAllBorrowedAndLateReturnedBook(), HttpStatus.OK);
    }
}
