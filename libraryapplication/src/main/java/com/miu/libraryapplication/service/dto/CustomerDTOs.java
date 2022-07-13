package com.miu.libraryapplication.service.dto;

import java.util.Collection;

public class CustomerDTOs {
    Collection<CustomerDTO> customerDTOS;

    public CustomerDTOs() {
    }

    public Collection<CustomerDTO> getCustomerDTOS() {
        return customerDTOS;
    }

    public void setCustomerDTOS(Collection<CustomerDTO> customerDTOS) {
        this.customerDTOS = customerDTOS;
    }
}
