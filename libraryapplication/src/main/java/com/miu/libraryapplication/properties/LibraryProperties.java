package com.miu.libraryapplication.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "libraryapplication")
public class LibraryProperties {
    private long MaxNumberOfBooks;
    private long MaxWeeksForCheckout;
    private String BookCatalogAPI;
    private double LateFeePerDay;

    public long getMaxNumberOfBooks() {
        return MaxNumberOfBooks;
    }

    public void setMaxNumberOfBooks(long maxNumberOfBooks) {
        MaxNumberOfBooks = maxNumberOfBooks;
    }

    public long getMaxWeeksForCheckout() {
        return MaxWeeksForCheckout;
    }

    public void setMaxWeeksForCheckout(long maxWeeksForCheckout) {
        MaxWeeksForCheckout = maxWeeksForCheckout;
    }

    public String getBookCatalogAPI() {
        return BookCatalogAPI;
    }

    public void setBookCatalogAPI(String bookCatalogAPI) {
        BookCatalogAPI = bookCatalogAPI;
    }

    public double getLateFeePerDay() {
        return LateFeePerDay;
    }

    public void setLateFeePerDay(double lateFeePerDay) {
        LateFeePerDay = lateFeePerDay;
    }
}
