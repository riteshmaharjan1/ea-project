package com.miu.libraryapplication.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.miu.libraryapplication.domain.BookReserve;
import com.miu.libraryapplication.domain.Customer;
import com.miu.libraryapplication.domain.value.CustomMsg;
import com.miu.libraryapplication.integration.activemq.NotifyCheckout;
import com.miu.libraryapplication.integration.mailing.IMailSender;
import com.miu.libraryapplication.properties.LibraryProperties;
import com.miu.libraryapplication.repository.IBookCheckoutRepository;
import com.miu.libraryapplication.repository.IBookReserveRepository;
import com.miu.libraryapplication.repository.ICustomerRepository;
import com.miu.libraryapplication.service.adapter.BookReserveAdapter;
import com.miu.libraryapplication.service.adapter.CustomerAdapter;
import com.miu.libraryapplication.service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerService {

    @Autowired
    ICustomerRepository customerRepository;

    RestTemplate restTemplate = new RestTemplate();
    @Autowired
    LibraryProperties libraryProperties;
    @Autowired
    private NotifyCheckout notifyCheckout;
    @Autowired
    IBookCheckoutRepository bookCheckoutRepository;
    @Autowired
    IBookReserveRepository bookReserveRepository;
    @Autowired
    IMailSender mailSender;

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

    public Object checkoutBook(BookCheckoutDTO bookCheckoutDTO) throws JsonProcessingException {
        LocalDate dueDate = LocalDate.now().plusDays(libraryProperties.getMaxWeeksForCheckout() * 7);
        bookCheckoutDTO.setDueDate(dueDate);
        BookDTO bookDTO = restTemplate.getForObject(libraryProperties.getBookCatalogAPI() + "/search?searchBy=scanCode&keyword=" + bookCheckoutDTO.getScanCode(), BookDTO.class);
        if (bookDTO != null && bookDTO.getBookCopies().stream().anyMatch(
                (x) -> x.getScanCode().equals(bookCheckoutDTO.getScanCode()) && x.getIsAvailable())) {
            Optional<Customer> customer = customerRepository.findByCustomerNumber(bookCheckoutDTO.getCustomerNumber());
            if (customer.isPresent()) {
                customer.get().checkoutBook(bookCheckoutDTO.getScanCode(), dueDate, libraryProperties.getMaxWeeksForCheckout());
                notifyCheckout.pushCheckoutMessage(new NotifyCheckoutBookDTO(bookCheckoutDTO.getScanCode()));
                return customerRepository.save(customer.get());
            } else {
                return new CustomMsg("Customer not found");
            }
        } else {
            return new CustomMsg("Requested Book is either not available or is occupied. Please reserve the book.");
        }
    }

    public Object checkinBook(BookCheckoutDTO bookCheckoutDTO) throws JsonProcessingException {
        Optional<Customer> customer = customerRepository.findByCustomerNumber(bookCheckoutDTO.getCustomerNumber());
        if (customer.isPresent()) {
            List<Long> customerList = bookReserveRepository.findByScanCodeAndClosed(bookCheckoutDTO.getScanCode());
            for (long customerId : customerList) {
                Optional<Customer> cust = customerRepository.findById(customerId);
                if (cust.isPresent()) {
                    mailSender.sendMail(cust.get().getEmail(), "Book " + bookCheckoutDTO.getScanCode() + " is available", "Book available");
                }
            }
            customer.get().returnBook(bookCheckoutDTO.getScanCode());

            notifyCheckout.pushCheckoutMessage(new NotifyCheckoutBookDTO(bookCheckoutDTO.getScanCode()));
            return customerRepository.save(customer.get());
        } else {
            return new CustomMsg("Customer Not Found");
        }
    }

    public Object reserveBook(BookReserveDTO bookReserveDTO) {
        Optional<Customer> customer = customerRepository.findByCustomerNumber(bookReserveDTO.getCustomerNumber());
        if (customer.isPresent()) {
            customer.get().reserveBook(bookReserveDTO.getScanCode());
            return customerRepository.save(customer.get());
        } else {
            return new CustomMsg("Customer Not Found");
        }
    }

    public Object payFee(long customerNumber, double amount) {
        Optional<Customer> customer = customerRepository.findByCustomerNumber(customerNumber);
        if (customer.isPresent()) {
            customer.get().payFee(amount);
            return customerRepository.save(customer.get());
        } else {
            return new CustomMsg("Customer not found");
        }
    }

    public List<OutstandingAmountPerCustomerDTO> getOutstandingFeePerCustomer(String customerNumber) {
        List<Object[]> tempObject = customerRepository.getOutstandingAmountPerCustomer(customerNumber, libraryProperties.getLateFeePerDay());
        List<OutstandingAmountPerCustomerDTO> resultSet = new ArrayList<>();
        for (Object[] result : tempObject) {
            OutstandingAmountPerCustomerDTO dataSet = new OutstandingAmountPerCustomerDTO();
            dataSet.setCustomerNumber((String) result[0]);
            dataSet.setName((String) result[1]);
            dataSet.setOutstandingFee((Double) result[2]);
            resultSet.add(dataSet);
        }
        return resultSet;
    }

    public List<BorrowedAndLateReturnedBookDTO> getAllBorrowedAndLateReturnedBook() {
        List<Object[]> tempObject = customerRepository.getAllBorrowedAndLateReturnedBook();
        List<BorrowedAndLateReturnedBookDTO> resultSet = new ArrayList<>();
        for (Object[] result : tempObject) {
            BorrowedAndLateReturnedBookDTO dataSet = new BorrowedAndLateReturnedBookDTO();
            dataSet.setScanCode((String) result[1]);
            dataSet.setCustomerName((String) result[2]);
            dataSet.setBorrowedDate((Date) result[3]);
            dataSet.setReturnedDate((Date) result[4]);
            dataSet.setReturnedDate((Date) result[5]);
            dataSet.setStatus((String) result[6]);
            resultSet.add(dataSet);
        }
        return resultSet;
    }

    @Scheduled(cron = "0/20 * * * * *")
    public void printOutstandingAmountPerCustomer() {
        List<Object[]> tempObject = customerRepository.getOutstandingAmountForAllCustomer(libraryProperties.getLateFeePerDay());
        List<OutstandingAmountPerCustomerDTO> resultSet = new ArrayList<>();
        for (Object[] result : tempObject) {
            OutstandingAmountPerCustomerDTO dataSet = new OutstandingAmountPerCustomerDTO();
            dataSet.setCustomerNumber((String) result[0]);
            dataSet.setName((String) result[1]);
            dataSet.setOutstandingFee((Double) result[2]);
            resultSet.add(dataSet);
        }
        System.out.println("***** Printing Outstanding report at : " + LocalDateTime.now().toString() + "*****");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Customer Number   |                  Name               | Outstanding Fee");
        System.out.println("-------------------------------------------------------------------------");
        for (OutstandingAmountPerCustomerDTO data : resultSet) {
            System.out.println(data.getCustomerNumber() + "             |            " + data.getName() + "          |    " + String.valueOf(data.getOutstandingFee()));
        }
        System.out.println("-------------------------------------------------------------------------");

    }
}
