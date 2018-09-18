# spring-aspect-demo

A user has access to zero or more buckets.

The `@BucketAware` annotation prevents access to buckets that the authenticated user
is not authorized to.

If a user attempts to access a bucket to which they are not authorized,
the API will respond with a `403 Forbidden`.

This project runs on port 9000.

### REST API
```
GET localhost:9000/buckets/{bucketId}/items
```

#### Authentication
Use basic authentication to authenticate as one of the following users:

|Username|Password|
|-----|---------|
|user1|password1|
|user2|password2|

### H2 In-Memory Database
The admin console is accessible on `http://localhost:9000/h2-console`

__Username:__ `sa`\
__Password:__ `<blank>`\
__JDBC URL:__ `jdbc:h2:mem:testdb`

#### Mappings
User to Bucket mappings can be found in the `USER_BUCKET` table.

|user_id|bucket_id|
|-------|---------|
|1|1|
|2|2|

The example above indicates that user 1 has access to bucket 1, and user 2 has access to bucket 2.
