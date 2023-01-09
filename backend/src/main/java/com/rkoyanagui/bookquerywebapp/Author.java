package com.rkoyanagui.bookquerywebapp;

import com.redis.om.spring.annotations.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@AllArgsConstructor(staticName = "of")
@Data
@Document
public class Author
{
    @Id
    @Indexed
    private String id;

    @Searchable
    @NonNull
    private String firstName;

    @Searchable
    @NonNull
    private String lastName;
}
