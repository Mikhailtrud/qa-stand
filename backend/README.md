# Backend

Spring Boot backend для QA Sandbox.

## Стек

* Java 21
* Spring Boot 3
* Spring Data JPA
* PostgreSQL
* Flyway
* Swagger

## Запуск локально

```bash
./gradlew bootRun
```

Swagger:

```text
http://localhost:8080/swagger-ui.html
```

## Сборка

```bash
./gradlew build
```

## Структура

```text
src/main/java/com/qasandbox/backend

├── controller
├── entity
├── repository
├── config
└── BackendApplication
```

## API

### Users

```http
GET    /users
GET    /users/{id}
POST   /users
DELETE /users/{id}
```

## Миграции

Папка:

```text
src/main/resources/db/migration
```

Формат:

```text
V1__create_users_table.sql
V2__...
```
