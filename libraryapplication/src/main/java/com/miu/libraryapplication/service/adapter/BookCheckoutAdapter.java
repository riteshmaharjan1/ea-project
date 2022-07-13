package com.miu.libraryapplication.service.adapter;

import com.miu.libraryapplication.domain.BookCheckout;
import com.miu.libraryapplication.domain.Customer;
import com.miu.libraryapplication.service.dto.BookCheckoutDTO;
import com.miu.libraryapplication.service.dto.CustomerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class BookCheckoutAdapter {
    public static ModelMapper mapper;

    public static Collection<BookCheckoutDTO> getAllBookCheckoutDTOList(Collection<BookCheckout> allBookCheckouts) {
        return allBookCheckouts.stream().map(b -> bookCheckoutToBookCheckoutDTO(b)).collect(Collectors.toList());
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }

    public static BookCheckoutDTO bookCheckoutToBookCheckoutDTO(BookCheckout bookCheckout) {
        return mapper.map(bookCheckout, BookCheckoutDTO.class);
    }

    public static BookCheckout bookCheckoutDTOToBookCheckout(BookCheckoutDTO BookCheckoutDTO) {
        return mapper.map(BookCheckoutDTO, BookCheckout.class);
    }
}