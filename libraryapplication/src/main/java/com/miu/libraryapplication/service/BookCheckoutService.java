package com.miu.libraryapplication.service;

import com.miu.libraryapplication.domain.BookCheckout;
import com.miu.libraryapplication.properties.LibraryProperties;
import com.miu.libraryapplication.repository.IBookCheckoutRepository;
import com.miu.libraryapplication.service.adapter.BookCheckoutAdapter;
import com.miu.libraryapplication.service.dto.BookCheckoutDTO;
import com.miu.libraryapplication.service.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BookCheckoutService {
    RestTemplate restTemplate = new RestTemplate();
    //    @Autowired
//    LibraryProperties libraryProperties;
    @Autowired
    IBookCheckoutRepository bookCheckoutRepository;

    //    private String bookCatalogUrl = libraryProperties.getBookCatalogAPI();
    private String bookCatalogUrl = "http://localhost:8080/books";

    public BookCheckoutDTO checkoutBook(BookCheckoutDTO bookCheckoutDTO) {
        BookCheckout bookCheckout = BookCheckoutAdapter.bookCheckoutDTOToBookCheckout(bookCheckoutDTO);
        BookDTO bookDTO = restTemplate.getForObject(bookCatalogUrl + "/search?searchBy=scanCode&keyword=" + bookCheckoutDTO.getScanCode(), BookDTO.class);
        if (bookDTO != null) {
            if (bookCheckoutRepository.getBookCheckout(bookCheckoutDTO.getCustomerNumber()).stream().count() < 3) {
                bookCheckoutRepository.save(bookCheckout);
            }
        }
        return null;
    }
}
