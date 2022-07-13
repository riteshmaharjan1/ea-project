package com.miu.libraryapplication.repository;

import com.miu.libraryapplication.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByCustomerNumber(long customerNumber);

    Optional<Customer> findByCustomerNumber(long customerNumber);

    @Query(value = " select  c.customer_number, \n" +
            "    max(c.name),\n" +
            "    (sum(current_date - (ce.due_date)) * (0.5)) as outstanding_fee \n" +
            "    from customer c\n" +
            "    inner join book_checkout ce on c.customer_number = ce.customer_id\n" +
            "    where ce.checkin_date != null\n" +
            "    and ce.due_date < current_date\n" +
            "    and c.customer_number = 1\n" +
            "    group by c.customer_number", nativeQuery = true)
    List<Object[]> getOutstandingAmountPerCustomer(@Param("customerNumber") String customerNumber, @Param("rate") double rate);

    @Query(value = "select\n" +
            "    bc.scan_code,\n" +
            "    c.name,\n" +
            "    bc.checkout_date,\n" +
            "    bc.checkin_date,\n" +
            "    bc.due_date,\n" +
            "    case when(bc.checkin_date is null) \n" +
            "        then 'BORROWED' \n" +
            "        else 'LATE RETURNED' \n" +
            "        END as status\n" +
            "    from book_checkout bc\n" +
            "    inner join customer c on c.customer_number = bc.customer_id\n" +
            "    where bc.checkin_date is null \n" +
            "    OR bc.checkin_date > bc.due_date", nativeQuery = true)
    List<Object[]> getAllBorrowedAndLateReturnedBook();

    @Query(value = "  select\n" +
            "    c.customer_number,\n" +
            "    max(name),\n" +
            "    (sum(current_date - (ce.due_date)) * (0.5)) as outstanding_fee\n" +
            "    from customer c\n" +
            "    inner join book_checkout ce on c.customer_number = ce.customer_id\n" +
            "    where ce.checkin_date != null\n" +
            "    and ce.due_date < current_date\n" +
            "    group by c.customer_number", nativeQuery = true)
    List<Object[]> getOutstandingAmountForAllCustomer(@Param("rate") double rate);
}
