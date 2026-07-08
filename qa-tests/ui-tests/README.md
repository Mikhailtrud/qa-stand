# UI Tests

UI автоматизация проекта QA Sandbox.

## Стек

- Java 21
- JUnit 5
- Selenide
- Gradle
- Allure
- Docker
- Selenoid (планируется)

---

## Запуск тестов

Все тесты:

```bash
./gradlew test
```

Конкретный класс:

```bash
./gradlew test --tests UserTests
```

---

## Allure

Генерация отчета:

```bash
allure serve build/allure-results
```

---

## Структура проекта

```text
src

├── test
│   ├── java
│   │   ├── pages
│   │   ├── tests
│   │   ├── api
│   │   ├── db
│   │   ├── utils
│   │   └── base
│   │
│   └── resources
│
└── build.gradle
```

---

## Текущее покрытие

Пока реализовано:

- настройка проекта

---

## Планируемые UI тесты

### Авторизация

- успешный вход
- неверный пароль
- пустые поля

### Пользователи

- создание пользователя
- удаление пользователя
- валидация полей
- отображение списка

### Playground

- Input
- Password
- Textarea
- Checkbox
- Radio
- Select
- Multi Select
- Upload
- Modal
- Alert
- Tabs
- Dynamic Table
- Pagination

---

## Roadmap

Планируется добавить:

- REST Assured
- Database проверки
- Test Data Builder
- Page Object
- Allure Steps
- Selenoid
- Docker Compose
- GitHub Actions