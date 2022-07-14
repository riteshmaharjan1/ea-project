package com.miu.bookcategoryclient.bookClient;

import com.miu.bookcategoryclient.bookClient.book.AuthorDTO;
import com.miu.bookcategoryclient.bookClient.book.BookCopyDTO;
import com.miu.bookcategoryclient.bookClient.book.BookDTO;
import com.miu.bookcategoryclient.bookClient.book.BookDTOs;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BookClientApplication implements CommandLineRunner {
    RestTemplate restTemplate = new RestTemplate();
    private String serverUrl = "http://localhost:8080/books";

    public static void main(String[] args) {
        SpringApplication.run(BookClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        BookCopyDTO bookCopyDto1 = new BookCopyDTO("SCN-9001", true);
        BookCopyDTO bookCopyDto2 = new BookCopyDTO("SCN-9002", true);
        BookCopyDTO bookCopyDto3 = new BookCopyDTO("SCN-9003", true);
        BookCopyDTO bookCopyDto4 = new BookCopyDTO("SCN-9004", true);
        BookCopyDTO bookCopyDto5 = new BookCopyDTO("SCN-9005", true);
        BookCopyDTO bookCopyDto6 = new BookCopyDTO("SCN-9006", true);

        List<BookCopyDTO> bookCopyDtos1 = new ArrayList<>();
        bookCopyDtos1.add(bookCopyDto1);
        bookCopyDtos1.add(bookCopyDto2);
        bookCopyDtos1.add(bookCopyDto3);

        List<BookCopyDTO> bookCopyDtos2 = new ArrayList<>();
        bookCopyDtos2.add(bookCopyDto4);
        bookCopyDtos2.add(bookCopyDto5);
        bookCopyDtos2.add(bookCopyDto6);

        List<BookCopyDTO> bookCopyDtos3 = new ArrayList<>();
        bookCopyDtos3.add(bookCopyDto3);

        AuthorDTO authorDTO1 = new AuthorDTO("Rene De Jong");
        AuthorDTO authorDTO2 = new AuthorDTO("Saimak Tavakoli");

        List<AuthorDTO> authorDTOS1 = new ArrayList<>();
        authorDTOS1.add(authorDTO1);

        List<AuthorDTO> authorDTOS2 = new ArrayList<>();
        authorDTOS2.add(authorDTO2);


        BookDTO bookDto1 = new BookDTO("ISBN-9001", "Enterprise Architecture", authorDTOS1, bookCopyDtos1);

        BookDTO bookDto2 = new BookDTO("ISBN-9002", "Software Architecture", authorDTOS2, bookCopyDtos2);

        BookDTO bookDto3 = new BookDTO("ISBN-9003", "Web Application Architecture", authorDTOS2, bookCopyDtos3);
        System.out.println("\n");
        System.out.println("\nAdding Two Books");
        System.out.println("***********************************************************************");
        restTemplate.postForLocation(serverUrl, bookDto1);
        restTemplate.postForLocation(serverUrl, bookDto2);
        restTemplate.postForLocation(serverUrl, bookDto3);

        // Get book by isbn
        System.out.println("\n");
        System.out.println("\nGet book by ISBN");
        System.out.println("***********************************************************************");
        BookDTO book1 = restTemplate.getForObject(serverUrl + "/{isbn}", BookDTO.class, bookDto1.getIsbn());
        BookDTO book2 = restTemplate.getForObject(serverUrl + "/{isbn}", BookDTO.class, bookDto2.getIsbn());
        System.out.println(book1);
        System.out.println(book2);

        // update book by isbn
        book1.setTitle("Enterprise Architecture Revised");
        restTemplate.put(serverUrl, book1);
        System.out.println("\n");
        System.out.println("\nUpdate book by ISBN");
        System.out.println("***********************************************************************");
        System.out.println(book1);


        // DELETE book by isbn
        System.out.println("\n");
        System.out.println("\nDELETE Book by ISBN");
        System.out.println("***********************************************************************");
        restTemplate.delete(serverUrl + "/{isbn}", bookDto3.getIsbn());

        //GET ALL BOOK
        System.out.println("\n");
        System.out.println("\nGET ALL BOOK");
        System.out.println("***********************************************************************");
        BookDTOs bookList = restTemplate.getForObject(serverUrl + "/all", BookDTOs.class);
        System.out.println(bookList);

        //Search book by ISBN
        System.out.println("\nSearch Book by ISBN");
        System.out.println("***********************************************************************");
        BookDTOs bookByISBN = restTemplate.getForObject(serverUrl + "?searchBy=isbn&keyword=ISBN-9001", BookDTOs.class);
        System.out.println(bookByISBN);
    }
}
