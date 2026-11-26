# Spring Boot Student CRUD REST API

A RESTful CRUD application built with **Spring Boot**, **Spring Data JPA**, **MySQL**, **Bean Validation**, and **Global Exception Handling**.

## Tech Stack
* **Java / Spring Boot** (Spring Web, Spring Data JPA, Validation)
* **Database:** MySQL
* **Tools:** IntelliJ IDEA, Postman, DBeaver, Maven

## Features
* Complete **CRUD operations** for Student records (`Create`, `Read`, `Update`, `Delete`)
* **DTO pattern** separating request/response payloads from database entities
* **Input validation** using `@Valid` and custom validation error responses
* **Global exception handling** (`@RestControllerAdvice`) for `ResourceNotFound`, duplicate resources, and field validation errors

## How to Run Locally

### 1. Clone the Repository
```bash
git clone https://github.com
cd spring-boot-crud
```

### 2. Configure MySQL Database
Create a MySQL database named `student_crud_db`:
```sql
CREATE DATABASE student_crud_db;
```

### 3. Set Your Database Password
This project uses an environment variable (`DB_PASSWORD`) so your database credentials stay private.
* **In IntelliJ IDEA:** Go to **Run → Edit Configurations → Environment variables** and add:
  ```text
  DB_PASSWORD=your_mysql_password
  ```
* **Or via Terminal:**
  ```bash
  export DB_PASSWORD=your_mysql_password   # Mac/Linux
  set DB_PASSWORD=your_mysql_password      # Windows
  ```

### 4. Start the Application
Run `CrudApplication.java` in IntelliJ IDEA or via Maven:
```bash
mvn spring-boot:run
```
The server starts at `http://localhost:8080`.
