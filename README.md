# Employee Management System

A Spring Boot backend project for managing employees and departments through REST APIs.

## Features

- Create, list, get, update, and delete employees
- Create and list departments
- Filter employees by name, email, job title, department, and status
- Track employee status and audit timestamps
- Validate request payloads
- Return consistent API error responses
- Reject duplicate records and invalid pagination/sorting inputs
- Explore the API with Swagger UI and OpenAPI docs
- Persist data with Spring Data JPA and MySQL
- Run locally with an H2 in-memory database
- Run integration tests against H2 in-memory database

## Tech Stack

- Java 17
- Spring Boot 3.5
- Spring Web
- Spring Data JPA
- Bean Validation
- Springdoc OpenAPI / Swagger UI
- MySQL
- Maven
- JUnit 5 and MockMvc

## Project Structure

- `controller` keeps HTTP endpoints thin
- `service` contains business logic
- `repository` handles data access
- `entity` stores JPA models
- `dto` defines request and response shapes
- `exception` centralizes API error handling

## API Endpoints

- `GET /api/hello`
- `POST /api/departments`
- `GET /api/departments`
- `POST /api/employees`
- `GET /api/employees`
- `GET /api/employees/{id}`
- `PUT /api/employees/{id}`
- `DELETE /api/employees/{id}`
- `GET /v3/api-docs`
- `GET /swagger-ui/index.html`

### Employee list query parameters

- `page` default `0`
- `size` default `10`
- `sortBy` default `id`
- `sortDirection` default `asc`
- `name`
- `email`
- `jobTitle`
- `departmentId`
- `status` supports `ACTIVE` or `INACTIVE`

Example:

```text
GET /api/employees?status=ACTIVE&page=0&size=5&sortBy=firstName&sortDirection=desc
```

## Example Employee Payload

```json
{
  "firstName": "Alicia",
  "lastName": "Tan",
  "email": "alicia.tan@example.com",
  "jobTitle": "Backend Developer",
  "salary": 5200.00,
  "departmentId": 1,
  "status": "ACTIVE"
}
```

## Local Setup

1. Install JDK 17 and verify `java -version`.
2. Run tests:

```bash
./mvnw test
```

3. Start the application with the local H2 profile:

```bash
DEBUG=false ./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

Swagger UI is available at:

```text
http://localhost:8080/swagger-ui/index.html
```

4. For MySQL, create a database named `employee_management_system`.
5. Set environment variables if your database credentials are not the defaults:
   - `DB_URL`
   - `DB_USERNAME`
   - `DB_PASSWORD`
6. Start the application with the default profile:

```bash
DEBUG=false ./mvnw spring-boot:run
```

## What This Project Demonstrates

- Layered Spring Boot application structure
- JPA entity relationships
- CRUD REST API design
- Input validation and centralized exception handling
- Employee filtering, pagination, and sorting
- Employee status and audit timestamps
- Integration testing for happy paths and unhappy paths
- Swagger UI and generated OpenAPI documentation

## Roadmap

### Strong portfolio improvements

- Soft delete or deactivation flow
- Manager relationships
- CSV export
- Flyway migrations
- Docker setup

### Optional future extension

- AI help assistant using RAG over system usage guides or FAQ content
- Example goal: help users answer “how do I use this system?” questions more quickly
- This is a later add-on, not the main backend proof
