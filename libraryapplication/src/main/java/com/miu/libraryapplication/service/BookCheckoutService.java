package com.miu.libraryapplication.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.miu.libraryapplication.domain.BookCheckout;
import com.miu.libraryapplication.domain.Customer;
import com.miu.libraryapplication.domain.value.CustomMsg;
import com.miu.libraryapplication.integration.activemq.NotifyCheckout;
import com.miu.libraryapplication.integration.mailing.IMailSender;
import com.miu.libraryapplication.properties.LibraryProperties;
import com.miu.libraryapplication.repository.IBookCheckoutRepository;
import com.miu.libraryapplication.repository.IBookReserveRepository;
import com.miu.libraryapplication.repository.ICustomerRepository;
import com.miu.libraryapplication.service.adapter.BookCheckoutAdapter;
import com.miu.libraryapplication.service.dto.BookCheckoutDTO;
import com.miu.libraryapplication.service.dto.BookDTO;
import com.miu.libraryapplication.service.dto.NotifyCheckoutBookDTO;
import com.miu.libraryapplication.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookCheckoutService {
//    RestTemplate restTemplate = new RestTemplate();
//    @Autowired
//    LibraryProperties libraryProperties;
//    @Autowired
//    private NotifyCheckout notifyCheckout;
//    @Autowired
//    IBookCheckoutRepository bookCheckoutRepository;
//    @Autowired
//    IBookReserveRepository bookReserveRepository;
//
//    @Autowired
//    ICustomerRepository customerRepository;
//    @Autowired
//    IMailSender mailSender;

//    public Object checkoutBook(BookCheckoutDTO bookCheckoutDTO) throws JsonProcessingException {
//        LocalDate dueDate = LocalDate.now().plusDays(libraryProperties.getMaxWeeksForCheckout() * 7);
//        bookCheckoutDTO.setDueDate(dueDate);
//        BookDTO bookDTO = restTemplate.getForObject(libraryProperties.getBookCatalogAPI() + "/search?searchBy=scanCode&keyword=" + bookCheckoutDTO.getScanCode(), BookDTO.class);
//        if (bookDTO != null && bookDTO.getBookCopies().stream().anyMatch(
//                (x) -> x.getScanCode().equals(bookCheckoutDTO.getScanCode()) && x.getIsAvailable())) {
//            Optional<Customer> customer = customerRepository.findByCustomerNumber(bookCheckoutDTO.getCustomerNumber());
//            if (customer.isPresent()) {
//                customer.get().checkoutBook(bookCheckoutDTO.getScanCode(), dueDate, libraryProperties.getMaxWeeksForCheckout());
//                notifyCheckout.pushCheckoutMessage(new NotifyCheckoutBookDTO(bookCheckoutDTO.getScanCode()));
//                return customerRepository.save(customer.get());
//            } else {
//                return new CustomMsg("Customer not found");
//            }
//        } else {
//            return new CustomMsg("Requested Book is either not available or is occupied. Please reserve the book.");
//        }
//    }
//
//    public Object checkinBook(BookCheckoutDTO bookCheckoutDTO) {
//        Optional<Customer> customer = customerRepository.findByCustomerNumber(bookCheckoutDTO.getCustomerNumber());
//        if (customer.isPresent()) {
//            List<Long> customerList = bookReserveRepository.findByScanCodeAndClosed(bookCheckoutDTO.getScanCode());
//            for (long customerId : customerList) {
//                Optional<Customer> cust = customerRepository.findById(customerId);
//                if (cust.isPresent()) {
//                    mailSender.sendMail(cust.get().getEmail(), "Book " + bookCheckoutDTO.getScanCode() + " is available", "Book available");
//                }
//            }
//            customer.get().returnBook(bookCheckoutDTO.getScanCode());
//            customerRepository.save(customer.get());
//        }
//        return new CustomMsg("Customer Not Found");
//    }
}
