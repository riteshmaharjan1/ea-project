package com.book.bookcatalog.repository;

import com.book.bookcatalog.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IBookRepository extends MongoRepository<Book, String> {
    Book findBookByIsbn(String isbn);

    List<Book> findBookByTitle(String title);

    @Query("{ 'authorName': { $elemMatch: { 'name' : :#{#name} } }}")
    List<Book> findBookByAuthorName(String name);

    @Query("{isbn : :#{#isbn}}")
    List<Book> findBookListByIsbn(String isbn);

    @Query("{ 'bookCopies': { $elemMatch: { 'scanCode' : :#{#scanCode} } }}")
    Book findBookByScanCode(String scanCode);

    @Query("{ 'bookCopies': { $elemMatch: { 'isAvailable' : :#{#isAvailable} } }}")
    List<Book> findBookByAvailable(boolean isAvailable);
}
