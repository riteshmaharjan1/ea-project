package com.miu.libraryapplication.service.adapter;

import com.miu.libraryapplication.domain.Customer;
import com.miu.libraryapplication.service.dto.CustomerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomerAdapter {
    public static ModelMapper mapper;
    public static Collection<CustomerDTO> getAllCustomerDTOList(Collection<Customer> allBooks) {
        return allBooks.stream().map(b -> customerToCustomerDTO(b)).collect(Collectors.toList());
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }

    public static CustomerDTO customerToCustomerDTO(Customer customer) {
        return mapper.map(customer, CustomerDTO.class);
    }

    public static Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        return mapper.map(customerDTO, Customer.class);
    }
}
