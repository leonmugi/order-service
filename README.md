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
<img width="1920" height="1080" alt="Image" src="https://github.com/user-attachments/assets/4de2b437-fa13-498e-88ef-64dd33b0ab74" />
**Description:**\
The project was initialized using Spring Initializr with the following
stack:\
Spring Boot 3.5.6, Java 17, and dependencies Spring Web, Spring Data
JPA, Validation, and H2 Database.

### 2️⃣ Hello World Endpoint Test
<img width="1920" height="1080" alt="Image" src="https://github.com/user-attachments/assets/e155f8aa-d85d-4dbb-979b-5167e8ee0215" />
**Description:**\
The `/ora` and `/` endpoints were created to verify that the server was
running successfully.\
Swagger UI correctly returned the "Hola Mundo" message, confirming that
the controller mapping works.

### 3️⃣ H2 Database Console
<img width="1920" height="1080" alt="Image" src="https://github.com/user-attachments/assets/3ebdae0c-8fc3-4044-9830-484dc558147b" />
**Description:**\
The H2 console was used to inspect in-memory data.\
The connection was established with `jdbc:h2:mem:ordersdb`, username
`sa`, and no password, confirming proper DB configuration.

### 4️⃣ H2 Database Tables Loaded
<img width="1920" height="1080" alt="Image" src="https://github.com/user-attachments/assets/870d85f1-1c3d-41ee-9872-8e119f831352" />
**Description:**\
After creating and persisting orders, the H2 console displayed both
tables --- `ORDERS` and `ORDER_ITEMS` --- automatically generated by
JPA/Hibernate.

### 5️⃣ Swagger UI -- CRUD Endpoints
<img width="1920" height="1080" alt="Image" src="https://github.com/user-attachments/assets/1b45e41e-d6d8-4dc5-8f17-3a60328fb479" />
**Description:**\
Swagger (OpenAPI 3.0) automatically documented all CRUD endpoints for
`/api/orders`, including GET, POST, PUT, and DELETE, making API testing
easier.

### 6️⃣ POST -- Create Order Example
<img width="1920" height="1080" alt="Image" src="https://github.com/user-attachments/assets/29e5fc43-fa29-4f24-8069-c5ffcfc101cd" />
**Description:**\
A test order was created using Swagger UI.\
The API dynamically calculated the total amount (449.48) based on the
item quantities and unit prices.

### 7️⃣ GET -- List Orders (Pagination)
<img width="1920" height="1080" alt="Image" src="https://github.com/user-attachments/assets/0bdaab61-9dc6-4751-9e89-f75bd0d0dbb7" />
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


# 🧾 Order Service API — Version 2.0.0 (Sprint 2)

This project is part of **Challenge 5** from the **Technoready Backend Bootcamp** and corresponds to **Sprint 2: Environment Profiles and System Variables**.  
Its goal is to implement multiple environment profiles (`dev`, `test`, `prod`) in a **Spring Boot 3** application, each using different configurations and databases.

---

## 🚀 Sprint Objective

The main goal of Sprint 2 was to configure the project to work under different execution environments, separating logic and variables for each one:

- **Development (dev)** → Local environment using an H2 in-memory database.  
- **Testing (test)** → Isolated environment for running automated integration tests.  
- **Production (prod)** → Real environment using PostgreSQL database and persistent configuration.

Each environment uses its own configuration file (`application-*.yml`) and environment variables to keep credentials secure and ensure flexibility.

---

## 🏗️ Project Structure

```
src/
 └── main/
     ├── java/com/leon/challenge5/
     │   ├── controller/
     │   ├── dto/
     │   ├── model/
     │   ├── repository/
     │   ├── service/
     │   └── OrderServiceApplication.java
     └── resources/
         ├── application.yml
         ├── application-dev.yml
         ├── application-test.yml
         ├── application-prod.yml
         └── .env.example
```

---

## ⚙️ Environment Profiles Configuration

### 1️⃣ application.yml

**Base configuration delegating to the active profile:**

