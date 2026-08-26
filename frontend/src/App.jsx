import { useEffect, useState } from "react";
import AppRouter from "./router/AppRouter";
import { getStoredToken, logout } from "./services/authService";

import {
    getUsers,
    createUser as createUserRequest,
    updateUser as updateUserRequest,
    deleteUser as deleteUserRequest
} from "./services/userService";

function App() {

    const [token, setToken] = useState(getStoredToken);

    const [email, setEmail] = useState("");
    const [name, setName] = useState("");
    const [password, setPassword] = useState("");
    const [role, setRole] = useState("USER");
    const [editingUserId, setEditingUserId] = useState(null);

    const [users, setUsers] = useState([]);
    const [message, setMessage] = useState("");

    async function loadUsers() {

        if (!token) {
            return;
        }

        try {

            setMessage("");

            const data = await getUsers(token);

            setUsers(data);

        } catch (e) {

            setMessage(e.message);

        }

    }

    async function createUser() {

        try {

            setMessage("");

            await createUserRequest(token, {
                email,
                name,
                password,
                role
            });

            await loadUsers();

            setEmail("");
            setName("");
            setPassword("");
            setRole("USER");

            setMessage("User created successfully.");

        } catch (e) {

            setMessage(e.message);

        }

    }

    function editUser(user) {
        setEditingUserId(user.id);
        setEmail(user.email);
        setName(user.name);
        setPassword("");
        setRole(user.role);
        setMessage("");
    }

    function cancelEdit() {
        setEditingUserId(null);
        setEmail("");
        setName("");
        setPassword("");
        setRole("USER");
    }

    async function updateUser() {
        try {
            setMessage("");
            await updateUserRequest(token, editingUserId, {
                email,
                name,
                role,
                password
            });
            await loadUsers();
            cancelEdit();
            setMessage("User updated successfully.");
        } catch (e) {
            setMessage(e.message);
        }
    }

    async function deleteUser(id) {

        try {

            setMessage("");

            await deleteUserRequest(token, id);

            await loadUsers();

            setMessage("User deleted successfully.");

        } catch (e) {

            setMessage(e.message);

        }

    }

    useEffect(() => {
        if (!token) {
            return undefined;
        }

        let active = true;
        getUsers(token)
            .then((data) => {
                if (active) {
                    setUsers(data);
                }
            })
            .catch((error) => {
                if (active) {
                    setMessage(error.message);
                }
            });

        return () => {
            active = false;
        };
    }, [token]);

    function handleLogout() {
        logout();
        setToken(null);
        setUsers([]);
        setMessage("");
    }

    return (

        <AppRouter
            token={token}
            setToken={setToken}
            onLogout={handleLogout}

            email={email}
            setEmail={setEmail}

            name={name}
            setName={setName}

            password={password}
            setPassword={setPassword}

            role={role}
            setRole={setRole}

            users={users}
            message={message}

            createUser={createUser}
            updateUser={updateUser}
            editUser={editUser}
            cancelEdit={cancelEdit}
            editingUserId={editingUserId}
            deleteUser={deleteUser}
        />

    );

}

export default App;
