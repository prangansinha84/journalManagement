# Secure Journal Management Platform

A secure backend application built with **Spring Boot** for managing personal journal entries with user authentication, role-based authorization, and event-driven communication using Apache Kafka.

## Features

- User registration and authentication
- Secure REST APIs using Spring Security
- BCrypt password hashing
- CRUD operations for journal entries
- MongoDB Atlas integration
- Event-driven architecture using Apache Kafka
- Layered architecture (Controller → Service → Repository)

## Tech Stack

- Java
- Spring Boot
- Spring Security
- Apache Kafka
- MongoDB Atlas
- Maven

## Architecture

```
Controller
    ↓
Service
    ↓
Repository
    ↓
MongoDB Atlas

          ↓
      Apache Kafka
 (Producer ↔ Consumer)
```

## API Features

- User Authentication
- Create Journal
- Read Journal
- Update Journal
- Delete Journal
- Role-based endpoint protection

## Getting Started

### Clone the repository

```bash
git clone https://github.com/prangansinha84/journalApp.git
```

### Configure

Update your MongoDB connection string in:

```
application.properties
```

Configure Kafka broker details.

### Run

```bash
mvn spring-boot:run
```

## Future Improvements

- JWT Authentication
- Docker support
- Swagger/OpenAPI
- Unit & Integration Tests
- CI/CD pipeline