```yaml
spring:
  profiles:
    active: @activatedProperties@
```

---

### 2️⃣ Development Profile — application-dev.yml

**Uses H2 database for rapid local testing.**
- Includes the `/h2-console` for data inspection.
- Loads lightweight configuration for faster builds.

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:orders_dev
    driver-class-name: org.h2.Driver
    username: sa
  jpa:
    hibernate:
      ddl-auto: update
server:
  port: 8080
management:
  endpoints:
    web:
      exposure:
        include: health,info
```

✅ **Expected result:**  
`http://localhost:8080/actuator/health` returns `{ "status": "UP" }`

---

### 3️⃣ Test Profile — application-test.yml

**Used during unit and integration testing via Maven.**

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:orders_test
    driver-class-name: org.h2.Driver
    username: sa
  jpa:
    hibernate:
      ddl-auto: create-drop
server:
  port: 0
```

✅ **Expected result:**  
`BUILD SUCCESS` on Maven test output.

---

### 4️⃣ Production Profile — application-prod.yml

**Connects to a PostgreSQL database and uses .env variables for credentials.**

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/orders
    username: orders_user
    password: change_me
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    database-platform: org.hibernate.dialect.PostgreSQLDialect
server:
  port: 8080
management:
  endpoints:
    web:
      exposure:
        include: health,info
```

✅ **Expected result:**

```json
{
  "status": "UP",
  "components": {
    "db": { "status": "UP", "details": { "database": "PostgreSQL" } },
    "diskSpace": { "status": "UP" },
    "ping": { "status": "UP" }
  }
}
```

---

## 💰 Estimated Budget and Resources (SPRINT 2)

| Category | Resource | Cost (USD) |
|-----------|-----------|------------|
| Development tools | IntelliJ IDEA, Postman, H2, Maven | $0 (community/free versions) |
| Database | PostgreSQL (local installation) | $0 |
| Hardware & Power | Developer machine, internet, electricity | $500 USD approx. |
| **Total estimated to date** |  | **≈ $500 USD** |

---

## 📈 Scalability & Sustainability

### Scalability
- The app follows a modular, profile-based design allowing deployment to multiple environments.  
- PostgreSQL supports vertical and horizontal scaling, and the project can integrate Docker or Kubernetes for orchestration.  
- This makes the system ready for CI/CD pipelines and distributed microservices in future sprints.

### Sustainability
- Built entirely on open-source technologies (Spring Boot, PostgreSQL, H2).  
- Externalized configuration ensures reusable code across environments.  
- Lightweight components reduce memory and CPU use — aligning with green software principles.

---

## 🧱 Technical Stack

| Component | Technology |
|------------|-------------|
| Language | Java 17 |
| Framework | Spring Boot 3.5.6 |
| ORM | Hibernate / JPA |
| Databases | H2, PostgreSQL |
| Build Tool | Maven |
| Documentation | Springdoc OpenAPI (Swagger UI) |
| IDE | IntelliJ IDEA |
| Version Control | Git & GitHub |

---

## 🧩 How to Run

Clone the repository:
```bash
git clone https://github.com/leonmugi/order-service.git
cd order-service
```

Set environment variables in `.env` or your system.

Run with desired profile:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
or
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

Test endpoints:

