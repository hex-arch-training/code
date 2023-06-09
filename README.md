# Hexagonal Architecture's code example

This repository contains an example implementation of some `Document Mangement System`.

## Install and Build
0. Prerequisite: you need a Java SDK 17 and a Git client.
1. Clone this repository:
   ```bash
   git clone https://github.com/hex-arch-training/code.git
   cd code
   ```
2. Build the code using the Maven wrapper:
    ```bash
    ./mvnw clean package
    ```
## Run and enjoy :)
1. You can run the `Document Mangement System`'s REST API as a [Spring Boot]() web application:
    ```bash
    java -jar configuration/web-api/target/web-api-0.0.1-SNAPSHOT.jar
    ```
2. Add a simple revision:
   ```bash
   curl -X POST http://localhost:8080/revision -H 'Content-Type: application/json' -d '{"documentTitle": "Title", "revisionContent": "some content"}'
   ```
   The above POST request returns the ID of the revision just created, e.g: `1`

3. Query the revision just created using this ID:
   ```bash
   curl http://localhost:8080/revision/1
   ```
