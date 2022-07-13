package com.miu.libraryapplication.service.adapter;

import com.miu.libraryapplication.domain.BookReserve;
import com.miu.libraryapplication.service.dto.BookReserveDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class BookReserveAdapter {
    public static ModelMapper mapper;

    public static Collection<BookReserveDTO> getAllBookReserveDTOList(Collection<BookReserve> allBookReserves) {
        return allBookReserves.stream().map(b -> bookReserveToBookReserveDTO(b)).collect(Collectors.toList());
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }

    public static BookReserveDTO bookReserveToBookReserveDTO(BookReserve bookReserve) {
        return mapper.map(bookReserve, BookReserveDTO.class);
    }

    public static BookReserve bookReserveDTOToBookReserve(BookReserveDTO BookReserveDTO) {
        return mapper.map(BookReserveDTO, BookReserve.class);
    }
}
