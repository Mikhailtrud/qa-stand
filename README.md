# QA Sandbox

QA Sandbox — учебный проект для практики QA Automation, построенный на современном стеке Java + Spring Boot + React.

Проект используется как полигон для изучения UI, API и Database тестирования, а также инфраструктуры автоматизации.

---

# Технологии

## Frontend

- React
- React Router
- Vite

## Backend

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Flyway
- Swagger / OpenAPI

## Database

- PostgreSQL 17

## Automation

- Java 21
- JUnit 5
- Selenide
- REST Assured (планируется)
- Allure
- Gradle

## Infrastructure

- Docker
- Docker Compose

---

# Архитектура

```text
                +----------------+
                |    Frontend    |
                | React + Vite   |
                +-------+--------+
                        |
                        |
                        v
                +----------------+
                |    Backend     |
                | Spring Boot    |
                +-------+--------+
                        |
                        |
                        v
                +----------------+
                | PostgreSQL 17  |
                +----------------+
```

---

# Возможности

## Backend

### Authentication

- Bearer Token авторизация
- Роли ADMIN / USER

### Users API

- GET /users
- GET /users/{id}
- POST /users
- DELETE /users/{id}

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
password
role
created_at
```

---

# Frontend

Страницы:

- Dashboard
- Users
- Playground

Возможности:

- авторизация
- просмотр пользователей
- создание пользователя
- удаление пользователя

---

# QA Playground

Реализовано:

- Text Input
- Password
- Textarea
- Checkbox
- Radio Button
- Select
- Multi Select
- File Upload
- Modal Window
- Alert
- Tabs
- Dynamic Table
- Pagination

---

# Swagger

```
http://localhost:8080/swagger-ui.html
```

---

# Frontend

```
http://localhost:5173
```

---

# Запуск проекта

Из корня проекта:

```bash
docker compose up -d --build
```

---

# Остановка

```bash
docker compose down
```

---

# PostgreSQL

```text
Host: localhost
Port: 5432

Database: qasandbox

User: qauser
Password: qapass
```

---

# Структура проекта

```text
qasandbox

├── backend
├── frontend
├── tests
├── docker-compose.yml
└── README.md
```

---

# Roadmap

## Backend

- BCrypt Password Encoder
- Bean Validation
- Global Exception Handler
- Update User
- Search Users
- Pagination
- DTO для всех API

## Frontend

- Frontend Validation
- Edit User
- Search
- Sorting
- Pagination
- Responsive UI

## QA Playground

Добавить:

- Date Picker
- Drag & Drop
- Infinite Scroll
- Shadow DOM
- iFrame
- Download
- Toast Notifications
- Progress Bar
- Slider

## Automation

- UI тесты
- API тесты
- Database проверки
- UI → API → DB сценарии
- Test Data Builder
- Testcontainers
- Selenoid
- Jenkins / GitHub Actions
- Allure Reports