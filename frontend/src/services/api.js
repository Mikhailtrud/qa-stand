const BASE_URL = "http://localhost:8080";

async function request(url, options = {}) {

    const response = await fetch(BASE_URL + url, options);

    let data = null;

    try {
        data = await response.json();
    } catch {
        data = null;
    }

    if (!response.ok) {
        throw new Error(data?.message || "Request failed");
    }

    return data;
}

export { request };