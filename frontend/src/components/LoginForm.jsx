import { useState } from "react";
import { login } from "../services/authService";
import Alert from "./common/Alert";

function LoginForm({ onLogin }) {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    async function handleLogin() {

        try {

            setError("");

            const data = await login(email, password);

            onLogin(data.token);

        } catch (e) {

            setError(e.message);

        }

    }

    return (

        <div>

            <h2 data-testid="login-title">Login</h2>

            <Alert
                message={error}
                type="error"
            />

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
                onClick={handleLogin}
            >
                Login
            </button>

        </div>

    );

}

export default LoginForm;
