package com.miu.libraryapplication.RestAssured;

import com.miu.libraryapplication.domain.Customer;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class BorrowRestAssuredTest {
    @BeforeClass
    public static void setup(){
        RestAssured.port = Integer.valueOf(8081);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testGetOneCustomer() {
        // add the book to be fetched
        Customer customer = new Customer("Ritesh", "ritesh@gmail.com");
        given()
                .contentType("application/json")
                .body(customer)
                .when().post("/customers").then()
                .statusCode(200);
        // test getting the book
        given()
                .when()
                .get("/customers/1")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("name",equalTo("Ritesh"))
                .body("email",equalTo("ritesh@gmail.com"));
        //cleanup
        given()
                .when()
                .delete("/customers/1");
    }
}
