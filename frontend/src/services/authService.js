import { request } from "./api";

export const AUTH_TOKEN_KEY = "authToken";

export async function login(email, password) {

    const data = await request("/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            email,
            password
        })
    });

    localStorage.setItem(AUTH_TOKEN_KEY, data.token);

    return data;

}

export function getStoredToken() {
    return localStorage.getItem(AUTH_TOKEN_KEY);
}

export function logout() {
    localStorage.removeItem(AUTH_TOKEN_KEY);
}
