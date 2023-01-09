package com.rkoyanagui.bookquerywebapp;

import com.redis.om.spring.annotations.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;

@AllArgsConstructor(staticName = "of")
@Data
@Document
public class Book
{
    @Id
    @Indexed
    private String id;

    @Searchable
    @NonNull
    private String name;

    @NonNull
    private Integer pageCount;

    @Reference
    @NonNull
    private Author author;
}
