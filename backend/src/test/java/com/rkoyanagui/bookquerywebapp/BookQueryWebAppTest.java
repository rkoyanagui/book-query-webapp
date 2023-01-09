package com.rkoyanagui.bookquerywebapp;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
        classes = TestRedisConfiguration.class,
        webEnvironment = DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
class BookQueryWebAppTest
{
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Test
    void shouldFindAllBooks()
    {
        var alice = authorRepository.save(Author.of("author-1", "Alice", "One"));
        var bob = authorRepository.save(Author.of("author-2", "Bob", "Two"));
        bookRepository.save(Book.of("book-1", "First Book", 1, alice));
        bookRepository.save(Book.of("book-2", "Second Book", 2, alice));
        bookRepository.save(Book.of("book-3", "Third Book", 3, bob));
        var requestBody = "{\"query\": \"query {allBooks {id name pageCount author { id firstName lastName } } }\"}";
        var responseBody = "{\"data\":{\"allBooks\":[{\"id\":\"book-1\",\"name\":\"First Book\",\"pageCount\":1,\"author\":{\"id\":\"author-1\",\"firstName\":\"Alice\",\"lastName\":\"One\"}},{\"id\":\"book-2\",\"name\":\"Second Book\",\"pageCount\":2,\"author\":{\"id\":\"author-1\",\"firstName\":\"Alice\",\"lastName\":\"One\"}},{\"id\":\"book-3\",\"name\":\"Third Book\",\"pageCount\":3,\"author\":{\"id\":\"author-2\",\"firstName\":\"Bob\",\"lastName\":\"Two\"}}]}}";
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/graphql")
                .then()
                .statusCode(200)
                .body(equalTo(responseBody));
    }

    @Test
    void shouldFindBookById()
    {
        var alice = authorRepository.save(Author.of("author-1", "Alice", "One"));
        bookRepository.save(Book.of("book-1", "First Book", 1, alice));
        bookRepository.save(Book.of("book-2", "Second Book", 2, alice));
        var requestBody = "{\"query\": \"query {bookById(id: \\\"book-1\\\") {id name pageCount author { id firstName lastName } } }\"}";
        var responseBody = "{\"data\":{\"bookById\":{\"id\":\"book-1\",\"name\":\"First Book\",\"pageCount\":1,\"author\":{\"id\":\"author-1\",\"firstName\":\"Alice\",\"lastName\":\"One\"}}}}";
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/graphql")
                .then()
                .statusCode(200)
                .body(equalTo(responseBody));
    }
}
