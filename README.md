# Product - Inventory Management API

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-4.0.2-brightgreen)
![Maven](https://img.shields.io/badge/Maven-Build-red)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)

## 📋 Overview

**Product** is a RESTful API built with **Spring Boot** for managing product inventories.  
It allows applications to perform CRUD operations on products stored in a **PostgreSQL database**.

The project follows **Spring Boot best practices**, with a layered architecture and automatic API documentation using **OpenAPI / Swagger**.

---

## ✨ Features

- Product CRUD operations
- RESTful API design
- PostgreSQL database integration
- JPA / Hibernate ORM
- Interactive API documentation with Swagger
- Maven project management
- Unit and integration testing

---

##  🛠️ Tech Stack

| Technology | Description |
|------------|-------------|
| Java 17 | Programming language |
| Spring Boot 4.0.2 | Application framework |
| Spring Web MVC | REST API development |
| Spring Data JPA | Database access layer |
| PostgreSQL | Relational database |
| Lombok | Boilerplate code reduction |
| SpringDoc OpenAPI | API documentation |
| Maven | Dependency management |

---

##  🏗️ Architecture

The application follows a **layered architecture**:

**Controller**
Handles HTTP requests.

**Service**
Contains business logic.

**Repository**
Data access layer using Spring Data JPA.

**Entity**
Represents database tables.

---

##  📁 Project Structure

src
├── main
│ ├── java
│ │ └── ken/tar/product
│ │ ├── controller
│ │ ├── service
│ │ ├── repository
│ │ ├── model
│ │ └── ProductApplication.java
│ │
│ └── resources
│ └── application.properties
│
└── test
└── java


---

## ✅ Prerequisites

Before running the project install:

- **Java 17**
- **Maven**
- **PostgreSQL**
- **Git**

Check versions:

```bash
java -version
mvn -version 
```

---

## 🔧 Installation

Clone the repository:
 ```bash
git clone https://github.com/your-username/product.git
cd product
```
Build the project:

```bash
mvn clean install
```

---

## 🗄️ Database Setup

```SQL
CREATE DATABASE productdb;
```

Update configuration:

#### src/main/resources/application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/productdb
spring.datasource.username=${USERNAME}
spring.datasource.password=${PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

---

## 🚀 Running the Application

Run using Maven:

```bash
mvn clean
mvn clean package -DskipTests
docker-compose up -d
```

Server starts on:

- http://localhost:8080 if you are using mvn spring-boot:run 

- http://localhost:8082 if you are running product-service container

---

## 📚 API Documentation 

Once the application is running, you can access the interactive Swagger UI:

- Maven run: http://localhost:8080/swagger-ui/index.html

- Docker run: http://localhost:8082/swagger-ui/index.html

---

## 📮 Example API Endpoints

### API Endpoints

| Method | Endpoint | Description | Status Code    |
|------|------|------|----------------|
| GET | /products | Retrieve all products | 200 OK         |
| GET | /products/{id} | Retrieve a product by its ID | 200 OK         |
| GET | /products/search?name=value | Search products by name | 200 OK         |
| POST | /products | Create a new product | 201 CREATED    |
| PUT | /products/{id} | Fully update a product | 202 ACCEPTED   |
| PATCH | /products/{id} | Partially update a product | 202 ACCEPTED   |
| DELETE | /products/{id} | Delete a product | 204 NO CONTENT |

---

## 🔮 Future Improvements

Possible improvements:

- Authentication with Spring Security
- Pagination and filtering
- CI/CD pipeline
- API versioning 

---

## 👤 Author

**KENMOE Marc**

Github: [@kenmoe](https://github.com/KENMOEmarc)

Email: [kenmarcbertrand@gmail.com](kenmarcbertrand@gmail.com)

---

## 🙏 Acknowledgements

- Spring Boot
- OpenAPI
- PostgreSQL
