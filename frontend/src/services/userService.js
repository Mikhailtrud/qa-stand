import { request } from "./api";

export async function getUsers(token) {

    return await request("/users", {
        headers: {
            Authorization: `Bearer ${token}`
        }
    });

}

export async function createUser(token, user) {

    return await request("/users", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`
        },
        body: JSON.stringify(user)
    });

}

export async function deleteUser(token, id) {

    await request(`/users/${id}`, {
        method: "DELETE",
        headers: {
            Authorization: `Bearer ${token}`
        }
    });

}

export async function updateUser(token, id, user) {

    return await request(`/users/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`
        },
        body: JSON.stringify(user)
    });

}
