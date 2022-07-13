package com.miu.libraryapplication.repository;

import com.miu.libraryapplication.domain.BookCheckout;
import com.miu.libraryapplication.service.dto.BookCheckoutDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IBookCheckoutRepository extends JpaRepository<BookCheckout, Long> {
    @Query("select b from BookCheckout b where b.customer.customerNumber =:customerNumber")
    List<BookCheckout> getBookCheckout(@Param("customerNumber") long customerNumber);
}
