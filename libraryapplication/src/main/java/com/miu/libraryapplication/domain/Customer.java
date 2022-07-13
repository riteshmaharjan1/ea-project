package com.miu.libraryapplication.domain;

import com.miu.libraryapplication.domain.value.CustomMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.awt.print.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@SequenceGenerator(name = "customerSeq", sequenceName = "customer_seq")
@Data
@NoArgsConstructor

public class Customer {
    @Id
    @GeneratedValue(generator = "customerSeq")
    private Long customerNumber;
    private String name;
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<BookCheckout> bookCheckouts = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<PaymentEntry> paymentEntries = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<BookReserve> bookReserves = new ArrayList<>();


    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void checkoutBook(String scanCode, LocalDate dueDate, long maxNumberOfBooks) {
        if (ableToCheckout(maxNumberOfBooks)) {
            BookCheckout bookCheckout = new BookCheckout();
            bookCheckout.setCheckoutDate(LocalDate.now());
            bookCheckout.setDueDate(dueDate);
            bookCheckout.setScanCode(scanCode);
            bookCheckouts.add(bookCheckout);
        }
    }

    public void returnBook(String scanCode) {
        Optional<BookCheckout> bookCheckout = bookCheckouts.stream().filter(ce -> ce.getScanCode().equals(scanCode)).findFirst();
        if (bookCheckout.isPresent()) {
            bookCheckout.get().setCheckinDate(LocalDate.now());
        }
    }

    public void reserveBook(String scanCode) {
        BookReserve bookReserve = new BookReserve();
        bookReserve.setReserveDate(LocalDate.now());
        bookReserve.setScanCode(scanCode);
        List<BookReserve> reservationEntriesWithSameBook = bookReserves.stream().filter(re -> re.getScanCode().equals(scanCode)).collect(Collectors.toList());
        if (reservationEntriesWithSameBook.size() == 0) {
            bookReserves.add(bookReserve);
        }
    }

    public void payFee(double amount){
        PaymentEntry paymentEntry = new PaymentEntry(LocalDate.now(), amount);
        paymentEntries.add(paymentEntry);
    }

    public boolean ableToCheckout(long maxBookThatCanBeCheckout) {
        List<BookCheckout> bookCheckoutList = bookCheckouts.stream().filter(ce -> ce.getCheckinDate() != null).collect(Collectors.toList());
        if (bookCheckoutList.size() >= maxBookThatCanBeCheckout) {
            return false;
        } else {
            return true;
        }
    }

}
