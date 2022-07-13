package com.book.bookcatalog.integration.activemq;

import com.book.bookcatalog.service.BookService;
import com.book.bookcatalog.service.dto.NotifyCheckoutBookDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CheckoutListener {
    @Autowired
    BookService bookService;

    @JmsListener(destination = "CHECKOUT_BOOK")
    public void receiveMsgFromActiveMQ(String checkoutMsg) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            NotifyCheckoutBookDTO bookDTO = objectMapper.readValue(checkoutMsg, NotifyCheckoutBookDTO.class);
            bookService.updateCheckedOutBook(bookDTO.getScanCode());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
