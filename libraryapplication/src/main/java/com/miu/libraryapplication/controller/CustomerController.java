package com.miu.libraryapplication.controller;

import com.miu.libraryapplication.service.CustomerService;
import com.miu.libraryapplication.service.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>("Deleted Succesfully", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomer() {
        Collection<CustomerDTO> customerDTOS = customerService.getAllCustomer();
        return new ResponseEntity<>(customerDTOS, HttpStatus.OK);
    }
}
