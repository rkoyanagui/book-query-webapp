package com.rkoyanagui.bookquerywebapp;

import com.redis.om.spring.repository.RedisDocumentRepository;

public interface BookRepository extends RedisDocumentRepository<Book, String>
{
}
