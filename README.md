# Profit intelligence system for retail products using cost selling analysis

A RESTful API for managing products, built with Spring Boot 3 and PostgreSQL. It supports creating and retrieving products with automatic profit calculation based on retail and selling costs.

---

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Database Setup](#database-setup)
  - [Configuration](#configuration)
  - [Running the Application](#running-the-application)
- [API Reference](#api-reference)
  - [Create a Product](#create-a-product)
  - [Get All Products](#get-all-products)
  - [Get Product by ID](#get-product-by-id)
- [Data Model](#data-model)
- [Validation Rules](#validation-rules)
- [Future Scope](#future-scope)
- [Contributing](#contributing)

---

## Features

- Create new products with pricing and cost details
- Retrieve all products or a single product by ID
- Automatic profit calculation (`sellingCost - retailCost`)
- Input validation using Jakarta Bean Validation
- Clean DTO-based architecture separating API contracts from the database model

---

## Tech Stack

| Layer        | Technology                          |
|--------------|--------------------------------------|
| Language     | Java 21                              |
| Framework    | Spring Boot 3.5.8                    |
| Database     | PostgreSQL                           |
| ORM          | Spring Data JPA (Hibernate)          |
| Validation   | Jakarta Bean Validation              |
| Build Tool   | Maven (with Maven Wrapper)           |

---

## Project Structure

```
src/main/java/com/example/product_Spring_boot_project/
├── Controller/
│   └── ProductController.java       # REST endpoints
├── Service/
│   ├── ProductService.java          # Service interface
│   └── IProductServiceImp.java      # Service implementation
├── Repository/
│   ├── ProductRepo.java             # Main product repository
│   └── ProductInputRepo.java        # Input repository
├── model/
│   └── Product.java                 # JPA entity
├── dto/
│   ├── InputDto.java                # Request payload DTO
│   ├── ViewDto.java                 # Response payload DTO
│   └── EarningDto.java              # Earnings DTO
└── ProductSpringBootProjectApplication.java
```

---

## Getting Started

### Prerequisites

- Java 21+
- Maven 3.8+ (or use the included `./mvnw` wrapper)
- PostgreSQL 13+

### Database Setup

1. Start your PostgreSQL server and connect to it:

```sql
CREATE DATABASE spring_db;
```

2. The application uses `spring.jpa.hibernate.ddl-auto=update`, so the `products` table will be created automatically on first run.

### Configuration

Update `src/main/resources/application.properties` with your database credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/spring_db
spring.datasource.username=your_username
spring.datasource.password=your_password

server.port=8081
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> **Note:** Avoid committing real credentials to version control. Consider using environment variables or a `.env` file in production.

### Running the Application

Using the Maven Wrapper (no local Maven installation required):

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

The API will be available at `http://localhost:8081`.

---

## API Reference

### Create a Product

**`POST /products`**

Creates a new product and returns the saved product with its computed profit.

**Request Body:**

```json
{
  "productName": "Wireless Headphones",
  "brandName": "SoundMax",
  "description": "Noise-cancelling over-ear headphones",
  "price": 149.99,
  "retailCost": 80.00,
  "sellingCost": 149.99
}
```

**Response `201 Created`:**

```json
{
  "id": 1,
  "productName": "Wireless Headphones",
  "brandName": "SoundMax",
  "description": "Noise-cancelling over-ear headphones",
  "price": 149.99,
  "retailCost": 80.00,
  "sellingCost": 149.99,
  "profitOnProduct": 69.99
}
```

---

### Get All Products

**`GET /Products`**

Returns a list of all products.

**Response `200 OK`:**

```json
[
  {
    "id": 1,
    "productName": "Wireless Headphones",
    "brandName": "SoundMax",
    "description": "Noise-cancelling over-ear headphones",
    "price": 149.99,
    "retailCost": 80.00,
    "sellingCost": 149.99,
    "profitOnProduct": 69.99
  }
]
```

---

### Get Product by ID

**`GET /Products/{id}`**

Returns a single product by its ID.

**Path Parameter:** `id` — the product's numeric ID

**Response `200 OK`:**

```json
{
  "id": 1,
  "productName": "Wireless Headphones",
  "brandName": "SoundMax",
  "description": "Noise-cancelling over-ear headphones",
  "price": 149.99,
  "retailCost": 80.00,
  "sellingCost": 149.99,
  "profitOnProduct": 69.99
}
```

**Response `404 Not Found`:** Returned when no product exists with the given ID.

---

## Data Model

The `Product` entity maps to the `products` table in PostgreSQL.

| Field             | Type          | Required | Description                              |
|-------------------|---------------|----------|------------------------------------------|
| `id`              | Long          | Auto     | Primary key, auto-generated              |
| `productName`     | String        | Yes      | Name of the product (max 150 chars)      |
| `brandName`       | String        | Yes      | Brand of the product (max 100 chars)     |
| `description`     | String        | No       | Product description (max 1000 chars)     |
| `price`           | BigDecimal    | No       | Optional base/listed price               |
| `retailCost`      | BigDecimal    | Yes      | Purchase/inventory cost (≥ 0)            |
| `sellingCost`     | BigDecimal    | Yes      | Price at which product is sold (≥ 0)     |
| `profitOnProduct` | BigDecimal    | No       | Computed as `sellingCost - retailCost`   |

---

## Validation Rules

The API enforces the following constraints on incoming requests:

- `productName` — must not be blank; max 150 characters
- `brandName` — must not be blank; max 100 characters
- `description` — optional; max 1000 characters
- `price` — optional; must be ≥ 0 if provided
- `retailCost` — required; must be ≥ 0
- `sellingCost` — required; must be ≥ 0
- `profitOnProduct` — optional; must be > 0 if provided

---

## Future Scope

The following enhancements are planned or can be considered for upcoming versions of this project:

### API Completeness
- **Update Product (`PUT /Products/{id}`)** — The `UpdateProduct` method is defined in the service interface but not yet implemented; completing it would make this a full CRUD API
- **Delete Product (`DELETE /Products/{id}`)** — The `deleteProduct` service method exists but has no corresponding controller endpoint
- **Earnings endpoint** — The `EarningDto` class is already defined; a dedicated endpoint like `GET /Products/{id}/earnings` could expose profit analytics per product

### Filtering, Sorting & Pagination
- Add query parameters to `GET /Products` for filtering by `brandName`, minimum/maximum price, or profit range
- Support sorting by `productName`, `price`, or `profitOnProduct`
- Implement pagination using Spring Data's `Pageable` to handle large datasets efficiently

### Security
- Integrate **Spring Security** with JWT-based authentication to protect write endpoints (`POST`, `PUT`, `DELETE`)
- Add role-based access control (e.g., `ADMIN` for mutations, `USER` for read-only access)

### Error Handling
- Add a global `@ControllerAdvice` exception handler to return structured error responses (with field-level validation messages) instead of default Spring error pages

### API Documentation
- Integrate **Springdoc OpenAPI (Swagger UI)** to auto-generate interactive API docs at `/swagger-ui.html`

### Testing
- Add unit tests for the service layer (`IProductServiceImp`) using JUnit 5 and Mockito
- Add integration tests for the controller layer using `@SpringBootTest` and `MockMvc`

### DevOps & Deployment
- Containerize the application with **Docker** and add a `docker-compose.yml` for running the app alongside PostgreSQL
- Add a CI/CD pipeline (GitHub Actions) for automated build, test, and deployment
- Externalize secrets using environment variables or a secrets manager (e.g., AWS Secrets Manager, HashiCorp Vault)

### Auditing & Observability
- Add `createdAt` and `updatedAt` timestamp fields to the `Product` entity using Spring Data JPA Auditing
- Integrate **Spring Boot Actuator** for health checks and metrics endpoints
- Set up structured logging with correlation IDs for easier debugging in production

---

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature-name`
3. Commit your changes: `git commit -m "Add your feature"`
4. Push to the branch: `git push origin feature/your-feature-name`
5. Open a Pull Request

Please make sure your code compiles and existing tests pass before submitting a PR.
