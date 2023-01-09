package com.rkoyanagui.bookquerywebapp;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class BookController
{
    private final BookRepository bookRepository;

    @QueryMapping
    public Mono<Book> bookById(@Argument String id)
    {
        return Mono.justOrEmpty(bookRepository.findById(id));
    }

    @QueryMapping
    public Flux<Book> allBooks()
    {
        return Flux.fromIterable(bookRepository.findAll());
    }

    @SchemaMapping
    public Mono<Author> author(Book book)
    {
        return Mono.just(book.getAuthor());
    }
}