- **Health check** → [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)  
- **Swagger UI** → [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  
- **H2 Console (dev only)** → [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---

## 📘 Learnings & Takeaways

- Practical understanding of Spring Boot environment profiles.  
- Secure management of environment variables.  
- Use of H2 in-memory DB for testing.  
- Full integration of PostgreSQL for production use.  
- Validation via Spring Actuator endpoints.  
- Project ready for scalability and continuous delivery.

---
The screenshot shows the user setting environment variables in Windows PowerShell for the Spring Boot production profile.
Commands like setx SERVER_PORT, setx DB_URL, setx DB_USERNAME, and setx DB_PASSWORD are used to securely store database connection details for PostgreSQL. Each variable is confirmed as “saved successfully.”

<img width="996" height="996" alt="Image" src="https://github.com/user-attachments/assets/134cc317-87f3-4eec-9b28-947485c53948" />

This image displays the Spring Boot test run in the terminal (Git Bash).
The active profile is “test”, using the in-memory H2 database.
Hibernate initializes successfully, and the test results show:
✅ Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
💬 The final message is “BUILD SUCCESS”, confirming that all integration tests passed correctly.

<img width="1920" height="1080" alt="Image" src="https://github.com/user-attachments/assets/ba1aace7-6828-42d7-b1be-dcd049eb1392" />

The screenshot shows the H2 web console running at localhost:8080/h2-console.
It displays database tables such as ORDERS and ORDER_ITEMS, showing that the schema and in-memory database were correctly initialized during the development profile execution.

<img width="1920" height="1080" alt="Image" src="https://github.com/user-attachments/assets/204caad5-accb-464f-a182-4a8a96ba31bf" />

This image captures the Spring Boot Actuator health check at http://localhost:52572/actuator/health.
The JSON response confirms that all components are active:

"status": "UP"

"database": "H2"

"ping": "UP"

"diskSpace": "UP"
indicating the application is fully operational and connected to the expected H2 database instance.

<img width="1920" height="1030" alt="Image" src="https://github.com/user-attachments/assets/99286bc3-be5d-431b-b54c-914043536b78" />

📦 **Version: 2.0.0 — Sprint 2**  
**Challenge 5 — Technoready Backend Bootcamp**


# Order Service API — Version 3.0.0 (Sprint 3)

**Technoready Backend Bootcamp — Challenge 5**  
Stage: **Sprint 3 — OpenAPI (Swagger) Documentation, Unit and Integration Testing, and Quality Assurance**

---

## Objective of Sprint 3

The main goal of this sprint is to ensure that the Order Service API is fully documented, validated, and tested according to production-grade quality standards.  
This includes implementing **OpenAPI/Swagger documentation**, creating **unit and integration test suites** with **code coverage analysis**, and ensuring repository readiness for CI/CD integration.

**Expected outcomes:**
1. Fully functional and documented OpenAPI (Swagger) interface.
2. Comprehensive unit and integration test coverage.
3. Automated quality reports (JaCoCo).
4. Source code structured and documented.
5. Execution and usage instructions clearly stated in the repository.

---

## Architecture and Technology Stack

- **Java 17**
- **Spring Boot 3.x**
- **Build System:** Maven
- **Persistence:** H2 (dev/test) and PostgreSQL (prod)
- **API Documentation:** Springdoc OpenAPI (Swagger UI)
- **Testing:** JUnit 5, Mockito, Spring Test (MockMvc / WebTestClient)
- **Coverage:** JaCoCo
- **Optional Integration:** Testcontainers (PostgreSQL)

### Project Structure
```
src/
 ├── main/java/com/leon/challenge5/order/
 │   ├── controller/       # REST endpoints
 │   ├── service/          # Business logic
 │   ├── repository/       # JPA repositories
 │   ├── model/            # Entities / DTOs
 │   └── config/           # Swagger, CORS, etc.
 └── test/java/com/leon/challenge5/order/
     ├── unit/             # Unit tests
     └── integration/      # Integration tests
```

---

## Domain Overview

This service manages **Orders** with CRUD operations and related **OrderItems**.  
Each order contains a set of items, total price calculation, and status management.

**Example JSON:**
```json
{
  "id": 1,
  "customerId": "CUST-001",
  "status": "CREATED",
  "items": [
    {"sku": "SKU-123", "name": "Widget", "quantity": 2, "unitPrice": 99.5}
  ],
  "total": 199.0,
  "createdAt": "2025-10-01T10:00:00Z"
}
```

---

## API Documentation (OpenAPI / Swagger)

### Maven Dependency
```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.6.0</version>
</dependency>
```

### Access URLs
- Swagger UI → `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON → `http://localhost:8080/v3/api-docs`

### Custom Configuration
```java
@OpenAPIDefinition(
  info = @Info(
    title = "Order Service API",
    version = "3.0.0",
    description = "CRUD operations, order status transitions, and validation"
  ),
  servers = {@Server(url = "/", description = "Local")}
)
@Configuration
public class SwaggerConfig {}
```

### Documentation Best Practices
- Annotate endpoints with `@Operation`, `@ApiResponses`, and DTOs with `@Schema`.
- Document all status codes (200, 201, 204, 400, 404, 409, 422, 500).
- Include validation rules using `jakarta.validation` annotations.
- Provide request/response examples for each major endpoint.

---

## Environment Configuration (Profiles)

Inherited from Sprint 2:
- `application-dev.yml` → H2 database, `spring.jpa.hibernate.ddl-auto=update`.
- `application-test.yml` → H2 or PostgreSQL via Testcontainers.
- `application-prod.yml` → PostgreSQL with environment variable credentials.

**Run with a profile:**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

---

## Testing

### Scope and Criteria
- **Unit tests:** Validate isolated business logic using Mockito.
- **Integration tests:** Validate endpoint flows using MockMvc or WebTestClient.
- Cover success, edge, and failure cases.
- Minimum coverage thresholds:
  - 80% line coverage
  - 70% branch coverage

### Example Unit Test
```java
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

  @Mock private OrderRepository repo;
  @InjectMocks private OrderService service;

  @Test
  void create_shouldCalculateTotalAndPersist() {
    Order o = new Order();
    when(repo.save(any())).thenAnswer(inv -> {
      Order saved = inv.getArgument(0);
      saved.setId(1L);
      return saved;
    });

    Order result = service.create(o);

    assertNotNull(result.getId());
    assertTrue(result.getTotal().doubleValue() > 0);
    verify(repo).save(any(Order.class));
  }
}
```

### Example Integration Test
```java
@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerIT {

  @Autowired private MockMvc mockMvc;

  @Test
  void createOrder_shouldReturn201AndLocation() throws Exception {
    String body = "{\"customerId\":\"CUST-1\",\"items\":[{\"sku\":\"SKU-1\",\"name\":\"X\",\"quantity\":1,\"unitPrice\":10.0}]}";

    mockMvc.perform(post("/api/v1/orders")
          .contentType(MediaType.APPLICATION_JSON)
          .content(body))
      .andExpect(status().isCreated())
      .andExpect(header().exists("Location"));
  }
}
```

### Run Tests and Generate Coverage
```bash
mvn clean verify
```
Coverage report → `target/site/jacoco/index.html`

---

## Test Scenarios

1. Create order (201) and verify total calculation.
2. Create with empty items (400).
3. Create with invalid quantity or price (422).
4. Retrieve existing order (200) and non-existing (404).
5. Update status (CREATED → PAID → SHIPPED → CANCELLED) with proper rules.
6. Delete order (204) and confirm not found afterwards (404).
7. Validation and error response formats.
8. Concurrency cases (optional optimistic locking).

---

## Local Execution

### Requirements
- JDK 17+
- Maven 3.9+
- Docker (optional for PostgreSQL or Testcontainers)

### Commands
```bash
# Run in dev profile
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Execute tests
mvn clean verify

# Build JAR for production
mvn -DskipTests package
java -jar target/order-service-*.jar --spring.profiles.active=prod
```

### Useful URLs
- Swagger UI → `http://localhost:8080/swagger-ui/index.html`
- H2 Console → `http://localhost:8080/h2-console`

---

## Continuous Integration 

Example GitHub Actions workflow:
```yaml
name: CI
on: [push, pull_request]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: "17"
      - name: Build and Test
        run: mvn -B clean verify
```

---

## Design Rationale

- **Springdoc OpenAPI** provides automatic API documentation for the project.
- **JUnit 5 + Mockito** ensures test isolation and flexibility.
- **Spring Test (MockMvc)** validates full HTTP-layer integration.
- **H2 Database** offers lightweight in-memory persistence for quick local testing.
- **JaCoCo** provides code coverage enforcement to ensure continuous quality.
- **Profiles (dev/test/prod)** separate configurations for maintainability.
- **REST conventions** are strictly followed (proper status codes and messages).

# Project Context and Objective

The Order Service API was developed as part of the Technoready Backend Bootcamp, Challenge 5.  
The objective was to design, document, and test a scalable microservice using **Spring Boot 3** that could efficiently handle order management operations while adhering to professional software engineering standards.

The project simulates a real-world scenario similar to an e-commerce order management system, where accuracy, reliability, and scalability are essential.  
It responds to a common industry challenge: maintaining synchronized order data across multiple environments while ensuring robust API documentation and automated validation through testing.

---

## Sprint Focus Areas

The sprint specifically focuses on three pillars:

1. **Comprehensive API Documentation** using **OpenAPI / Swagger**.  
2. **Unit and Integration Testing** with **JUnit 5**, **Mockito**, and **Spring Test**.  
3. **Code Quality and Sustainability**, including **JaCoCo** coverage analysis and environment profiles for development, testing, and production.

---

## Technical Overview

The project is built using **Java 17** and **Spring Boot 3**, following a modular architecture divided into layers:  
**Controller**, **Service**, **Repository**, and **Model**.

The persistence layer supports multiple profiles:

- **Development and Testing:** In-memory **H2** database for fast iteration.  
- **Production:** **PostgreSQL** database connected through environment variables.

**Swagger (springdoc-openapi)** automatically documents all endpoints, displaying their request/response structures and validation rules.  
This allows the API to serve as a self-descriptive contract that developers and clients can easily test via the Swagger UI interface at runtime.

### Key Features

- CRUD operations for **Orders** and related **OrderItems**.  
- Automatic **total calculation** based on quantity and unit price.  
- Validation rules enforced through **jakarta.validation** annotations (`@NotNull`, `@Positive`, `@NotBlank`).  
- Proper **HTTP status codes** and **error handling** using `ProblemDetail` for standardized responses.  
- Environment isolation through **Spring Profiles** (`dev`, `test`, `prod`), allowing smooth transitions between local testing and deployment.

---

## Testing and Quality Assurance

The service integrates **JUnit 5**, **Mockito**, and **Spring Boot Test** to ensure functionality across all layers.  
Two test categories are implemented:

- **Unit Tests:** Validate business logic, calculations, and repository calls independently.  
- **Integration Tests:** Validate the full flow through REST endpoints using **MockMvc** and embedded databases.

Code coverage is analyzed with **JaCoCo**, guaranteeing that at least **80% of lines** and **70% of branches** are verified.  
This level of automation provides long-term maintainability and confidence in each build.

---

## Functional Description

The **Order Service API** acts as a core microservice in a potential distributed system that handles customer orders.  
It exposes REST endpoints for:

- **Creating new orders:** Validates input, calculates totals, and stores results in the database.  
- **Reading orders:** Returns all orders or specific ones by ID.  
- **Updating orders:** Modifies order data or transitions their status (e.g., from `CREATED` to `PAID` or `SHIPPED`).  
- **Deleting orders:** Removes outdated or invalid entries safely.

Each operation is documented within Swagger, allowing users to test live interactions directly from the browser.  
Responses include structured JSON with timestamps, status codes, and detailed error messages when applicable.

### Example Response

```json
{
  "id": 1,
  "customerId": "CUST-001",
  "status": "PAID",
  "items": [
    {"sku": "SKU-123", "name": "Widget", "quantity": 2, "unitPrice": 99.5}
  ],
  "total": 199.0,
  "createdAt": "2025-10-01T10:00:00Z"
}
```

This approach demonstrates the fundamental capabilities of modern **service-oriented architecture**:  
**clear contracts**, **predictable outputs**, and **consistent validation**.


## Budget

# Technical Budget Report – Order Service API Development

**Eng. Leonel Campos Valdés**  
*Biomedical Engineer & Backend Developer Apprentice*  
*Technoready Backend Bootcamp*  

---

## 1. Project Overview

The *Order Service API*   
Its objective was to design, document, and test a scalable microservice using **Spring Boot 3** that efficiently manages order processing operations.  
The service simulates an **e-commerce order management environment** requiring accuracy, reliability, and scalability.  
It includes API documentation with **OpenAPI/Swagger**, automated testing (**JUnit 5, Mockito**), and quality validation (**JaCoCo coverage reports**).

---

## 2. Professional Profile

This project was executed by **Eng. Leonel Campos Valdés**, a Biomedical Engineer specialized in healthcare simulation technology and currently training as a Backend Developer.  
The approach integrates biomedical engineering methodology with modern software development standards, focusing on modular architecture, documentation, and system sustainability.

---

## 3. Methodology and Work Phases

The project was structured into **five main phases** that reflect a complete software engineering cycle.  
Each phase was executed using agile principles with measurable deliverables, continuous testing, and version control through GitHub.

| Phase | Description | Estimated Hours | Rate (MXN/h) | Subtotal (MXN) |
|:------|:-------------|:----------------|:--------------|:----------------|
| **1. Analysis & Planning** | Requirements gathering, data modeling, system scope definition | 10 | $320 | $3,200 |
| **2. Architecture & Design** | System architecture, entity modeling, OpenAPI setup | 12 | $320 | $3,840 |
| **3. Implementation** | Backend coding (Spring Boot, Controllers, Services, Repositories) | 20 | $320 | $6,400 |
| **4. Testing & QA** | JUnit, Mockito, Integration Tests, JaCoCo configuration | 10 | $320 | $3,200 |
| **5. Documentation & Deployment** | Technical README, Swagger documentation, setup validation | 6 | $320 | $1,920 |

---

## 4. Financial Summary

The financial estimation considers a **blended hourly rate** derived from biomedical and software engineering tabulators.  
A **15% profit margin** and **16% VAT (IVA)** are applied to the subtotal to calculate the final cost.

| Concept | Amount (MXN) |
|:---------|--------------:|
| **Subtotal** | $18,560.00 |
| **Profit Margin (15%)** | $2,784.00 |
| **Total Estimated Cost** | **$21,344.00 MXN** |

---

## 5. Technical and Economic Justification

The proposed cost reflects not only the hours dedicated to development but also the **professional value added** by integrating biomedical engineering precision with backend software development practices.  
Each deliverable adheres to **clean code principles**, **standardized documentation**, and **reproducibility** across different environments (`dev`, `test`, `prod`).  

Automated testing, API documentation, and architectural scalability ensure **long-term sustainability** and **quality assurance**.  
The final cost of approximately **$51,000 MXN** represents a fair market value for professional backend API development within the Mexican engineering context, considering the project’s complexity, documentation quality, and inclusion of sustainability and scalability practices.

---

### Prepared by:
**Eng. Leonel Campos Valdés**  
Biomedical Engineer & Backend Developer Apprentice  
*October 2025*

---


## Scalability and Sustainability

### Scalability
- **Stateless Architecture:** Each API instance is stateless and horizontally scalable through container orchestration (e.g., Docker / Kubernetes).
- **Database Scalability:** PostgreSQL allows replication and read scaling. Connection pooling via HikariCP ensures efficient resource use.
- **Load Balancing:** Multiple application nodes can be deployed behind a load balancer to handle high request volumes.
- **Microservice Ready:** The Order Service can integrate within a distributed architecture, communicating via REST or message queues (Kafka/RabbitMQ).

### Sustainability
- **Code Maintainability:** Modular layered structure (controller / service / repository) ensures separation of concerns and reusability.
- **Automated Testing:** Comprehensive testing provides long-term maintainability and early defect detection.
- **Documentation:** API contracts are automatically generated via OpenAPI, facilitating external integration and onboarding.
- **Energy and Resource Efficiency:** Lightweight frameworks (Spring Boot 3, H2) and containerization minimize resource usage.
- **Continuous Integration:** Automated pipelines encourage consistent builds and reduce manual errors, supporting sustainable development.

---

## Version 3.0.0 (Sprint 3)



