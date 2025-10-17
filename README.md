# Order Service -- Spring Boot 3 (Challenge 5)

API for Order Management in an e-commerce platform, built with Spring
Boot 3, Java 17, JPA/Hibernate, H2 (dev/test), PostgreSQL (prod), and
Swagger/OpenAPI for documentation.

## Table of Contents

-   Overview
-   Architecture and Design
-   Tech Stack
-   Why These Technologies?
-   Profiles and Environment Variables
-   Project Structure
-   Data Model
-   Endpoints
-   How to Run Locally
-   Swagger / OpenAPI
-   H2 Console
-   Postman Collection
-   Testing Strategy
-   Deployment (prod)
-   Sustainability and Scalability
-   Estimated Budget
-   Short Roadmap
-   How to Push to GitHub
-   License
-   Project Evidence (Screenshots)

## Overview

This service exposes a CRUD for orders with automatic total calculation
from items. It serves as the foundation for a simple but scalable and
well-documented Order Management System (OMS).

### Challenge 5 Objectives

-   **Sprint 1:** Spring Boot 3 project + CRUD + DB + Postman + startup
    script.\
-   **Sprint 2:** dev/test/prod profiles, environment variables, and
    basic security.\
-   **Sprint 3:** Swagger documentation and unit/integration tests.

## Architecture and Design

Controller layer: exposes REST endpoints (/api/orders).\
Service layer: business rules (total calculation, validations).\
Repository layer: persistence via Spring Data JPA.\
Model: entities `Order` and `OrderItem` (1..N).\
Configuration: YML profiles by environment.

    controller → service → repository → database
               ↑ DTO mappers / validations

## Tech Stack

-   **Java 17** -- Modern LTS version with great performance.\
-   **Spring Boot 3.x** -- Rapid application development framework.\
-   **Spring Web** -- REST API module.\
-   **Spring Data JPA / Hibernate** -- ORM and persistence abstraction.\
-   **H2 Database** -- In-memory DB for dev/testing.\
-   **PostgreSQL** -- Production-grade relational database.\
-   **Jakarta Validation** -- DTO validation.\
-   **Swagger / OpenAPI** -- Interactive API documentation.\
-   **Maven** -- Dependency and build management.

## What is Spring Boot?

Spring Boot is a framework that simplifies the creation of Spring
applications through autoconfiguration, an embedded server (Tomcat), and
sensible defaults, enabling fast and productive development.

## What is Swagger/OpenAPI?

OpenAPI is a standard specification for describing REST APIs. Swagger
(via springdoc-openapi) automatically generates interactive
documentation to explore and test endpoints without external
clients---ideal for QA and onboarding.

## Why These Technologies?

-   Rapid development (Spring Boot + Autoconfiguration).\
-   Maintainability (clear layers + DTO validation).\
-   Portability (H2 for dev/test, PostgreSQL for prod).\
-   Observability (Swagger UI improves developer experience).\
-   Scalability (Spring Boot, JPA, and PostgreSQL scale
    horizontally/vertically).

## Profiles and Environment Variables

Profiles separate configuration for each environment:

-   `application.yml` (base)\
-   `application-dev.yml` (H2, SQL logs, H2 console)\
-   `application-test.yml` (H2, create-drop)\
-   `application-prod.yml` (PostgreSQL via env vars)

### Suggested prod variables

    SPRING_DATASOURCE_URL=jdbc:postgresql://<host>:<port>/<db>
    SPRING_DATASOURCE_USERNAME=<user>
    SPRING_DATASOURCE_PASSWORD=<password>
    SPRING_JPA_HIBERNATE_DDL_AUTO=validate
    SERVER_PORT=8080

Activate a profile: `--spring.profiles.active=dev`

## Project Structure

    src/main/java/com/leon/challenge5
     ├─ controller/        
     ├─ dto/               
     ├─ model/             
     ├─ repository/        
     ├─ service/           
     ├─ service/impl/      
     └─ OrderServiceApplication.java

    src/main/resources
     ├─ application.yml
     ├─ application-dev.yml
     ├─ application-test.yml
     └─ (application-prod.yml later)

    docs/                 
    postman/              

## Data Model

### Order

-   id : Long\
-   customerId : String\
-   items : List`<OrderItem>`{=html}\
-   totalAmount : BigDecimal (calculated)\
-   status : String (NEW, PAID, SHIPPED, CANCELED)\
-   createdAt, updatedAt : OffsetDateTime

### OrderItem

