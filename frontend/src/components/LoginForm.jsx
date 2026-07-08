import { useState } from "react";

function LoginForm({ onLogin }) {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    async function login() {

        const response = await fetch("http://localhost:8080/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email,
                password
            })
        });

        if (!response.ok) {
            alert("Invalid credentials");
            return;
        }

        const data = await response.json();

        onLogin(data.token);

    }

    return (
        <div>

            <h2>Login</h2>

            <div>
                <label>Email</label>
                <br />
                <input
                    data-testid="login-email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
            </div>

            <br />

            <div>
                <label>Password</label>
                <br />
                <input
                    type="password"
                    data-testid="login-password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
            </div>

            <br />

            <button
                data-testid="login-button"
                onClick={login}
            >
                Login
            </button>

        </div>
    );

}

export default LoginForm;