package com.miu.libraryapplication.repository;

import com.miu.libraryapplication.domain.BookReserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Book;
import java.util.List;

public interface IBookReserveRepository extends JpaRepository<BookReserve,Long> {
    @Query(value = "select customer_id from book_reserve br where br.scan_code = :scanCode", nativeQuery = true)
    List<Long> findByScanCodeAndClosed(@Param("scanCode") String scanCode);
}
