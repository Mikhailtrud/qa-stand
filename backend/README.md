# Backend

Spring Boot backend для проекта QA Sandbox.

## Стек

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- PostgreSQL
- Flyway
- Swagger / OpenAPI
- Docker

---

## Возможности

- Bearer Token авторизация
- Ролевая модель (ADMIN / USER)
- CRUD пользователей
- Bean Validation
- Flyway миграции
- Swagger UI

---

## Запуск локально

```bash
./gradlew bootRun
```

---

## Сборка

```bash
./gradlew build
```

---

## Swagger

```
http://localhost:8080/swagger-ui.html
```

---

## Структура проекта

```text
src/main/java/com/qasandbox/backend

├── controller
├── dto
├── entity
├── repository
├── security
├── config
└── BackendApplication
```

---

## REST API

### Authentication

```http
POST /auth/login
```

---

### Users

```http
GET    /users
GET    /users/{id}
POST   /users
DELETE /users/{id}
```

---

## Авторизация

Все запросы (кроме `/auth/login` и Swagger) требуют Bearer Token.

Пример:

```
Authorization: Bearer admin-token
```

---

## Миграции

Расположение:

```text
src/main/resources/db/migration
```

Пример:

```text
V1__create_users_table.sql
V2__insert_default_users.sql
```

---

## Roadmap

Планируется добавить:

- BCrypt Password Encoder
- Global Exception Handler
- DTO для всех запросов и ответов
- API Validation Errors
- Integration Tests
- Testcontainers