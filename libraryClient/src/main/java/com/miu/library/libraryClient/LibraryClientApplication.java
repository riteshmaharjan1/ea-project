package com.miu.library.libraryClient;

import com.miu.library.libraryClient.dto.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LibraryClientApplication implements CommandLineRunner {

    RestTemplate restTemplate = new RestTemplate();

    private String serverUrl = "http://localhost:8081/customers";

    public static void main(String[] args) {
        SpringApplication.run(LibraryClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Add Customer
        System.out.println("\n");
        System.out.println("\nCreate Customer");
        System.out.println("***********************************************************************");
        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setName("Ritesh Maharjan");
        customerDto.setEmail("ritesh@gmail.com");
        restTemplate.postForLocation(serverUrl, customerDto, CustomerDTO.class);
        System.out.println(customerDto);

        //Add Customer
        System.out.println("\n");
        System.out.println("\nCreate Customer");
        System.out.println("***********************************************************************");
        CustomerDTO customerDto1 = new CustomerDTO();
        customerDto1.setName("Raj Shrestha");
        customerDto1.setEmail("raj@gmail.com");
        restTemplate.postForLocation(serverUrl, customerDto1, CustomerDTO.class);
        System.out.println(customerDto1);

        //GET Customer
        System.out.println("\n");
        System.out.println("\nGet Customer");
        System.out.println("***********************************************************************");
        CustomerDTO cust = restTemplate.getForObject(serverUrl + "/{customerNumber}", CustomerDTO.class, 1);
        System.out.println("-------------------------Print Customer--------------------------");
        System.out.println(cust.toString());

        //Update Customer
        System.out.println("\n");
        System.out.println("\nUpdate Customer");
        System.out.println("***********************************************************************");
        cust.setName("RRitesh Maharjan");
        restTemplate.put(serverUrl, cust);

        //Print Updated Customer
        CustomerDTO updatedCustomer = restTemplate.getForObject(serverUrl + "/{customerNumber}", CustomerDTO.class, 1);
        System.out.println("-------------------------Print updated Customer--------------------------");
        System.out.println(updatedCustomer.toString());

        // reserve book
        BookReserveDTO reqForReservation = new BookReserveDTO();
        reqForReservation.setCustomerNumber(2);
        reqForReservation.setScanCode("SCN-9001");
        System.out.println("******************* reserve a book *******************");
        restTemplate.postForLocation(serverUrl + "/reserveBook", reqForReservation);


        BooKCheckoutDTO reqForCheckout = new BooKCheckoutDTO();


        // Checkout 1
        try {
            reqForCheckout.setCustomerNumber(1);
            reqForCheckout.setScanCode("SCN-9001");
            System.out.println("******************* checkout a book *******************");
            var response = restTemplate.postForLocation(serverUrl + "/checkout", reqForCheckout);
            System.out.println(response);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        // Checkout 2
        try {
            reqForCheckout.setCustomerNumber(1);
            reqForCheckout.setScanCode("SCN-9002");
            System.out.println("******************* checkout a book *******************");
            var response = restTemplate.postForLocation(serverUrl + "/checkout", reqForCheckout);
            System.out.println(response);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        // Checkout 3
        try {
            reqForCheckout.setCustomerNumber(1);
            reqForCheckout.setScanCode("SCN-9003");
            System.out.println("******************* checkout a book *******************");
            var response = restTemplate.postForLocation(serverUrl + "/checkout", reqForCheckout);
            System.out.println(response);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        // Checkout 4
        try {
            reqForCheckout.setCustomerNumber(1);
            reqForCheckout.setScanCode("SCN-9004");
            System.out.println("******************* checkout a book *******************");
            var response = restTemplate.postForLocation(serverUrl + "/checkout", reqForCheckout);
            System.out.println(response);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        // Checkout 5 (Should throw an error)
        try {
            reqForCheckout.setCustomerNumber(1);
            reqForCheckout.setScanCode("SCN-9005");
            System.out.println("******************* checkout a book *******************");
            var response = restTemplate.postForLocation(serverUrl + "/checkout", reqForCheckout);
            System.out.println(response);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        // return book
        try {
            reqForCheckout.setCustomerNumber(1);
            reqForCheckout.setScanCode("SCN-9001");
            System.out.println("******************* return a book *******************");
            var response = restTemplate.postForLocation(serverUrl + "/checkin", reqForCheckout);
            System.out.println(response);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        // get outstanding fee for customer
        OutstandingAmountPerCustomerDTO outstandingFeePerCustomer = restTemplate.getForObject(serverUrl + "/reports/getOutstandingAmountPerCustomer/{customerNumber}", OutstandingAmountPerCustomerDTO.class, 1);
        System.out.println("******************* Get outstanding fee *******************");
        System.out.println(outstandingFeePerCustomer);

        // pay fee for the customer
        try {
            PaymentDTO payment = new PaymentDTO();
            payment.setCustomerNumber(1);
            payment.setAmount(10.0);
            System.out.println("******************* pay fee *******************");
            var response = restTemplate.postForLocation(serverUrl + "/payfee", payment);
            System.out.println(response);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
