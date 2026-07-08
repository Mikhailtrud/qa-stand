import { useEffect, useState } from "react";
import AppRouter from "./router/AppRouter";

function App() {

    const [token, setToken] = useState(null);

    const [email, setEmail] = useState("");
    const [name, setName] = useState("");
    const [password, setPassword] = useState("");
    const [role, setRole] = useState("USER");

    const [users, setUsers] = useState([]);

    async function loadUsers() {

        if (!token) {
            return;
        }

        const response = await fetch("http://localhost:8080/users", {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });

        const data = await response.json();

        setUsers(data);
    }

    async function createUser() {

        const response = await fetch("http://localhost:8080/users", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`
            },
            body: JSON.stringify({
                name,
                email,
                password,
                role
            })
        });

        const data = await response.json();

        await loadUsers();

        setEmail("");
        setName("");
        setPassword("");
        setRole("USER");

        alert("User created. ID = " + data.id);

    }

    async function deleteUser(id) {

        await fetch("http://localhost:8080/users/" + id, {
            method: "DELETE",
            headers: {
                Authorization: `Bearer ${token}`
            }
        });

        await loadUsers();

    }

    useEffect(() => {
        loadUsers();
    }, [token]);

    return (

        <AppRouter
            token={token}
            setToken={setToken}

            email={email}
            setEmail={setEmail}

            name={name}
            setName={setName}

            password={password}
            setPassword={setPassword}

            role={role}
            setRole={setRole}

            users={users}

            createUser={createUser}
            deleteUser={deleteUser}
        />

    );

}

export default App;