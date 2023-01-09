package com.rkoyanagui.bookquerywebapp;

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRedisDocumentRepositories
public class BookQueryWebApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(BookQueryWebApp.class, args);
    }
}
