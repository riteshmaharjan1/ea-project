package com.miu.libraryapplication.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "libraryapplication")
@Data
public class LibraryProperties {
    private Long MaxNumberOfBooks;
    private Long MaxWeeksForCheckout;
    private String BookCatalogAPI;
    private Double LateFeePerDay;

}
