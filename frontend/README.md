# Frontend

React 19 / Vite frontend for QA Stand.

## Features

- bearer-token login;
- Dashboard, Users, and QA Playground routes;
- list, create, edit, and delete user actions;
- optional password field during edit;
- backend validation and API error display;
- existing QA Playground controls for browser automation practice.

The Users form requires email, name, and password for create. Edit loads the
existing email, name, and role; leaving password empty preserves the backend's
current BCrypt password. The backend remains responsible for authoritative
validation and authorization.

## Run and build

Run the UI locally with the backend at `http://localhost:8080`:

```bash
npm install
npm run dev
```

Production build and lint:

```bash
npm run build
npm run lint
```

Application: <http://localhost:5173>

Run it as part of the complete stand from the repository root:

```bash
docker compose -f docker-compose.yaml up --build -d
```

## Structure

```text
src/
|-- components/
|   |-- common/
|   |-- layout/
|   |-- playground/
|   `-- users/
|-- pages/
|   |-- Dashboard/
|   |-- Playground/
|   `-- Users/
|-- router/
|-- services/
|-- styles/
|-- App.jsx
`-- main.jsx
```

`services/api.js` converts backend field-validation responses into messages the
page can display. `services/userService.js` contains GET, POST, PUT, and DELETE
calls for `/users`.

## QA Playground

The Playground includes text/password inputs, textarea, checkbox/radio/select
controls, file upload, modal and alert behavior, tabs, dynamic tables, and
pagination. Its behavior is separate from the user-management integrations.
