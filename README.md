# Employee Management System using Spring Boot

A RESTful Employee Management System built using Spring Boot that provides secure authentication and role-based authorization using JWT. The application allows administrators to manage employees and departments efficiently with features like CRUD operations, pagination, sorting, filtering, validation, logging, and API documentation using Swagger.

## Features

- JWT Authentication and Authorization
- Role-Based Access Control (Admin/User)
- Employee CRUD Operations
- Department CRUD Operations
- Employee–Department Relationship
- Pagination, Sorting, and Filtering
- Request Validation
- Global Exception Handling
- ModelMapper for DTO Mapping
- SLF4J Logging
- Swagger/OpenAPI Documentation
- MySQL Database Integration
- Spring Data JPA with Hibernate

## Tech Stack

- Java 17
- Spring Boot 3.5
- Spring Security
- Spring Data JPA (Hibernate)
- MySQL
- JWT (JJWT)
- ModelMapper
- Lombok
- Maven
- Swagger (SpringDoc OpenAPI)

## Project Structure
src
├── organization
├── config
├── controller
├── dto
├── entity
├── exceptionhandler
├── logger
├── service
├── repository
├── entity
├── security
├── specification
├── validation
└── util
├── pom.xml

## API Documentation
http://localhost:8081/swagger-ui/index.html

After running the application, access Swagger UI at:
## Authentication

The application uses JWT-based authentication. Users must log in to obtain a JWT token, which is required to access secured endpoints.

## Logging

Application logs are generated using SLF4J and Logback for easier monitoring and debugging.

## Database

- MySQL
- Spring Data JPA
- Hibernate ORM

## Build Tool

- Maven

