package com.miu.libraryapplication.domain;

import com.miu.libraryapplication.exception.CustomErrorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerTest {
    private Customer customer;

    @BeforeEach
    public void setUp() throws Exception {
        customer = new Customer("Ritesh Maharjan", "ritesh@gmail.com");
    }

    @Test
    public void whenReserveBookShouldCreateReserveEntry() {
        String bookScanCode = "SCN-9001";
        customer.reserveBook(bookScanCode);
        assertThat(customer.getBookReserves().get(0).getScanCode(), equalTo(bookScanCode));
    }

    @Test
    public void whenReserveMultipleBookShouldCreateMultipleReserveEntry() {
        String bookScanCode = "SCN-9001";
        customer.reserveBook(bookScanCode);
        bookScanCode = "SCN-9002";
        customer.reserveBook(bookScanCode);
        assertThat(customer.getBookReserves(), hasSize(2));
    }

    @Test
    public void whenCheckoutBookShouldCreateCheckoutEntry() {
        String scanCode = "SCN-9001";
        LocalDate dueDate = LocalDate.now().plusDays(21);
        int maxBookThatCanBeCheckout = 4;
        customer.checkoutBook(scanCode, dueDate, maxBookThatCanBeCheckout);
        assertThat(customer.getBookCheckouts().get(0).getScanCode(), equalTo(scanCode));
    }

    @Test
    public void whenCheckoutMultipleBookShouldCreateMultipleCheckoutEntry() {
        String scanCode = "SCN-9001";
        LocalDate dueDate = LocalDate.now().plusDays(21);
        int maxBookThatCanBeCheckout = 4;
        customer.checkoutBook(scanCode, dueDate, maxBookThatCanBeCheckout);
        scanCode = "SCN-9001";
        customer.checkoutBook(scanCode, dueDate, maxBookThatCanBeCheckout);
        assertThat(customer.getBookCheckouts(), hasSize(2));
    }

    @Test
    public void afterMaxCheckoutCustomerNotAllowFurtherCheckout() {
        String scanCode = "SCN-9001";
        LocalDate dueDate = LocalDate.now().plusDays(21);
        int maxBookThatCanBeCheckout = 4;
        customer.checkoutBook(scanCode, dueDate, maxBookThatCanBeCheckout);
        scanCode = "SCN-9002";
        customer.checkoutBook(scanCode, dueDate, maxBookThatCanBeCheckout);
        scanCode = "SCN-9003";
        customer.checkoutBook(scanCode, dueDate, maxBookThatCanBeCheckout);
        scanCode = "SCN-9004";
        customer.checkoutBook(scanCode, dueDate, maxBookThatCanBeCheckout);
        scanCode = "SCN-9005";

        String finalScanCode = scanCode;
        Throwable exception = assertThrows(CustomErrorException.class, () -> {
            customer.checkoutBook(finalScanCode, dueDate, maxBookThatCanBeCheckout);
        });
        assertTrue(exception.getMessage().contains("Customer already exceed maximum books for checking out"));
    }

    @Test
    public void throwExceptionIfTryToReturnInvalidBook() {
        String scanCode = "SCN-9001";
        Throwable exception = assertThrows(CustomErrorException.class, () -> {
            customer.returnBook(scanCode);
        });
        assertTrue(exception.getMessage().contains("Information provided is not valid"));
    }

    @Test
    public void whenReturnBookSetReturnBookScanCode(){
        String scanCode = "SCN-9001";
        LocalDate dueDate = LocalDate.now().plusDays(21);
        int maxBookThatCanBeCheckout = 4;
        customer.checkoutBook(scanCode,dueDate,maxBookThatCanBeCheckout);
        customer.returnBook(scanCode);
        assertThat(customer.getBookCheckouts().get(0).getScanCode(), equalTo("SCN-9001"));
    }

    @Test
    public void whenPayFeeCreatePaymentEntry(){
        double amount = 100;
        customer.payFee(amount);
        assertThat(customer.getPaymentEntries().get(0).getAmount(), closeTo(100, 0.01));
    }
}