-   id : Long\
-   order : Order\
-   productId : String\
-   quantity : Integer\
-   unitPrice : BigDecimal

## Endpoints

  -------------------------------------------------------------------------------------------------
  Method     Route              Description      Request Body        Response
  ---------- ------------------ ---------------- ------------------- ------------------------------
  POST       /api/orders        Create an order  OrderRequest        OrderResponse

  GET        /api/orders        List orders      ---                 Page`<OrderResponse>`{=html}
                                (pagination)                         

  GET        /api/orders/{id}   Get by ID        ---                 OrderResponse

  PUT        /api/orders/{id}   Update order     OrderRequest        OrderResponse

  DELETE     /api/orders/{id}   Delete by ID     ---                 204 No Content
  -------------------------------------------------------------------------------------------------

## How to Run Locally

Requirements: **Java 17** and **Maven 3.9+**

### Quick Start (dev)

    mvn spring-boot:run -Dspring-boot.run.profiles=dev

or via JAR:

    mvn clean package
    java -jar target/order-service-*.jar --spring.profiles.active=dev

## Useful URLs

-   API: http://localhost:8080/api/orders\
-   Swagger UI: http://localhost:8080/swagger-ui/index.html\
-   OpenAPI JSON: http://localhost:8080/v3/api-docs\
-   H2 console: http://localhost:8080/h2-console

## Sustainability and Scalability

Maintainable code (separated layers, DTOs, validations).\
Portable DB setup (H2 for dev/test, PostgreSQL for prod).\
Automatic documentation with Swagger.\
Scalable architecture: stateless Spring Boot design enables horizontal
scaling.

## Estimated Budget

  Task                       Hours   Rate (USD/h)   Subtotal
  -------------------------- ------- -------------- ---------------
  Analysis & Design          6       35             210
  CRUD + DB Implementation   10      35             350
  Profiles & Configuration   4       35             140
  Swagger & Documentation    3       35             105
  Unit/Integration Tests     6       35             210
  **Total Estimated**                               **1,015 USD**

## Short Roadmap

-   Stable CRUD + profiles + Swagger.\
-   Add unit and integration tests.\
-   Implement authentication and CI/CD.\
-   Future: microservices, event queues, cache, metrics.

## How to Push to GitHub

    git init
    git add .
    git commit -m "feat: initial Spring Boot 3 order service"
    git remote add origin https://github.com/<your-username>/order-service.git
    git branch -M main
    git push -u origin main

## License

MIT License\
Author: **León**\
Mentorship: **SofIA -- Challenge 5**

------------------------------------------------------------------------

## 📸 Project Evidence (Screenshots)

### 1️⃣ Spring Initializr Setup

**Description:**\
The project was initialized using Spring Initializr with the following
stack:\
Spring Boot 3.5.6, Java 17, and dependencies Spring Web, Spring Data
JPA, Validation, and H2 Database.

### 2️⃣ Hello World Endpoint Test

**Description:**\
The `/ora` and `/` endpoints were created to verify that the server was
running successfully.\
Swagger UI correctly returned the "Hola Mundo" message, confirming that
the controller mapping works.

### 3️⃣ H2 Database Console

**Description:**\
The H2 console was used to inspect in-memory data.\
The connection was established with `jdbc:h2:mem:ordersdb`, username
`sa`, and no password, confirming proper DB configuration.

### 4️⃣ H2 Database Tables Loaded

**Description:**\
After creating and persisting orders, the H2 console displayed both
tables --- `ORDERS` and `ORDER_ITEMS` --- automatically generated by
JPA/Hibernate.

### 5️⃣ Swagger UI -- CRUD Endpoints

**Description:**\
Swagger (OpenAPI 3.0) automatically documented all CRUD endpoints for
`/api/orders`, including GET, POST, PUT, and DELETE, making API testing
easier.

### 6️⃣ POST -- Create Order Example

**Description:**\
A test order was created using Swagger UI.\
The API dynamically calculated the total amount (449.48) based on the
item quantities and unit prices.

### 7️⃣ GET -- List Orders (Pagination)

**Description:**\
The pagination endpoint `/api/orders?page=0&size=10` returned all saved
orders in JSON format, confirming data persistence and DTO mapping
functionality.

------------------------------------------------------------------------

## 🧩 Integration Summary

All modules---controller, service, repository, and model---were tested
successfully through Swagger and the embedded H2 database.\
The API demonstrated stability, data persistence, and automatic
documentation, proving the system is ready for production deployment
once switched to PostgreSQL.
