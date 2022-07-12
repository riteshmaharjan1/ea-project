package com.miu.libraryapplication.service;

import com.miu.libraryapplication.domain.Customer;
import com.miu.libraryapplication.repository.ICustomerRepository;
import com.miu.libraryapplication.service.adapter.CustomerAdapter;
import com.miu.libraryapplication.service.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    ICustomerRepository customerRepository;

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerAdapter.customerDTOToCustomer(customerDTO);
        customerRepository.save(customer);
        return CustomerAdapter.customerToCustomerDTO(customer);
    }

    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerAdapter.customerDTOToCustomer(customerDTO);
        customerRepository.save(customer);
        return CustomerAdapter.customerToCustomerDTO(customer);
    }

    public void deleteCustomer(long customerNumber) {
        Customer customer = customerRepository.findCustomerByCustomerNumber(customerNumber);
        customerRepository.delete(customer);
    }

    public CustomerDTO getCustomer(long customerNumber) {
        Customer customer = customerRepository.findCustomerByCustomerNumber(customerNumber);
        return CustomerAdapter.customerToCustomerDTO(customer);
    }

    public Collection<CustomerDTO> getAllCustomer() {
        List<Customer> customerList = customerRepository.findAll();
        return CustomerAdapter.getAllCustomerDTOList(customerList);
    }
}
