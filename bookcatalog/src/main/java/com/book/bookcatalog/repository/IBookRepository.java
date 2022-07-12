package com.book.bookcatalog.repository;

import com.book.bookcatalog.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IBookRepository extends MongoRepository<Book, Long>{
}
