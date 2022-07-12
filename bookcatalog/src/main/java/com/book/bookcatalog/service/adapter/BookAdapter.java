package com.book.bookcatalog.service.adapter;

import com.book.bookcatalog.domain.Book;
import com.book.bookcatalog.service.dto.BookDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
@Service
public class BookAdapter {
    private static ModelMapper mapper;

    public static Collection<BookDTO> getAllBookDTOList(Collection<Book> allBooks) {
        return allBooks.stream().map(b -> bookToBookDTO(b)).collect(Collectors.toList());
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }

    public static BookDTO bookToBookDTO(Book book) {
        return mapper.map(book, BookDTO.class);
    }

    public static Book bookDTOToBook(BookDTO bookDTO) {
        return mapper.map(bookDTO, Book.class);
    }
}
