package com.rkoyanagui.bookquerywebapp;

import com.redis.om.spring.repository.RedisDocumentRepository;

public interface AuthorRepository extends RedisDocumentRepository<Author, String>
{
}
