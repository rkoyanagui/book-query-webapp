# book-query-webapp

Simple book query service.

Data is bulk loaded to a Redis database. It can be accessed via GraphQL at `/graphql`.

Example:

```json
{
  "query": "query {allBooks {id name pageCount author { id firstName lastName } } }"
}
```
