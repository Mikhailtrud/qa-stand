# QA Sandbox

Учебный стенд для практики QA Automation.

## Технологии

### Frontend

* React
* Vite

### Backend

* Java 21
* Spring Boot 3
* Spring Data JPA
* Flyway
* Swagger/OpenAPI

### Database

* PostgreSQL 17

### Infrastructure

* Docker
* Docker Compose

---

## Архитектура

```text
Frontend (React)
        |
        v
Backend (Spring Boot)
        |
        v
PostgreSQL
```

---

## Текущие возможности

### Backend API

#### Users

* GET /users
* GET /users/{id}
* POST /users
* DELETE /users/{id}

### Database

Таблица:

```text
users
```

Поля:

```text
id
email
name
role
created_at
```

---

## Swagger

После запуска:

http://localhost:8080/swagger-ui.html

---

## Frontend

После запуска:

http://localhost:5173

Текущий функционал:

* создание пользователя
* просмотр пользователей

---

## Запуск проекта

Из корня проекта:

```bash
docker compose up --build
```

---

## Остановка проекта

```bash
docker compose down
```

---

## PostgreSQL

Параметры подключения:

```text
Host: localhost
Port: 5432

Database: qasandbox

User: qauser
Password: qapass
```

---

## Flyway

Миграции находятся:

```text
backend/src/main/resources/db/migration
```

---

## Цель проекта

Подготовка и практика:

* Selenide
* REST Assured
* JDBC
* Allure
* Docker
* PostgreSQL
* Spring Boot

---

## План развития

### UI Playground

Добавить:

* Input
* Password
* Textarea
* Checkbox
* Radio
* Select
* Multi Select
* Modal
* Alert
* File Upload
* Tabs
* Pagination

### API

Добавить:

* PUT /users/{id}
* расширение CRUD

### Automation

* UI тесты
* API тесты
* DB проверки
* UI → API → DB сценарии
* Allure отчёты

```
```
