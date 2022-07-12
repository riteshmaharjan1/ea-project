package com.miu.libraryapplication.repository;

import com.miu.libraryapplication.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByCustomerNumber(long customerNumber);
}
