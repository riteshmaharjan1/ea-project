package com.miu.libraryapplication.service.dto;

import java.util.Collection;

public class BookCheckoutDTOs {

    Collection<BookCheckoutDTO> bookCheckoutDTOS;

    public BookCheckoutDTOs() {
    }

    public BookCheckoutDTOs(Collection<BookCheckoutDTO> bookCheckoutDTOS) {
        this.bookCheckoutDTOS = bookCheckoutDTOS;
    }

    public Collection<BookCheckoutDTO> getAccountDTOS() {
        return bookCheckoutDTOS;
    }

    public void setAccountDTOS(Collection<BookCheckoutDTO> bookCheckoutDTOS) {
        this.bookCheckoutDTOS = bookCheckoutDTOS;
    }
}
