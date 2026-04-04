# Employee Management System

A Spring Boot backend project for managing employees and departments through REST APIs.

## Features

- Create, list, get, update, and delete employees
- Create and list departments
- Validate request payloads
- Return consistent API error responses
- Persist data with Spring Data JPA and MySQL
- Run integration tests against H2 in-memory database

## Tech Stack

- Java 17
- Spring Boot 3.5
- Spring Web
- Spring Data JPA
- Bean Validation
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

### Employee list query parameters

- `page` default `0`
- `size` default `10`
- `sortBy` default `id`
- `sortDirection` default `asc`

Example:

```text
GET /api/employees?page=0&size=5&sortBy=firstName&sortDirection=desc
```

## Example Employee Payload

```json
{
  "firstName": "Alicia",
  "lastName": "Tan",
  "email": "alicia.tan@example.com",
  "jobTitle": "Backend Developer",
  "salary": 5200.00,
  "departmentId": 1
}
```

## Local Setup

1. Install JDK 17 and verify `java -version`.
2. Create a MySQL database named `employee_management_system`.
3. Set environment variables if your database credentials are not the defaults:
   - `DB_URL`
   - `DB_USERNAME`
   - `DB_PASSWORD`
4. Run tests:

```powershell
.\mvnw test
```

5. Start the application:

```powershell
.\mvnw spring-boot:run
```

## What This Project Demonstrates

- Layered Spring Boot application structure
- JPA entity relationships
- CRUD REST API design
- Input validation and centralized exception handling
- Pagination and sorting for employee listing
- Basic integration testing for controller endpoints

## Roadmap

### Must-have backend improvements

- Employee search and filtering
- Employee status and audit timestamps
- Better README and broader test coverage

### Strong portfolio improvements

- Swagger / OpenAPI
- Soft delete or deactivation flow
- Manager relationships
- CSV export
- Flyway migrations
- Docker setup

### Optional future extension

- AI help assistant using RAG over system usage guides or FAQ content
- Example goal: help users answer “how do I use this system?” questions more quickly
- This is a later add-on, not the main backend proof
