package com.miu.libraryapplication.service;

import com.miu.libraryapplication.domain.BookCheckout;
import com.miu.libraryapplication.domain.value.CustomMsg;
import com.miu.libraryapplication.properties.LibraryProperties;
import com.miu.libraryapplication.repository.IBookCheckoutRepository;
import com.miu.libraryapplication.service.adapter.BookCheckoutAdapter;
import com.miu.libraryapplication.service.dto.BookCheckoutDTO;
import com.miu.libraryapplication.service.dto.BookDTO;
import com.miu.libraryapplication.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class BookCheckoutService {
    RestTemplate restTemplate = new RestTemplate();
    @Autowired
    LibraryProperties libraryProperties;
    @Autowired
    IBookCheckoutRepository bookCheckoutRepository;

    public Object checkoutBook(BookCheckoutDTO bookCheckoutDTO) {
        BookCheckout bookCheckout = BookCheckoutAdapter.bookCheckoutDTOToBookCheckout(bookCheckoutDTO);
        BookDTO bookDTO = restTemplate.getForObject(libraryProperties.getBookCatalogAPI() + "/search?searchBy=scanCode&keyword=" + bookCheckoutDTO.getScanCode(), BookDTO.class);
        if (bookDTO != null && bookDTO.getBookCopies().stream().anyMatch(
                (x) -> x.getScanCode().equals(bookCheckoutDTO.getScanCode()) && x.getIsAvailable())) {
            if (bookCheckoutRepository.getBookCheckout(bookCheckoutDTO.getCustomerNumber()).stream().count() < libraryProperties.getMaxNumberOfBooks()) {
                return bookCheckoutRepository.save(bookCheckout);
            } else {
                return new CustomMsg("Maximum number of checkout reached for customer: " + bookCheckoutDTO.getCustomerNumber());
            }
        } else {
            return new CustomMsg("Requested Book is either not available or is occupied. Please reserve the book.");
        }
    }

    public Object checkinBook(BookCheckoutDTO bookCheckoutDTO) {
//        BookCheckout bookCheckout = BookCheckoutAdapter.bookCheckoutDTOToBookCheckout(bookCheckoutDTO);
        BookCheckout bookCheckout = bookCheckoutRepository.getBookCheckoutInfo(bookCheckoutDTO.getCustomerNumber(), bookCheckoutDTO.getScanCode());
        if (bookCheckout != null) {
            if (libraryProperties.getMaxWeeksForCheckout() * 7
                    > DateUtils.findDaysDifference(bookCheckout.getCheckoutDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now())) {
                bookCheckout.setCheckinDate(new Date());
                return bookCheckoutRepository.save(bookCheckout);
            } else {
                return new CustomMsg("Book max checkout period reached. Please clear your payment first.");
            }
        } else {
            return new CustomMsg("Book doesnt exits in librayry system scancode: " + bookCheckoutDTO.getScanCode());
        }
    }
}
