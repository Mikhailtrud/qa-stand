import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import LoginForm from "../components/LoginForm";

import DashboardPage from "../pages/Dashboard/DashboardPage";
import UsersPage from "../pages/Users/UsersPage";
import PlaygroundPage from "../pages/Playground/PlaygroundPage";

function AppRouter(props) {

    if (!props.token) {
        return <LoginForm onLogin={props.setToken} />;
    }

    return (

        <BrowserRouter>

            <Routes>

                <Route
                    path="/"
                    element={<Navigate to="/dashboard" replace />}
                />

                <Route
                    path="/dashboard"
                    element={<DashboardPage />}
                />

                <Route
                    path="/users"
                    element={
                        <UsersPage
                            email={props.email}
                            setEmail={props.setEmail}

                            name={props.name}
                            setName={props.setName}

                            password={props.password}
                            setPassword={props.setPassword}

                            role={props.role}
                            setRole={props.setRole}

                            users={props.users}

                            createUser={props.createUser}
                            deleteUser={props.deleteUser}
                        />
                    }
                />

                <Route
                    path="/playground"
                    element={<PlaygroundPage />}
                />

            </Routes>

        </BrowserRouter>

    );

}

export default AppRouter;